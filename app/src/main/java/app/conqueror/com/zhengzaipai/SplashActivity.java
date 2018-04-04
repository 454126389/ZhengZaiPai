package app.conqueror.com.zhengzaipai;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import java.io.File;
import java.util.Locale;

import app.conqueror.com.zhengzaipai.mainfragment.MainTabActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.login.LoginActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.login.LoginSelectActivity;
import app.conqueror.com.zhengzaipai.util.SpUtil;
import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by wwjun.wang on 2015/8/11.
 */
    public class SplashActivity extends Activity {


    @Bind(R.id.iv_start)
    ImageView iv_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //全屏代码
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash);
        ButterKnife.bind(this);
        initImage();



        Locale locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = LocaleList.getDefault().get(0);
        } else {
            locale = Locale.getDefault();
        }
        String language = locale.getLanguage() + "-" + locale.getCountry();
//        App.showText(language);
        SpUtil.setLanguage(language);


    }


    private void initImage() {
        File dir = getFilesDir();
        final File imgFile = new File(dir, "start.png");
        if (imgFile.exists()) {
            iv_start.setImageBitmap(BitmapFactory.decodeFile(imgFile.getAbsolutePath()));
        } else {
            iv_start.setImageResource(R.mipmap.start2);
        }


        if(SpUtil.getLanguage().equals("zh-TW"))
            iv_start.setImageResource(R.mipmap.start2);
        else
            iv_start.setImageResource(R.mipmap.star3);

        final ScaleAnimation scaleAnim = new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        scaleAnim.setFillAfter(true);
        scaleAnim.setDuration(3000);
        scaleAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (SpUtil.isNoFirst()) {
                    startMainActivity();
                } else {
                    SpUtil.setFirst(true);
                    startFirstActivity();
                }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        iv_start.startAnimation(scaleAnim);
    }

    private void startFirstActivity() {
        Intent intent = new Intent(SplashActivity.this, HowToUseActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out);
        finish();
    }

    //进入主程序
    private void startMainActivity() {
//        检测是否登录
        if(SpUtil.getIsAutoLogin())
        {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in,
                    android.R.anim.fade_out);
            finish();
        }else
        {
            Intent intent = new Intent(SplashActivity.this, LoginSelectActivity.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in,
                    android.R.anim.fade_out);
            finish();
        }
    }


//    private void startLogin() {
//        Intent intent = new Intent(SplashActivity.this, LoginSelectActivity.class);
//        startActivity(intent);
//        overridePendingTransition(android.R.anim.fade_in,
//                android.R.anim.fade_out);
//        finish();
//    }


}
