package com.bigjelly.shaddockvideoplayer.view.video.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;

import com.andfast.pullrecyclerview.BaseRecyclerAdapter;
import com.andfast.pullrecyclerview.BaseViewHolder;
import com.bigjelly.shaddockvideoplayer.R;
import com.bigjelly.shaddockvideoplayer.model.VideoFile;
import com.bigjelly.shaddockvideoplayer.util.FragmentUtils;
import com.bigjelly.shaddockvideoplayer.view.video.fragment.VideoListFragment;
import com.bumptech.glide.Glide;

import java.io.File;
import java.util.List;

/**
 * Created by mby on 17-8-25.
 */

public class VideoFilesAdpater extends BaseRecyclerAdapter<VideoFile> {

    private FragmentManager mFragmentManager;
    public VideoFilesAdpater(Context context, int layoutResId, List<VideoFile> data, FragmentManager fragmentManager) {
        super(context, layoutResId, data);
        mFragmentManager = fragmentManager;
    }

    @Override
    protected void convert(BaseViewHolder holder, final VideoFile item) {
        holder.setText(R.id.tv_file_name,item.name);
        holder.setText(R.id.tv_file_number,String.format("共有 %d 个视频",item.count));

        Glide.with(mContext)
                .load(Uri.fromFile(new File(item.path)))
                .placeholder(R.mipmap.ic_video_files)
                .error(R.mipmap.ic_video_files)
                .crossFade()
                .into((ImageView)holder.getView(R.id.img_video));
        holder.getView(R.id.item_root).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentUtils.replace(mFragmentManager,R.id.video_frg,VideoListFragment.newInstance(item),true,"VideoList");
            }
        });
    }
}
