package com.bigjelly.shaddockvideoplayer.presenter.video;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;

import com.bigjelly.shaddockvideoplayer.AndFastApplication;
import com.bigjelly.shaddockvideoplayer.model.VideoInfo;
import com.bigjelly.shaddockvideoplayer.util.BitmapUtils;
import com.bigjelly.shaddockvideoplayer.util.LogUtils;
import com.bigjelly.shaddockvideoplayer.util.UIUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mby on 17-8-24.
 */

public class ScannerVideoTask extends AsyncTask<Void,Integer,List<VideoInfo>> {
    private final static String TAG = "ScannerVideoTask";
    private List<VideoInfo> videoInfos=new ArrayList<VideoInfo>();
    @Override
    protected List<VideoInfo> doInBackground(Void... params) {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
            File storageDirectory = Environment.getExternalStorageDirectory();
            videoInfos=getVideoFile(videoInfos, storageDirectory);
            videoInfos=filterVideo(videoInfos);
            LogUtils.d(TAG,"最后的大小"+videoInfos.size());
        }else {
            LogUtils.d(TAG,"The external storage state is not MEDIA_MOUNTED.");
        }
        return videoInfos;
    }

        /**
         * 获取视频文件
         * @param list
         * @param file
         * @return
         */
    private List<VideoInfo> getVideoFile(final List<VideoInfo> list, File file) {

        file.listFiles(new FileFilter() {

            @Override
            public boolean accept(File file) {

                String name = file.getName();

                int i = name.indexOf('.');
                if (i != -1) {
                    name = name.substring(i);
                    if (name.equalsIgnoreCase(".mp4")
                            || name.equalsIgnoreCase(".3gp")
                            || name.equalsIgnoreCase(".wmv")
                            || name.equalsIgnoreCase(".ts")
                            || name.equalsIgnoreCase(".rmvb")
                            || name.equalsIgnoreCase(".mov")
                            || name.equalsIgnoreCase(".m4v")
                            || name.equalsIgnoreCase(".avi")
                            || name.equalsIgnoreCase(".m3u8")
                            || name.equalsIgnoreCase(".3gpp")
                            || name.equalsIgnoreCase(".3gpp2")
                            || name.equalsIgnoreCase(".mkv")
                            || name.equalsIgnoreCase(".flv")
                            || name.equalsIgnoreCase(".divx")
                            || name.equalsIgnoreCase(".f4v")
                            || name.equalsIgnoreCase(".rm")
                            || name.equalsIgnoreCase(".asf")
                            || name.equalsIgnoreCase(".ram")
                            || name.equalsIgnoreCase(".mpg")
                            || name.equalsIgnoreCase(".v8")
                            || name.equalsIgnoreCase(".swf")
                            || name.equalsIgnoreCase(".m2v")
                            || name.equalsIgnoreCase(".asx")
                            || name.equalsIgnoreCase(".ra")
                            || name.equalsIgnoreCase(".ndivx")
                            || name.equalsIgnoreCase(".xvid")) {
                        VideoInfo video = new VideoInfo();
                        file.getUsableSpace();
                        video.name = file.getName();
                        video.path = file.getAbsolutePath();
                        Bitmap videoThumbnail = BitmapUtils.getVideoThumbnail(file.getAbsolutePath(), UIUtils.px2dip(AndFastApplication.getContext(), 100), UIUtils.px2dip(AndFastApplication.getContext(), 50), MediaStore.Images.Thumbnails.MINI_KIND);
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        videoThumbnail.compress(Bitmap.CompressFormat.PNG, 100, baos);
                        byte[] bytes=baos.toByteArray();
                        video.imgBytes = bytes;
                        LogUtils.d(TAG,"name"+video.path);
                        list.add(video);
                        return true;
                    }
                    //判断是不是目录
                } else if (file.isDirectory()) {
                    getVideoFile(list, file);
                }
                return false;
            }
        });

        return list;
    }

    /**10M=10485760 b,小于10m的过滤掉
     * 过滤视频文件
     * @param videoInfos
     * @return
     */
    private List<VideoInfo> filterVideo(List<VideoInfo> videoInfos){
        List<VideoInfo> newVideos=new ArrayList<VideoInfo>();
        for(VideoInfo videoInfo:videoInfos){
            File f=new File(videoInfo.path);
            if(f.exists()&&f.isFile()&&f.length()>10485760){
                newVideos.add(videoInfo);
                LogUtils.d(TAG,"文件大小"+f.length());
            }else {
                LogUtils.d(TAG,"文件太小或者不存在");
            }
        }
        return newVideos;
    }
}
