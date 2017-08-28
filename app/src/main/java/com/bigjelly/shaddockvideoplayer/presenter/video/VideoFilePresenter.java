package com.bigjelly.shaddockvideoplayer.presenter.video;

import android.os.Environment;

import com.bigjelly.shaddockvideoplayer.model.VideoFile;
import com.bigjelly.shaddockvideoplayer.model.VideoInfo;
import com.bigjelly.shaddockvideoplayer.presenter.base.BasePresenter;
import com.bigjelly.shaddockvideoplayer.util.FileUtils;
import com.bigjelly.shaddockvideoplayer.util.LogUtils;
import com.bigjelly.shaddockvideoplayer.util.MediaUtils;
import com.bigjelly.shaddockvideoplayer.view.video.Impl.IVideoFileView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.observables.GroupedObservable;
import rx.schedulers.Schedulers;

/**
 * Created by mby on 17-8-25.
 */

public class VideoFilePresenter extends BasePresenter<IVideoFileView> {
    private static final String TAG = "VideoFilePresenter";

    /**
     * 所有视频文件
     */
    private List<File> videoFiles = new ArrayList<File>();

    /**
     * 包含有视频文件夹集合
     */
    private List<VideoFile> fileBeans = new ArrayList<VideoFile>();

    public VideoFilePresenter(IVideoFileView view) {
        super(view);
    }

    public void getVideoFileList() {

        File rootFile = Environment.getExternalStorageDirectory();
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
                    groupByFiles(videoFiles);
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(File file) {
                    videoFiles.add(file);
                }

            });
        } else {
            LogUtils.i(TAG, "get root file is null,please check sdcard!");
        }
    }

    /**
     * 对所有的视频文件进行分组操作
     * @param files 文件集合
     */
    private void groupByFiles(List<File> files) {
        addSubscription(Observable.from(files).groupBy(new Func1<File, File>() {
            @Override
            public File call(File file) {
                /**以视频文件的父文件夹路径进行分组**/
                return file.getParentFile();
            }
        }), new Subscriber<GroupedObservable<File, File>>() {
            @Override
            public void onCompleted() {
                mView.onVideoFileSuccess(fileBeans);
                videoFiles.clear();
                fileBeans.clear();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(GroupedObservable<File, File> fileFileGroupedObservable) {
                fileFileGroupedObservable
                        .observeOn(Schedulers.computation())  //代表CPU计算密集型的操作, 例如需要大量计算的操作
                        .subscribe(new Subscriber<File>() {
                    int count = 0;
                    File tmpFile;
                    List<VideoInfo> videoInfos = new ArrayList<VideoInfo>();
                    @Override
                    public void onCompleted() {
                        VideoFile videoFile = new VideoFile();
                        videoFile.name = tmpFile.getParentFile().getName();
                        videoFile.count = count;
                        videoFile.path = tmpFile.getAbsolutePath();
                        videoFile.videoInfos = videoInfos;
                        fileBeans.add(videoFile);
                        count = 0;
                        tmpFile = null;
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(File file) {
                        count++;
                        tmpFile = file;
                        VideoInfo videoInfo = new VideoInfo();
                        videoInfo.name = file.getName();
                        videoInfo.path = file.getAbsolutePath();
                        videoInfo.size = FileUtils.showFileSize(file.length());
                        videoInfos.add(videoInfo);
                    }
                });
            }
        });
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
                            return f.exists() && f.canRead() && MediaUtils.isVideo(f);
                        }
                    });
        }
    }
}
