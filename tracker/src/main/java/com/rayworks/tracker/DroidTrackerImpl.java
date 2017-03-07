package com.rayworks.tracker;

import android.content.Context;

import com.adobe.mobile.Analytics;
import com.adobe.mobile.Config;
import com.rayworks.tracker.utils.DroidLogger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Created by Sean on 3/6/17.
 * <p>
 * A TrackHelper class provides the concrete implementations of tracking clients.
 */

public abstract class DroidTrackerImpl {
    private static volatile DroidTrackerImpl droidTracker;

    /***
     * Initializes the tracker.
     *
     * @param context
     * @param debugEnabled
     * @param token
     */
    public abstract void init(Context context, boolean debugEnabled, String token);

    /***
     * Tracks the app states.
     *
     * @param key
     * @param values
     */
    public abstract void trackState(String key, Map<String, Object> values);

    /***
     * Tracks user actions.
     *
     * @param action
     * @param contextData
     */
    public abstract void trackAction(String action, Map<String, Object> contextData);

    /***
     * Starts tracking action.
     *
     * @param action
     * @param contextData
     */
    public void trackActionStart(String action, Map<String, Object> contextData) {
    }

    /***
     * Completes tracking action.
     *
     * @param action
     * @param contextData
     */
    public void trackActionEnd(String action, Map<String, Object> contextData) {
    }

    /* package */
    static DroidTrackerImpl getTracker() {
        if (droidTracker == null) {
            throw new IllegalStateException("UnInitialized tracker.");
        }
        return droidTracker;
    }

    public synchronized static DroidTrackerImpl fromOmniture() {
        if (droidTracker == null) {
            droidTracker = new DroidTrackerImpl.OmnitureTracker();
        }
        return droidTracker;
    }

    /*public*/ static class OmnitureTracker extends DroidTrackerImpl {
        private OmnitureTracker() {
        }

        @Override
        public void init(Context context, boolean debugEnabled, String token) {
            if (context == null) {
                throw new IllegalArgumentException("Context is not valid");
            }

            DroidLogger.setDebugMode(debugEnabled);

            Config.setContext(context.getApplicationContext());
            Config.setDebugLogging(debugEnabled);

            String configFileName = "ADBMobileConfig.json";
            String configFileNameDev = "ADBMobileConfig_dev.json";

            String config;
            String folder = "tracking" + File.separator;

            if (debugEnabled) {
                config = folder + configFileNameDev;
            } else {
                config = folder + configFileName;
            }

            DroidLogger.d("Config loaded : " + config);

            try {
                InputStream inputStream = context.getAssets().open(config);

                Config.overrideConfigStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();

                throw new RuntimeException("Failed to load the config file for tracking" + config);
            }
        }

        @Override
        public void trackState(String key, Map<String, Object> values) {
            Analytics.trackState(key, values);
        }

        @Override
        public void trackAction(String action, Map<String, Object> contextData) {
            Analytics.trackAction(action, contextData);
        }
    }
}
