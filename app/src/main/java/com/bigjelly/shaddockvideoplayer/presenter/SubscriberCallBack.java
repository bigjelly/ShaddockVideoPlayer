package com.bigjelly.shaddockvideoplayer.presenter;


import com.bigjelly.shaddockvideoplayer.constant.GeneralID;
import com.bigjelly.shaddockvideoplayer.net.ResultResponse;
import com.bigjelly.shaddockvideoplayer.presenter.base.BaseCallBack;

/**
 * Created by mby on 17-8-6.
 */

public abstract class SubscriberCallBack<T> extends BaseCallBack<ResultResponse<T>> {
    private static final String TAG = "SubscriberCallBack";
    @Override
    public void onNext(ResultResponse<T> resultResponse) {
        if (resultResponse.success){
            onSuccess(resultResponse.data);
        }else {
            onFailure(resultResponse);
        }
    }

    @Override
    public void onError(Throwable e) {
        onFailure(new ResultResponse("", GeneralID.TYPE_NET_ERROR_CODE,false,"",e));
    }

    protected abstract void onFailure(ResultResponse response);
    protected abstract void onSuccess(T response);
}
