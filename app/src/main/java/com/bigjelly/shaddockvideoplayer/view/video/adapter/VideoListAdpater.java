package com.bigjelly.shaddockvideoplayer.view.video.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;

import com.andfast.pullrecyclerview.BaseRecyclerAdapter;
import com.andfast.pullrecyclerview.BaseViewHolder;
import com.bigjelly.shaddockvideoplayer.R;
import com.bigjelly.shaddockvideoplayer.model.VideoInfo;
import com.bigjelly.shaddockvideoplayer.view.video.VideoActivity;
import com.bumptech.glide.Glide;

import java.io.File;
import java.util.List;

/**
 * Created by mby on 17-8-25.
 */

public class VideoListAdpater extends BaseRecyclerAdapter<VideoInfo> {

    private FragmentManager mFragmentManager;
    public VideoListAdpater(Context context, int layoutResId, List<VideoInfo> data, FragmentManager fragmentManager) {
        super(context, layoutResId, data);
        mFragmentManager = fragmentManager;
    }

    @Override
    protected void convert(BaseViewHolder holder, final VideoInfo item) {
        holder.setText(R.id.tv_file_name,item.name);
        holder.setText(R.id.tv_file_number,item.size);

        Glide.with(mContext)
                .load(Uri.fromFile(new File(item.path)))
                .placeholder(R.mipmap.ic_video_bg)
                .error(R.mipmap.ic_video_bg)
                .crossFade()
                .into((ImageView)holder.getView(R.id.img_video));
        holder.getView(R.id.item_root).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VideoActivity.intentTo(mContext, item.path,item.name);
            }
        });
    }
}
