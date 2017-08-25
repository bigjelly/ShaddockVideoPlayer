package com.bigjelly.shaddockvideoplayer.view.base.Impl;


import com.bigjelly.shaddockvideoplayer.net.ResultResponse;

/**
 * Created by mby on 17-8-5.
 */

public interface IBaseDetailView<T> {
    void onError(int type, ResultResponse<T> response);
}
