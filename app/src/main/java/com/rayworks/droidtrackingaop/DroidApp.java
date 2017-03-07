package com.rayworks.droidtrackingaop;

import android.app.Application;

import com.rayworks.droidtrackingaop.tracking.TrackingDelegator;

/**
 * Created by Sean on 3/7/17.
 */

public class DroidApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        TrackingDelegator.getInstance().init(this, BuildConfig.DEBUG, "");
    }
}
