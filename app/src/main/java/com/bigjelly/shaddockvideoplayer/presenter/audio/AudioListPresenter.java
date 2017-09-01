package com.bigjelly.shaddockvideoplayer.presenter.audio;

import android.os.Environment;

import com.bigjelly.shaddockvideoplayer.AndFastApplication;
import com.bigjelly.shaddockvideoplayer.greendao.DaoSession;
import com.bigjelly.shaddockvideoplayer.model.AudioInfo;
import com.bigjelly.shaddockvideoplayer.presenter.base.BasePresenter;
import com.bigjelly.shaddockvideoplayer.util.FileUtils;
import com.bigjelly.shaddockvideoplayer.util.LogUtils;
import com.bigjelly.shaddockvideoplayer.util.MediaUtils;
import com.bigjelly.shaddockvideoplayer.view.audio.Impl.IAudioListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by mby on 17-8-30.
 */

public class AudioListPresenter extends BasePresenter<IAudioListView> {

    private static final String TAG = "AudioListPresenter";
    public AudioListPresenter(IAudioListView view) {
        super(view);
    }

    public void getAudioList(boolean isRefresh) {
        final DaoSession daoSession = ((AndFastApplication) AndFastApplication.getContext()).getDaoSession();
        List<AudioInfo> audioInfoList = daoSession.getAudioInfoDao().queryBuilder().list();
        if (isRefresh) {
            daoSession.getAudioInfoDao().queryBuilder().buildDelete().executeDeleteWithoutDetachingEntities();


            audioInfoList = new ArrayList<>();
        }
        if (audioInfoList != null && audioInfoList.size() > 0) {
            mView.onVideoListSuccess(audioInfoList);
        } else {
            //select
            File rootFile = Environment.getExternalStorageDirectory();
            if (rootFile != null) {
                if (rootFile != null) {
                    Observable<File> observable = Observable.just(rootFile)
                            .flatMap(new Func1<File, Observable<File>>() {
                                @Override
                                public Observable<File> call(File file) {
                                    return listFiles(file);
                                }
                            });

                    addSubscription(observable, new Subscriber<File>() {
                        @Override
                        public void onCompleted() {
                            mView.onVideoListSuccess(daoSession.getAudioInfoDao().queryBuilder().list());
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(File file) {
                            AudioInfo audioInfo = new AudioInfo();
                            audioInfo.name = file.getName();
                            audioInfo.path = file.getAbsolutePath();
                            audioInfo.size = FileUtils.showFileSize(file.length());
                            daoSession.getAudioInfoDao().insert(audioInfo);
                        }

                    });
                } else {
                    LogUtils.i(TAG, "get root file is null,please check sdcard!");
                }
            }
        }
    }

        /**
         * rxjava递归查询内存中的视频文件
         * 在此方法中应该可以做groupby操作，这样能简化操作，日后再研究
         *
         * @param f
         * @return
         */
        public Observable<File> listFiles(final File f) {
            if (f.isDirectory()) {
                return Observable.from(f.listFiles())
                        .flatMap(new Func1<File, Observable<File>>() {
                            @Override
                            public Observable<File> call(File file) {
                                /**如果是文件夹就递归**/
                                return listFiles(file);
                            }
                        });
            } else {
                /**filter操作符过滤视频文件,是视频文件就通知观察者**/
                return Observable.just(f)
                        .filter(new Func1<File, Boolean>() {
                            @Override
                            public Boolean call(File file) {
                                return f.exists() && f.canRead() && MediaUtils.isAudio(f);
                            }
                        });
            }
        }
}
