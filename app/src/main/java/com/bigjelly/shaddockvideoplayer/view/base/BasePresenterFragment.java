package com.bigjelly.shaddockvideoplayer.view.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bigjelly.shaddockvideoplayer.presenter.base.BasePresenter;


/**
 * Created by mby on 17-8-7.
 */

public abstract class BasePresenterFragment<P extends BasePresenter> extends BaseFragment {

    protected P mvpPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mvpPresenter == null) mvpPresenter = createPresenter();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected abstract P createPresenter();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
            mvpPresenter = null;
        }
    }
}
