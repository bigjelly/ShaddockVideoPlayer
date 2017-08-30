package com.bigjelly.shaddockvideoplayer.presenter.video;

import com.bigjelly.shaddockvideoplayer.AndFastApplication;
import com.bigjelly.shaddockvideoplayer.greendao.DaoSession;
import com.bigjelly.shaddockvideoplayer.greendao.VideoInfoDao;
import com.bigjelly.shaddockvideoplayer.model.VideoInfo;
import com.bigjelly.shaddockvideoplayer.presenter.base.BasePresenter;
import com.bigjelly.shaddockvideoplayer.view.video.Impl.IVideoListView;

import java.util.List;

/**
 * Created by mby on 17-8-30.
 */

public class VideoListPresenter extends BasePresenter<IVideoListView> {

    public VideoListPresenter(IVideoListView view) {
        super(view);
    }

    public void getVideoList(boolean isRefresh,Long fileID) {
        DaoSession daoSession = ((AndFastApplication) AndFastApplication.getContext()).getDaoSession();
        List<VideoInfo> list = daoSession.getVideoInfoDao().queryBuilder().where(VideoInfoDao.Properties.FileID.eq(fileID)).list();
        if (list.size() > 0 ){
            mView.onVideoListSuccess(list);
        }
    }
}
