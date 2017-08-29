package com.bigjelly.shaddockvideoplayer.model;

import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

/**
 * Created by mby on 17-8-24.
 * 视频been
 */
@org.greenrobot.greendao.annotation.Entity
public class VideoInfo extends Entity {
    @Id
    public Long ID;
    public Long fileID;
    public String name;

    @Unique
    public String path;

    public String time;
    public String size;
    @Generated(hash = 1830830683)
    public VideoInfo(Long ID, Long fileID, String name, String path, String time,
            String size) {
        this.ID = ID;
        this.fileID = fileID;
        this.name = name;
        this.path = path;
        this.time = time;
        this.size = size;
    }
    @Generated(hash = 296402066)
    public VideoInfo() {
    }
    public Long getID() {
        return this.ID;
    }
    public void setID(Long ID) {
        this.ID = ID;
    }
    public Long getFileID() {
        return this.fileID;
    }
    public void setFileID(Long fileID) {
        this.fileID = fileID;
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
