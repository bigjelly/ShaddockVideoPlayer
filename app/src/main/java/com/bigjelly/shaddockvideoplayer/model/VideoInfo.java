package com.bigjelly.shaddockvideoplayer.model;

import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by mby on 17-8-24.
 * 视频been
 */
@org.greenrobot.greendao.annotation.Entity
public class VideoInfo extends Entity {
    @Id
    public int ID;
    public int fileID;
    public String name;
    public String path;
    public String time;
    public String size;
    @Generated(hash = 474135323)
    public VideoInfo(int ID, int fileID, String name, String path, String time,
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
    public int getID() {
        return this.ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public int getFileID() {
        return this.fileID;
    }
    public void setFileID(int fileID) {
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
