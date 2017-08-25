package com.bigjelly.shaddockvideoplayer.view.audio;

import android.view.View;
import android.widget.TextView;

import com.bigjelly.shaddockvideoplayer.R;
import com.bigjelly.shaddockvideoplayer.view.base.BaseFragment;


/**
 * Created by mby on 17-7-31.
 */

public class AudioFragment extends BaseFragment {

    private static final String TAG = "AudioFragment";

    @Override
    protected int getLayoutId() {
        return R.layout.frg_audio;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        TextView toolbarTitle = findView(R.id.toolbar_title);
        toolbarTitle.setText(R.string.tab_name_audio);
    }
}
