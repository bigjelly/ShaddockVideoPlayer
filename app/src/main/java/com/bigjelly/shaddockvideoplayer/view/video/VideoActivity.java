package com.bigjelly.shaddockvideoplayer.view.video;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.TableLayout;
import android.widget.TextView;

import com.bigjelly.shaddockvideoplayer.R;
import com.bigjelly.shaddockvideoplayer.constant.RecentMediaStorage;
import com.bigjelly.shaddockvideoplayer.util.LogUtils;
import com.bigjelly.shaddockvideoplayer.view.base.BaseActivity;
import com.bigjelly.shaddockvideoplayer.view.widget.video.AndroidMediaController;
import com.bigjelly.shaddockvideoplayer.view.widget.video.IjkVideoView;
import com.bigjelly.shaddockvideoplayer.view.widget.video.Settings;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * Created by mby on 17-9-1.
 */

public class VideoActivity extends BaseActivity {

    private static final String TAG = "VideoActivity";

    private Settings mSettings;
    private String mVideoPath;
    private String mVideoTitle;
    private Uri mVideoUri;
    private boolean mBackPressed;
    private int LastPosition = 0;

    private AndroidMediaController mMediaController;
    private IjkVideoView mVideoView;
    private TextView mToastTextView;
    private TableLayout mHudView;
    private DrawerLayout mDrawerLayout;



    public static Intent newIntent(Context context, String videoPath, String videoTitle) {
        Intent intent = new Intent(context, VideoActivity.class);
        intent.putExtra("videoPath", videoPath);
        intent.putExtra("videoTitle", videoTitle);
        return intent;
    }

    public static void intentTo(Context context, String videoPath, String videoTitle) {
        context.startActivity(newIntent(context, videoPath, videoTitle));
    }

    @Override
    protected int getContentView() {
        return R.layout.act_video_player;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        super.initView();
        mSettings = new Settings(this);

        // handle arguments
        mVideoPath = getIntent().getStringExtra("videoPath");
        mVideoTitle = getIntent().getStringExtra("videoTitle");
        if (!TextUtils.isEmpty(mVideoPath)) {
            new RecentMediaStorage(this).saveUrlAsync(mVideoPath);
        }
        // init UI
        Toolbar toolbar = findView(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        mMediaController = new AndroidMediaController(this, false);
        mMediaController.setSupportActionBar(actionBar);

        mToastTextView = findView(R.id.toast_text_view);
        mHudView = findView(R.id.hud_view);
        mDrawerLayout = findView(R.id.drawer_layout);
        mMediaController.setRootLayout(mDrawerLayout);

        // init player
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");

        mVideoView = findView(R.id.video_view);
        mVideoView.setMediaController(mMediaController);
        mVideoView.requestFocus();
        mVideoView.setHudView(mHudView);
        mVideoView.setOnCompletionListener(onCompletionListener);

        // prefer mVideoPath
        if (mVideoPath != null)
            mVideoView.setVideoPath(mVideoPath);
        else if (mVideoUri != null)
            mVideoView.setVideoURI(mVideoUri);
        else {
            LogUtils.e(TAG, "Null Data Source\n");
            finish();
            return;
        }
        mVideoView.start();
    }

    @Override
    protected void initData() {
        super.initData();
        mMediaController.setTitle(mVideoTitle);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mVideoView != null && !mVideoView.isPlaying()) {
            mVideoView.seekTo(LastPosition);
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (mVideoView != null) {
            LastPosition = mVideoView.getCurrentPosition();
            mVideoView.pause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBackPressed || !mVideoView.isBackgroundPlayEnabled()) {
            mVideoView.stopPlayback();
            mVideoView.release(true);
            mVideoView.stopBackgroundPlay();
        } else {
            mVideoView.enterBackground();
        }
        IjkMediaPlayer.native_profileEnd();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mVideoView != null && mVideoView.isDrawingCacheEnabled()) {
            mVideoView.destroyDrawingCache();
        }
    }

    @Override
    public void onBackPressed() {
        mBackPressed = true;

        super.onBackPressed();
    }

    /**
     * 视频播放完成事件回调
     */
    private IMediaPlayer.OnCompletionListener onCompletionListener = new IMediaPlayer.OnCompletionListener() {

        @Override
        public void onCompletion(IMediaPlayer mp) {
            mVideoView.pause();
        }
    };
}
