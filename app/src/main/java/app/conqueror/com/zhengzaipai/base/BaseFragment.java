package app.conqueror.com.zhengzaipai.base;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
public abstract class BaseFragment<T extends BasePresenter, E extends BaseModel> extends Fragment {
    public boolean isNight;
    public T mPresenter;
    public E mModel;
    public Context mContext;

    private SwipeBackLayout swipeBackLayout;
    private ImageView ivShadow;

    protected TextView bar_left;
    private TextView bar_title;
    protected TextView bar_right;
/*    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isNight = SpUtil.isNight();



        this.setContentView(this.getLayoutId());
        ButterKnife.bind(getActivity());
        mContext = getActivity();
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        this.initView();
        this.initPresenter();


    }*/

    protected Activity mActivity;

    /**
     * 获得全局的，防止使用getActivity()为空
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (Activity)context;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = activity;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null)
            mPresenter.onDestroy();
        ButterKnife.unbind(this);
    }




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container
            , Bundle savedInstanceState) {




        View view = LayoutInflater.from(getActivity())
                .inflate(getLayoutId(), container, false);
        initView(view, savedInstanceState);


        ButterKnife.bind(mActivity);
        mContext = mActivity;
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        this.initPresenter();

        return view;
    }




    /**
     * 该抽象方法就是 初始化view
     * @param view
     * @param savedInstanceState
     */
    protected abstract void initView(View view, Bundle savedInstanceState);



    /**
     * 简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
     */
    public abstract void initPresenter();


    public void initBaseDialog() {
        DialogUtil.initDialog(getActivity());
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

    /**
     * 该抽象方法就是 onCreateView中需要的layoutID
     * @return
     */
    protected abstract int getLayoutId();

}
