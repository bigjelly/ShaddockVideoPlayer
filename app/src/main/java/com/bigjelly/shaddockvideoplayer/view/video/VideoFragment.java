package com.bigjelly.shaddockvideoplayer.view.video;

import android.view.View;
import android.widget.TextView;

import com.andfast.pullrecyclerview.PullRecyclerView;
import com.bigjelly.shaddockvideoplayer.R;
import com.bigjelly.shaddockvideoplayer.view.base.BaseFragment;

/**
 * Created by mby on 17-7-31.
 */

public class VideoFragment extends BaseFragment {

    private PullRecyclerView mRecyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.frg_video;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        TextView textView =findView(R.id.toolbar_title);
        textView.setText(R.string.tab_name_video);
    }

    @Override
    protected void initData() {
        super.initData();
    }
}
