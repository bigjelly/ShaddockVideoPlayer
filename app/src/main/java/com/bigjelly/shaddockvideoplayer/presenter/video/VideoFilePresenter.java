package com.bigjelly.shaddockvideoplayer.presenter.video;

import com.bigjelly.shaddockvideoplayer.constant.GeneralID;
import com.bigjelly.shaddockvideoplayer.model.VideoFile;
import com.bigjelly.shaddockvideoplayer.net.ResultResponse;
import com.bigjelly.shaddockvideoplayer.presenter.base.BaseCallBack;
import com.bigjelly.shaddockvideoplayer.presenter.base.BasePresenter;
import com.bigjelly.shaddockvideoplayer.util.LogUtils;
import com.bigjelly.shaddockvideoplayer.view.video.Impl.IVideoFileView;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by mby on 17-8-25.
 */

public class VideoFilePresenter extends BasePresenter<IVideoFileView> {
    private static final String TAG = "VideoFilePresenter";
    public VideoFilePresenter(IVideoFileView view) {
        super(view);
    }
    public void getVideoFileList(){

        Observable observable = Observable.create(new Observable.OnSubscribe<List<VideoFile>>() {
            @Override
            public void call(Subscriber<? super List<VideoFile>> subscriber) {
                ArrayList<VideoFile> videoInfos = new ArrayList<>();
                for (int i = 0;i<20;i++){
                    VideoFile videoInfo = new VideoFile();
                    videoInfo.name = i+ "测试数据";
                    videoInfo.number = i;
                    videoInfos.add(videoInfo);
                    LogUtils.i(TAG,"videoInfo -->"+videoInfo.name);
                }
                subscriber.onNext(videoInfos);
            }
        });

        addSubscription(observable, new BaseCallBack<List<VideoFile>>() {
            @Override
            public void onError(Throwable e) {
                mView.onError(GeneralID.TYPE_GET_DATA_ERROR_CODE,new ResultResponse<VideoFile>());
            }

            @Override
            public void onNext(List<VideoFile> videoFiles) {
                mView.onVideoFileSuccess(videoFiles);
            }
        });

    }
}
