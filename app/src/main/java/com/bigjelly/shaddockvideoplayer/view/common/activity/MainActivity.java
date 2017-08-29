package com.bigjelly.shaddockvideoplayer.view.common.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.bigjelly.shaddockvideoplayer.R;
import com.bigjelly.shaddockvideoplayer.constant.GeneralID;
import com.bigjelly.shaddockvideoplayer.util.LogUtils;
import com.bigjelly.shaddockvideoplayer.view.base.BaseActivity;
import com.bigjelly.shaddockvideoplayer.view.base.BaseFragment;
import com.bigjelly.shaddockvideoplayer.view.base.Impl.BackHandledInterface;
import com.bigjelly.shaddockvideoplayer.view.common.TabManager;


public class MainActivity extends BaseActivity implements BackHandledInterface {

    private static final int REQUEST_PERMISSION = 100;

    private static final String TAG = "MainActivity";
    private long mKeyTime = 0;
    TabManager mTabManager;

    private BaseFragment mBackHandedFragment;


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        mTabManager.changeTab(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkPermission();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initView() {
        TabLayout tabLayout = findView(R.id.tl_main_tabs);
        // 初始化底部的view
        mTabManager = TabManager.getInstance(getApplicationContext());
        mTabManager.initTabs(this,getIntent(),tabLayout);
    }

    @Override
    protected void initData() {
    }

    @Override
    protected int getContentView() {
        return R.layout.act_main;
    }


//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (KeyEvent.KEYCODE_BACK == keyCode && event.getAction() == KeyEvent.ACTION_DOWN) {
//
//            if ((System.currentTimeMillis() - mKeyTime) > 2000) {
//                mKeyTime = System.currentTimeMillis();
//                Toast.makeText(getApplicationContext(), "确定要离开吗？", Toast.LENGTH_SHORT).show();
//            } else {
//                finish();
//                System.exit(0);
//            }
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }

    @Override
    public void onBackPressed() {
        if (mBackHandedFragment == null || !mBackHandedFragment.onBackPressed()) {
            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                if ((System.currentTimeMillis() - mKeyTime) > 2000) {
                    mKeyTime = System.currentTimeMillis();
                    Toast.makeText(getApplicationContext(), "确定要离开吗？", Toast.LENGTH_SHORT).show();
                } else {
                    finish();
                    System.exit(0);
                }
            } else {
                getSupportFragmentManager().popBackStack();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION) {
            if (null != grantResults && grantResults.length > 0) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                }
            } else {
                LogUtils.i(TAG,"Request permission has been refused.");
            }
        }
    }

    public void checkPermission() {
        if (Build.VERSION.SDK_INT < 23) {
            return;
        }
        if (PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(mContext,
                GeneralID.PERMISSION_ONPICKDOC)) {
            return;
        }
        LogUtils.i(TAG,"Request permission.");
        ActivityCompat.requestPermissions(this,
                new String[] {
                        GeneralID.PERMISSION_ONPICKDOC
                },
                REQUEST_PERMISSION);
    }

    @Override
    public void setSelectedFragment(BaseFragment selectedFragment) {
        this.mBackHandedFragment = selectedFragment;
    }
}
