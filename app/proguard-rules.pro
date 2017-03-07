# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/Shirley/Documents/develop/android-dev/android_sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-keepattributes *Annotation*

-keep class com.rayworks.droidtrackingaop.tracking.** {*;}

-keep class android.support.design.widget.** {*;}
-keep class android.support.v4.view.** {*;}
-keep class android.support.v4.widget.** {*;}
-keep class android.support.v7.** { *; }
-keep interface android.support.v7.** { *; }

-keep public class com.android.vending.licensing.ILicensingService

-keepclasseswithmembers class * { public <init>(android.content.Context, android.util.AttributeSet, int); }
-keepclasseswithmembers class * { public <init>(android.content.Context, android.util.AttributeSet); }
-keepclasseswithmembernames class * { native <methods>; }

-keep class * implements android.os.Parcelable { public static final android.os.Parcelable$Creator *; }

-keep class com.adobe.mobile.**{*;}

-keep public class * extends android.support.v7.app.AppCompatActivity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider

-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers class * extends android.content.Context {
   public void *(android.view.View);
   public void *(android.view.MenuItem);
}

-keepclassmembers class * implements android.os.Parcelable {
    static ** CREATOR;
}

-keepclassmembers class **.R$* {
    public static <fields>;
}

-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}