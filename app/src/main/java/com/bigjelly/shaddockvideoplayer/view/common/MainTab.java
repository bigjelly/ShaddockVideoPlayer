package com.bigjelly.shaddockvideoplayer.view.common;


import com.bigjelly.shaddockvideoplayer.R;
import com.bigjelly.shaddockvideoplayer.view.audio.AudioFragment;
import com.bigjelly.shaddockvideoplayer.view.setting.SettingFragment;
import com.bigjelly.shaddockvideoplayer.view.video.VideoFragment;

/**
 * Created by mby on 17-7-31.
 */

public enum MainTab {
    VIDEO(0, R.string.tab_name_video, R.drawable.tab_home_selector, VideoFragment.class),
    AUDIO(0, R.string.tab_name_audio, R.drawable.tab_home_selector, AudioFragment.class),
    SETTING(0, R.string.tab_name_setting, R.drawable.tab_home_selector, SettingFragment.class);
    private int idx;
    private int resName;
    private int resIcon;
    private Class<?> clazz;

    MainTab(int idx, int resName, int resIcon, Class<?> clazz) {
        this.idx = idx;
        this.resName = resName;
        this.resIcon = resIcon;
        this.clazz = clazz;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int getResName() {
        return resName;
    }

    public void setResName(int resName) {
        this.resName = resName;
    }

    public int getResIcon() {
        return resIcon;
    }

    public void setResIcon(int resIcon) {
        this.resIcon = resIcon;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }
}
