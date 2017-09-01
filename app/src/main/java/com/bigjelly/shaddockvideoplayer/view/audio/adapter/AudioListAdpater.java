package com.bigjelly.shaddockvideoplayer.view.audio.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;

import com.andfast.pullrecyclerview.BaseRecyclerAdapter;
import com.andfast.pullrecyclerview.BaseViewHolder;
import com.bigjelly.shaddockvideoplayer.R;
import com.bigjelly.shaddockvideoplayer.model.AudioInfo;
import com.bumptech.glide.Glide;

import java.io.File;
import java.util.List;

/**
 * Created by mby on 17-8-25.
 */

public class AudioListAdpater extends BaseRecyclerAdapter<AudioInfo> {

    private FragmentManager mFragmentManager;
    public AudioListAdpater(Context context, int layoutResId, List<AudioInfo> data, FragmentManager fragmentManager) {
        super(context, layoutResId, data);
        mFragmentManager = fragmentManager;
    }

    @Override
    protected void convert(BaseViewHolder holder, AudioInfo item) {
        holder.setText(R.id.tv_file_name,item.name);
        holder.setText(R.id.tv_file_number,item.size);

        Glide.with(mContext)
                .load(Uri.fromFile(new File(item.path)))
                .placeholder(R.mipmap.ic_audio_bg)
                .error(R.mipmap.ic_audio_bg)
                .crossFade()
                .into((ImageView)holder.getView(R.id.img_video));
        holder.getView(R.id.item_root).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
