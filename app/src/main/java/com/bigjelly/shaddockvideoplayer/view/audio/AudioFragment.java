package com.bigjelly.shaddockvideoplayer.view.audio;

import android.support.v7.widget.DividerItemDecoration;
import android.view.View;
import android.widget.TextView;

import com.andfast.pullrecyclerview.PullRecyclerView;
import com.andfast.pullrecyclerview.layoutmanager.XLinearLayoutManager;
import com.bigjelly.shaddockvideoplayer.R;
import com.bigjelly.shaddockvideoplayer.model.AudioInfo;
import com.bigjelly.shaddockvideoplayer.net.ResultResponse;
import com.bigjelly.shaddockvideoplayer.presenter.audio.AudioListPresenter;
import com.bigjelly.shaddockvideoplayer.view.audio.Impl.IAudioListView;
import com.bigjelly.shaddockvideoplayer.view.audio.adapter.AudioListAdpater;
import com.bigjelly.shaddockvideoplayer.view.base.BasePresenterFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by mby on 17-7-31.
 */

public class AudioFragment extends BasePresenterFragment<AudioListPresenter> implements IAudioListView, PullRecyclerView.OnRecyclerRefreshListener {

    private static final String TAG = "AudioFragment";
    private PullRecyclerView mRecyclerView;
    private XLinearLayoutManager mLayoutManager;
    private AudioListAdpater mAdpater;

    @Override
    protected int getLayoutId() {
        return R.layout.frg_audio;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        TextView toolbarTitle = findView(R.id.toolbar_title);
        toolbarTitle.setText(R.string.tab_name_audio);

        mRecyclerView = findView(R.id.pull_recycler_view);

        // 初始化PullRecyclerView
        mLayoutManager = new XLinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.shape_simple_item_decoration));
        mRecyclerView.addItemDecoration(itemDecoration);
        mAdpater = new AudioListAdpater(getContext(), R.layout.lay_item_video_file, new ArrayList<AudioInfo>(),getFragmentManager());
        mRecyclerView.setAdapter(mAdpater);
        mRecyclerView.enablePullRefresh(true); // 开启下拉刷新，默认即为true，可不用设置
        mRecyclerView.enableLoadMore(false);
        mRecyclerView.setOnRecyclerRefreshListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        mvpPresenter.getAudioList(false);
    }

    @Override
    protected AudioListPresenter createPresenter() {
        return new AudioListPresenter(this);
    }

    @Override
    public void onError(int type, ResultResponse<AudioInfo> response) {

    }

    @Override
    public void onVideoListSuccess(List<AudioInfo> response) {
        mRecyclerView.stopRefresh();
        mAdpater.replaceAll(response);
    }

    @Override
    public void onPullRefresh() {
        mvpPresenter.getAudioList(true);
    }

    @Override
    public void onLoadMore() {

    }
}
