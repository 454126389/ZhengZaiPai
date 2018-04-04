package app.conqueror.com.zhengzaipai.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.util.DialogUtil;
import app.conqueror.com.zhengzaipai.util.SpUtil;
import app.conqueror.com.zhengzaipai.util.TUtil;
import app.conqueror.com.zhengzaipai.view.layout.SwipeBackLayout;
import app.conqueror.com.zhengzaipai.view.pop.PopupMenuUtil;
import butterknife.ButterKnife;

import static app.conqueror.com.zhengzaipai.util.DialogUtil.hideDialog;

/**
 * Created by Administrator on 2016/4/5.
 */
public abstract class BaseActivity<T extends BasePresenter, E extends BaseModel> extends AppCompatActivity {
    public boolean isNight;
    public T mPresenter;
    public E mModel;
    public Context mContext;

    private SwipeBackLayout swipeBackLayout;
    private ImageView ivShadow;

    protected TextView bar_left;
    private TextView bar_title;
    protected TextView bar_right;

    protected Bundle savedInstanceState;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        isNight = SpUtil.isNight();
//        setTheme(isNight ? R.style.AppThemeNight : R.style.AppThemeDay);

//        ActionBar actionBar = getSupportActionBar();;
//
//        actionBar.setDisplayShowHomeEnabled(true);
//        actionBar.setDisplayUseLogoEnabled(true);
//
////        actionBar.setDisplayHomeAsUpEnabled(true);
//
//        //设置显示自定义的View，如果不设置这个属性，自定义的View不会起作用
//        actionBar.setDisplayShowCustomEnabled(true);
//
//        View view = LayoutInflater.from(this).inflate(R.layout.custom_action_bar, null);
//        bar_left = (TextView) view.findViewById(R.id.bar_left);
//        bar_title = (TextView) view.findViewById(R.id.bar_title);
//        bar_right = (TextView) view.findViewById(R.id.bar_right);
//
//        actionBar.setCustomView(view);
////        bar_left.setText("返回");
////        bar_left.setTextColor(Color.WHITE);
//        bar_title.setText(getString(R.string.app_name));
//        bar_title.setTextColor(Color.WHITE);
////        bar_right.setText("搜索");
////        bar_right.setTextColor(Color.WHITE);



        this.setContentView(this.getLayoutId());
        ButterKnife.bind(this);
        mContext = this;
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        this.initView();
        this.initPresenter();

        this.savedInstanceState=savedInstanceState;


    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    protected void onResume() {
//        initBaseDialog();
        super.onResume();
        if (isNight != SpUtil.isNight()) {
            reload();
        }
    }

    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }

    private View getContainer() {
        RelativeLayout container = new RelativeLayout(this);
        swipeBackLayout = new SwipeBackLayout(this);
        swipeBackLayout.setDragEdge(SwipeBackLayout.DragEdge.LEFT);
        ivShadow = new ImageView(this);
        ivShadow.setBackgroundColor(getResources().getColor(R.color.theme_black_7f));
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        container.addView(ivShadow, params);
        container.addView(swipeBackLayout);
        swipeBackLayout.setOnSwipeBackListener((fa, fs) -> ivShadow.setAlpha(1 - fs));
        return container;
    }


    public abstract int getLayoutId();

    public abstract void initView();

    /**
     * 简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
     */
    public abstract void initPresenter();


    public void initBaseDialog() {
        DialogUtil.initDialog(this);
    }

    public void showBaseDialog() {
        DialogUtil.showDialog();
    }


    public void hideBaseDialog() {
        hideDialog();
    }


 /*   @Override
    public void onBackPressed() {
        // 当popupWindow 正在展示的时候 按下返回键 关闭popupWindow 否则关闭activity
        if (PopupMenuUtil.getInstance()._isShowing()) {
            PopupMenuUtil.getInstance()._rlClickAction();
        } else {
            super.onBackPressed();
        }
    }*/

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {

            if (PopupMenuUtil.getInstance()._isShowing()) {
                PopupMenuUtil.getInstance()._rlClickAction();
            } else {
                super.onBackPressed();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
