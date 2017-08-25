package com.bigjelly.shaddockvideoplayer.view.video;

import android.support.v7.widget.DividerItemDecoration;
import android.view.View;
import android.widget.TextView;

import com.andfast.pullrecyclerview.PullRecyclerView;
import com.andfast.pullrecyclerview.layoutmanager.XLinearLayoutManager;
import com.bigjelly.shaddockvideoplayer.R;
import com.bigjelly.shaddockvideoplayer.model.VideoFile;
import com.bigjelly.shaddockvideoplayer.net.ResultResponse;
import com.bigjelly.shaddockvideoplayer.presenter.video.VideoFilePresenter;
import com.bigjelly.shaddockvideoplayer.view.base.BasePresenterFragment;
import com.bigjelly.shaddockvideoplayer.view.video.Impl.IVideoFileView;
import com.bigjelly.shaddockvideoplayer.view.video.adapter.VideoListAdpater;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mby on 17-7-31.
 */

public class VideoFragment extends BasePresenterFragment<VideoFilePresenter> implements IVideoFileView {

    private PullRecyclerView mRecyclerView;
    private XLinearLayoutManager mLayoutManager;
    private VideoListAdpater mAdpater;

    @Override
    protected int getLayoutId() {
        return R.layout.frg_video;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        TextView textView =findView(R.id.toolbar_title);
        textView.setText(R.string.tab_name_video);
        mRecyclerView = findView(R.id.pull_recycler_view);

        // 初始化PullRecyclerView
        mLayoutManager = new XLinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.shape_simple_item_decoration));
        mRecyclerView.addItemDecoration(itemDecoration);
        mAdpater = new VideoListAdpater(getContext(), R.layout.lay_item_video_file, new ArrayList<VideoFile>());
        mRecyclerView.setAdapter(mAdpater);
        mRecyclerView.enablePullRefresh(false);
        mRecyclerView.enableLoadMore(false);
    }

    @Override
    protected void initData() {
        super.initData();
        mvpPresenter.getVideoFileList();
    }

    @Override
    protected VideoFilePresenter createPresenter() {
        return new VideoFilePresenter(this);
    }

    @Override
    public void onError(int type, ResultResponse<VideoFile> response) {

    }

    @Override
    public void onVideoFileSuccess(List<VideoFile> response) {
        mAdpater.replaceAll(response);
    }
}
