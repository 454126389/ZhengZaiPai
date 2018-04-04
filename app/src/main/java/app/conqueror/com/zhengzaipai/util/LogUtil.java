package app.conqueror.com.zhengzaipai.util;

import android.util.Log;

import app.conqueror.com.zhengzaipai.BuildConfig;


/**
 * Created by baixiaokang on 16/4/28.
 */
public class LogUtil {

    private static final int JSON_INDENT = 4;

    public static void d(String tag, String data) {
        if (!BuildConfig.DEBUG) {
            return;
        }
        Log.d(tag, data);


    }
}
