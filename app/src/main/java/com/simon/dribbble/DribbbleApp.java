package com.simon.dribbble;

import android.app.Application;
import android.content.Context;

import com.simon.agiledevelop.utils.PreferencesHelper;
import com.simon.dribbble.data.model.User;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * .       .---.
 * .      (_,/\ \
 * .     (`a a(  )
 * .     ) \=  ) (
 * .    (.--' '--.)
 * .    / (_\_/_) \
 * .   | / \   / \ |
 * .    \\ / . \ //
 * .     \/\___/\/
 * .     |  \_/  |
 * .      \  /  /
 * .       \/  /
 * .        ( (
 * .        |\ \
 * .        | \ \
 * .        | |\ \
 * .        | | \ \
 * .       /__Y /__Y
 * <p>
 * Created by Simon Han on 2016/8/20.
 */
public class DribbbleApp extends Application {
    private static DribbbleApp mInstance;
    public static PreferencesHelper mPreferencesHelper;
    private User mUserInfo;
    private RefWatcher mRefWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        // 内存泄漏检测
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        mRefWatcher = LeakCanary.install(this);

        mInstance = this;
        mPreferencesHelper = new PreferencesHelper(this);

        UncaughtException.getInstance().init(this);
    }

    public static synchronized DribbbleApp context() {
        return mInstance;
    }

    public static PreferencesHelper spHelper() {
        return mPreferencesHelper;
    }

    public User getUserInfo() {
        return mUserInfo;
    }

    public void setUserInfo(User mUserInfo) {
        this.mUserInfo = mUserInfo;
    }

    public static RefWatcher getRefWatcher(Context context) {
        DribbbleApp application = (DribbbleApp) context.getApplicationContext();
        return application.mRefWatcher;
    }
}
