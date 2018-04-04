package app.conqueror.com.zhengzaipai;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.support.multidex.MultiDex;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;
import android.widget.Toast;


import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.Map;

import app.conqueror.com.zhengzaipai.util.DeviceUuidFactory;
import app.conqueror.com.zhengzaipai.util.SpUtil;
import app.conqueror.com.zhengzaipai.wechat.Constants;




/**
 * Created by baixiaokang on 16/4/23.
 */
public class App extends Application {
    private static App mApp;
    private static Context context;
    // 用于存放倒计时时间
    public static Map<String, Long> map;

    @Override
    public void onCreate() {
        super.onCreate();


        mApp = this;
        SpUtil.init(this);
        context = getApplicationContext();
        DeviceUuidFactory.init(context);





//        CrashReport.initCrashReport(getApplicationContext(), "5543cb2808", false);
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setAppChannel("myChannel");  //设置渠道
        strategy.setAppVersion("1.1.0");      //App的版本
        strategy.setAppPackageName("app.conqueror.com.zhengzaipai");  //App的包名
//        CrashReport.initCrashReport(context, "5543cb2808", true, strategy);

        Bugly.init(getApplicationContext(), "5543cb2808", false,strategy);

        XGPushConfig.enableDebug(this,true);
        XGPushManager.registerPush(context);


        final IWXAPI api = WXAPIFactory.createWXAPI(context, null);

        // 将该app注册到微信
        api.registerApp(Constants.APP_ID);


//        // 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
//        SDKInitializer.initialize(this);
    }

    public static Context getAppContext() {
        return mApp;
    }


    public static void showText(String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void showMessage(String message) {
        Looper.prepare();
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        Looper.loop();
    }



    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);

    }


}
