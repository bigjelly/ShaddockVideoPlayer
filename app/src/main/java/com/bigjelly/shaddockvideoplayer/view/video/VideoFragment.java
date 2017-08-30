package com.bigjelly.shaddockvideoplayer.view.video;

import android.view.View;

import com.andfast.pullrecyclerview.PullRecyclerView;
import com.andfast.pullrecyclerview.layoutmanager.XLinearLayoutManager;
import com.bigjelly.shaddockvideoplayer.R;
import com.bigjelly.shaddockvideoplayer.util.FragmentUtils;
import com.bigjelly.shaddockvideoplayer.view.base.BaseFragment;
import com.bigjelly.shaddockvideoplayer.view.video.adapter.VideoFilesAdpater;
import com.bigjelly.shaddockvideoplayer.view.video.fragment.VideoFileListFragment;

/**
 * Created by mby on 17-7-31.
 */

public class VideoFragment extends BaseFragment {

    private PullRecyclerView mRecyclerView;
    private XLinearLayoutManager mLayoutManager;
    private VideoFilesAdpater mAdpater;

    @Override
    protected int getLayoutId() {
        return R.layout.frg_video;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        FragmentUtils.replace(getFragmentManager(),R.id.video_frg,new VideoFileListFragment(),true,"VideoFileList");
    }

    @Override
    protected void initData() {
        super.initData();
    }
}
