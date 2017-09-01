package com.bigjelly.shaddockvideoplayer.view.audio.Impl;

import com.bigjelly.shaddockvideoplayer.model.AudioInfo;
import com.bigjelly.shaddockvideoplayer.view.base.Impl.IBaseDetailView;

import java.util.List;

/**
 * Created by mby on 17-8-25.
 */

public interface IAudioListView extends IBaseDetailView<AudioInfo> {
     void onVideoListSuccess(List<AudioInfo> response);
}
