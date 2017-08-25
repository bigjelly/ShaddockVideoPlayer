package com.bigjelly.shaddockvideoplayer.presenter.base;


import com.bigjelly.shaddockvideoplayer.view.base.Impl.IBasePullListView;

/**
 * Created by mby on 17-8-7.
 * RecyclerView
 */

public class BaseListPresenter extends BasePresenter<IBasePullListView> {
    public BaseListPresenter(IBasePullListView view) {
        super(view);
    }
}
