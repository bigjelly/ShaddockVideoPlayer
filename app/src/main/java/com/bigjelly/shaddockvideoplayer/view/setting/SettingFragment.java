package com.bigjelly.shaddockvideoplayer.view.setting;

import android.view.View;
import android.widget.TextView;

import com.bigjelly.shaddockvideoplayer.R;
import com.bigjelly.shaddockvideoplayer.model.Entity;
import com.bigjelly.shaddockvideoplayer.util.LogUtils;
import com.bigjelly.shaddockvideoplayer.view.base.BaseFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by mby on 17-7-31.
 */

public class SettingFragment extends BaseFragment {

    private static final String TAG = "SettingFragment";

    @Override
    protected int getLayoutId() {
        return R.layout.frg_setting;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        TextView toolbarTitle = findView(R.id.toolbar_title);
        toolbarTitle.setText(R.string.tab_name_setting);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventTest(Entity entity){
        LogUtils.i(TAG,"entity.eventCode -->"+entity.eventCode);
    }

    @Override
    protected void showFragmet() {
        super.showFragmet();
        registerEventBus(this);
    }

    @Override
    protected void hiddenFragment() {
        super.hiddenFragment();
        unregisterEventBus(this);
    }
}
