package com.bigjelly.shaddockvideoplayer.view.video.Impl;

import com.bigjelly.shaddockvideoplayer.model.VideoInfo;
import com.bigjelly.shaddockvideoplayer.view.base.Impl.IBaseDetailView;

import java.util.List;

/**
 * Created by mby on 17-8-25.
 */

public interface IVideoListView extends IBaseDetailView<VideoInfo> {
     void onVideoListSuccess(List<VideoInfo> response);
}
