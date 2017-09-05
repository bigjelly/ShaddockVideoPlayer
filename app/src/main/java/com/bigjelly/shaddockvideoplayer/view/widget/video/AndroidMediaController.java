/*
 * Copyright (C) 2015 Bilibili
 * Copyright (C) 2015 Zhang Rui <bbcallen@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bigjelly.shaddockvideoplayer.view.widget.video;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.MediaController;

import java.util.ArrayList;

public class AndroidMediaController extends MediaController implements IMediaController {
    private ActionBar mActionBar;
    private View mRootLayout;

    public AndroidMediaController(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public AndroidMediaController(Context context, boolean useFastForward) {
        super(context, useFastForward);
        initView(context);
    }

    public AndroidMediaController(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
    }

    public void setSupportActionBar(@Nullable ActionBar actionBar) {
        mActionBar = actionBar;
        if (isShowing()) {
            actionBar.show();
        } else {
            actionBar.hide();
        }
    }

    public void setRootLayout(@NonNull View rootLayout){
        mRootLayout = rootLayout;
        mRootLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    /**
     * 设置播放的文件名称
     */
    public void setTitle(String name) {
        if (mActionBar != null) {
            mActionBar.setTitle(name);
        }
    }

    @Override
    public void show() {
        super.show();
        if (mActionBar != null)
            mActionBar.show();
        if (mRootLayout != null)
            mRootLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
    }

    @Override
    public void hide() {
        super.hide();
        if (mActionBar != null)
            mActionBar.hide();
        if (mRootLayout != null)
            mRootLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        for (View view : mShowOnceArray)
            view.setVisibility(View.GONE);
        mShowOnceArray.clear();
    }

    //----------
    // Extends
    //----------
    private ArrayList<View> mShowOnceArray = new ArrayList<View>();

    public void showOnce(@NonNull View view) {
        mShowOnceArray.add(view);
        view.setVisibility(View.VISIBLE);
        show();
    }
}
