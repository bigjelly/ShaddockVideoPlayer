package com.bigjelly.shaddockvideoplayer.presenter.video;

import com.bigjelly.shaddockvideoplayer.AndFastApplication;
import com.bigjelly.shaddockvideoplayer.greendao.DaoSession;
import com.bigjelly.shaddockvideoplayer.greendao.VideoInfoDao;
import com.bigjelly.shaddockvideoplayer.model.VideoFile;
import com.bigjelly.shaddockvideoplayer.model.VideoInfo;
import com.bigjelly.shaddockvideoplayer.presenter.base.BasePresenter;
import com.bigjelly.shaddockvideoplayer.util.FileUtils;
import com.bigjelly.shaddockvideoplayer.util.MediaUtils;
import com.bigjelly.shaddockvideoplayer.view.video.Impl.IVideoListView;

import java.io.File;
import java.io.FileFilter;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by mby on 17-8-30.
 */

public class VideoListPresenter extends BasePresenter<IVideoListView> {

    public VideoListPresenter(IVideoListView view) {
        super(view);
    }

    public void getVideoList(boolean isRefresh, final VideoFile videoFile) {
        final DaoSession daoSession = ((AndFastApplication) AndFastApplication.getContext()).getDaoSession();
        List<VideoInfo> videoInfoList = daoSession.getVideoInfoDao().queryBuilder().where(VideoInfoDao.Properties.FileID.eq(videoFile.fileID)).list();
        if(isRefresh){
            daoSession.getVideoInfoDao().queryBuilder().where(VideoInfoDao.Properties.FileID.eq(videoFile.fileID)).buildDelete().executeDeleteWithoutDetachingEntities();

            File file = new File(videoFile.path);

            Observable.from(file.listFiles(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    return f.exists() && f.canRead() && MediaUtils.isVideo(f);
                }
            })).subscribe(new Subscriber<File>() {
                @Override
                public void onCompleted() {
                    List<VideoInfo> videoInfoList = daoSession.getVideoInfoDao().queryBuilder().where(VideoInfoDao.Properties.FileID.eq(videoFile.fileID)).list();
                    mView.onVideoListSuccess(videoInfoList);
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(File file) {
                    VideoInfo videoInfo = new VideoInfo();
                    videoInfo.name = file.getName();
                    videoInfo.path = file.getAbsolutePath();
                    videoInfo.size = FileUtils.showFileSize(file.length());
                    videoInfo.fileID = videoFile.fileID;
                    daoSession.getVideoInfoDao().insert(videoInfo);
                }
            });
        }else {
            mView.onVideoListSuccess(videoInfoList);
        }
    }
}
