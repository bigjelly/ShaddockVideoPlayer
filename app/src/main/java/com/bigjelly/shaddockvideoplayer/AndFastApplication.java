package com.bigjelly.shaddockvideoplayer;

import android.app.Application;
import android.content.Context;

import com.bigjelly.shaddockvideoplayer.greendao.DaoMaster;
import com.bigjelly.shaddockvideoplayer.greendao.DaoSession;
import com.bigjelly.shaddockvideoplayer.util.LogUtils;
import com.bigjelly.shaddockvideoplayer.util.StorageUtils;

import org.greenrobot.greendao.database.Database;


/**
 * Created by mby on 17-7-28.
 */

public class AndFastApplication extends Application {

    private final static String TAG = "AndFastApplication";
    private static Context mContext;
    private DaoSession daoSession;

    /** A flag to show how easily you can switch from standard SQLite to the encrypted SQLCipher. */
    public static final boolean ENCRYPTED = false;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        StorageUtils.initExtDir(getApplicationContext());
        initLog();
        CrashHandler.getInstance().init(getApplicationContext());
        LogUtils.i(TAG,"<><><><><><><><><>");
        LogUtils.i(TAG," app is start!");
        LogUtils.i(TAG,"<><><><><><><><><>");
        initDB();
    }

    private void initDB() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? "videoplayer-encrypted" : "videoplayer-db");
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    private void initLog() {
        LogUtils.setRootTag("andfast");
        LogUtils.setLogPath(StorageUtils.getLogDir());
        LogUtils.setSaveRuntimeInfo(true);
        LogUtils.setAutoSave(true);
        LogUtils.setDebug(BuildConfig.LOG_DEBUG);
    }

    public static Context getContext(){
        return mContext;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
