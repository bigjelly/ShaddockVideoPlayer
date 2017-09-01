package com.bigjelly.shaddockvideoplayer.model;

import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by mby on 17-8-24.
 * 音频been
 */
@org.greenrobot.greendao.annotation.Entity
public class AudioInfo extends Entity {
    @Id
    public Long ID;
    public String name;

    @Unique
    public String path;

    public String time;
    public String size;
    @Generated(hash = 1939201119)
    public AudioInfo(Long ID, String name, String path, String time, String size) {
        this.ID = ID;
        this.name = name;
        this.path = path;
        this.time = time;
        this.size = size;
    }
    @Generated(hash = 2083697945)
    public AudioInfo() {
    }
    public Long getID() {
        return this.ID;
    }
    public void setID(Long ID) {
        this.ID = ID;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPath() {
        return this.path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getSize() {
        return this.size;
    }
    public void setSize(String size) {
        this.size = size;
    }
}
