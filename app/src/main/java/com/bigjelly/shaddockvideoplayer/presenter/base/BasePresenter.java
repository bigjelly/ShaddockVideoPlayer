package com.bigjelly.shaddockvideoplayer.presenter.base;


import com.bigjelly.shaddockvideoplayer.AndFastApplication;
import com.bigjelly.shaddockvideoplayer.constant.GeneralID;
import com.bigjelly.shaddockvideoplayer.net.ApiRetrofit;
import com.bigjelly.shaddockvideoplayer.net.ApiService;
import com.bigjelly.shaddockvideoplayer.net.ResultResponse;
import com.bigjelly.shaddockvideoplayer.util.NetworkUtils;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by mby on 17-8-5.
 */

public class BasePresenter<V> {

    protected ApiService mApiService = ApiRetrofit.getInstance().getApiService();
    protected V mView;
    private CompositeSubscription mCompositeSubscription;

    public BasePresenter(V view) {
        attachView(view);
    }

    public void attachView(V view) {
        mView = view;
    }

    public void detachView() {
        mView = null;
        onUnsubscribe();
    }

    public void addSubscription(Observable observable, Subscriber subscriber) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        if (!NetworkUtils.isAvailable(AndFastApplication.getContext())){
            observable = Observable.create(new Observable.OnSubscribe<ResultResponse<V>>() {
                @Override
                public void call(Subscriber<? super ResultResponse<V>> subscriber) {
                    subscriber.onNext(new ResultResponse<V>("", GeneralID.TYPE_NET_UNAVAILABLE_CODE,false,"",null));
                }
            });
        }
        mCompositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }

    //RXjava取消注册，以避免内存泄露
    public void onUnsubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    public Observable createObservable(final Object data, final boolean result){
        return Observable.create(new Observable.OnSubscribe<ResultResponse>() {
            @Override
            public void call(Subscriber<? super ResultResponse> subscriber) {
                ResultResponse objectResultResponse = new ResultResponse<>();
                objectResultResponse.success = result;
                objectResultResponse.data = data;
                subscriber.onNext(objectResultResponse);
            }
        });
    }

}
