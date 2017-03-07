package com.rayworks.tracker.utils;

/**
 * Created by Sean on 3/7/17.
 * <p>
 * A simple logger for debug usage.
 */

public final class DroidLogger {
    private static boolean debugMode = true;

    private static final String PREFIX = ">>> ";

    public static void setDebugMode(boolean value) {
        debugMode = value;
    }

    public static boolean isDebugMode() {
        return debugMode;
    }

    public static void d(String msg) {
        if (debugMode)
            System.out.println(PREFIX + msg);
    }

    public static void e(String msg) {
        if (debugMode)
            System.err.println(PREFIX + msg);
    }
}
