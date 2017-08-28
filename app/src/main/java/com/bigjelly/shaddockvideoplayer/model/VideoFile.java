package com.bigjelly.shaddockvideoplayer.model;

import com.bigjelly.shaddockvideoplayer.greendao.DaoSession;
import com.bigjelly.shaddockvideoplayer.greendao.VideoFileDao;
import com.bigjelly.shaddockvideoplayer.greendao.VideoInfoDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.OrderBy;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;

/**
 * Created by mby on 17-8-25.
 */
@org.greenrobot.greendao.annotation.Entity
public class VideoFile extends Entity{
    @Id
    public int fileID;
    public String name;
    public String path;
    public int count;
    @ToMany(referencedJoinProperty = "fileID")
    @OrderBy("name ASC")
    public List<VideoInfo> videoInfos;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 79247334)
    private transient VideoFileDao myDao;
    @Generated(hash = 1790976915)
    public VideoFile(int fileID, String name, String path, int count) {
        this.fileID = fileID;
        this.name = name;
        this.path = path;
        this.count = count;
    }
    @Generated(hash = 106420510)
    public VideoFile() {
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
    public int getCount() {
        return this.count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 34130431)
    public List<VideoInfo> getVideoInfos() {
        if (videoInfos == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            VideoInfoDao targetDao = daoSession.getVideoInfoDao();
            List<VideoInfo> videoInfosNew = targetDao
                    ._queryVideoFile_VideoInfos(fileID);
            synchronized (this) {
                if (videoInfos == null) {
                    videoInfos = videoInfosNew;
                }
            }
        }
        return videoInfos;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 947766167)
    public synchronized void resetVideoInfos() {
        videoInfos = null;
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1483822610)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getVideoFileDao() : null;
    }
}
