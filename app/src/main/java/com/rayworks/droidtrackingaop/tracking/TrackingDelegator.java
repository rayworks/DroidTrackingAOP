package com.rayworks.droidtrackingaop.tracking;

import android.content.Context;

import com.rayworks.tracker.DroidTrackerImpl;
import com.rayworks.tracker.annotation.TargetScope;
import com.rayworks.tracker.annotation.Trackable;

import java.util.Map;

/**
 * Created by Sean on 3/7/17.
 */

public final class TrackingDelegator {
    private static TrackingDelegator tracker;

    public synchronized static TrackingDelegator getInstance() {
        if (tracker == null) {
            tracker = new TrackingDelegator();
        }
        return tracker;
    }

    public void init(Context context, boolean debugEnabled, String token) {
        DroidTrackerImpl.fromOmniture().init(context, debugEnabled, token);
    }

    /***
     * Tracks the states
     *
     * @param key    key for tracking
     * @param values additional data or null if not needed.
     */
    @Trackable(TargetScope.STATE)
    public void trackState(String key, Map<String, Object> values) {
        // logic covered by aspectJ
    }
}

