package app.conqueror.com.zhengzaipai.base;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.view.pop.PopupMenuUtil;
import app.conqueror.com.zhengzaipai.view.pop.popbase.SlideFromBottomPopup;
import app.conqueror.com.zhengzaipai.view.pop.popmore.MoreWindow;
import butterknife.Bind;
import butterknife.ButterKnife;

public class BaseTabActivity extends TabActivity {

    @Bind(android.R.id.tabcontent)
    FrameLayout tabcontent;
    @Bind(android.R.id.tabs)
    TabWidget tabs;
    @Bind(R.id.l1)
    LinearLayout l1;
    @Bind(R.id.tab_home_click)
    ImageView tabHomeClick;
    @Bind(R.id.frame_onway)
    FrameLayout frameOnway;
    @Bind(R.id.tab_bang)
    ImageView tabBang;
    @Bind(R.id.frame_mydevice)
    FrameLayout frameMydevice;
    @Bind(R.id.tab_find)
    ImageView tabFind;
    @Bind(R.id.frame_zzwarn)
    FrameLayout frameZzwarn;
    @Bind(R.id.tab_persion)
    ImageView tabPersion;
    @Bind(R.id.frame_usercenter)
    FrameLayout frameUsercenter;
    @Bind(R.id.frame_popmenu)
    FrameLayout framePopmenu;
    @Bind(android.R.id.tabhost)
    TabHost tabHost;
    @Bind(R.id.tv_tab1)
    TextView tvTab1;
    @Bind(R.id.tv_tab2)
    TextView tvTab2;
    @Bind(R.id.tv_tab3)
    TextView tvTab3;
    @Bind(R.id.tv_tab4)
    TextView tvTab4;

    private List<Class<?>> ClassList;
    private List<Integer> resIdNormaldList;
    private List<Integer> resIdClickdList;
    private Integer[] tabTitle;

    MoreWindow mMoreWindow;
    SlideFromBottomPopup basePopup;
    private long exitTime = 0;


    public void SetData(List<Class<?>> ClassList, List<Integer> resIdNormaldList, List<Integer> resIdClickdList,Integer[] tabTitle) {
        this.ClassList = ClassList;
        this.resIdNormaldList = resIdNormaldList;
        this.resIdClickdList = resIdClickdList;
        this.tabTitle = tabTitle;
        initView(ClassList);
        basePopup=new SlideFromBottomPopup(BaseTabActivity.this);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_tab);
        ButterKnife.bind(this);

    }

    private void initView(List<Class<?>> ClassList) {

        tabHost = getTabHost();
        for (int i = 0; i < ClassList.size(); i++) {
            if(ClassList.get(i)!=null)
            {
                Intent intent = new Intent();
                intent.setClass(BaseTabActivity.this, ClassList.get(i));

                tabHost.addTab(tabHost.newTabSpec(String.valueOf(i + 1)).setIndicator(String.valueOf(i + 1))
                        .setContent(intent));
            }
        }

        tvTab1.setText(getString(tabTitle[0]));
        tvTab2.setText(getString(tabTitle[1]));
        tvTab3.setText(getString(tabTitle[2]));
        tvTab4.setText(getString(tabTitle[3]));


        frameOnway.setOnClickListener(l);
        frameMydevice.setOnClickListener(l);
        frameZzwarn.setOnClickListener(l);
        frameUsercenter.setOnClickListener(l);
        framePopmenu.setOnClickListener(l);

        frameOnway.callOnClick();

    }


    OnClickListener l = new OnClickListener() {

        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            if (arg0 == frameOnway) {
                tabHost.setCurrentTabByTag("1");

                tvTab1.setTextColor(getResources().getColor(R.color.colorPrimary));
                tvTab2.setTextColor(getResources().getColor(R.color.popup_bg));
                tvTab3.setTextColor(getResources().getColor(R.color.popup_bg));
                tvTab4.setTextColor(getResources().getColor(R.color.popup_bg));

                tabHomeClick.setImageResource(resIdClickdList.get(0));
                tabBang.setImageResource(resIdNormaldList.get(1));
                tabFind.setImageResource(resIdNormaldList.get(2));
                tabPersion.setImageResource(resIdNormaldList.get(3));

            } else if (arg0 == frameMydevice) {
                tabHost.setCurrentTabByTag("2");

                tvTab1.setTextColor(getResources().getColor(R.color.popup_bg));
                tvTab2.setTextColor(getResources().getColor(R.color.colorPrimary));
                tvTab3.setTextColor(getResources().getColor(R.color.popup_bg));
                tvTab4.setTextColor(getResources().getColor(R.color.popup_bg));

                tabHomeClick.setImageResource(resIdNormaldList.get(0));
                tabBang.setImageResource(resIdClickdList.get(1));
                tabFind.setImageResource(resIdNormaldList.get(2));
                tabPersion.setImageResource(resIdNormaldList.get(3));

            } else if (arg0 == frameZzwarn) {
                tabHost.setCurrentTabByTag("3");

                tvTab1.setTextColor(getResources().getColor(R.color.popup_bg));
                tvTab2.setTextColor(getResources().getColor(R.color.popup_bg));
                tvTab3.setTextColor(getResources().getColor(R.color.colorPrimary));
                tvTab4.setTextColor(getResources().getColor(R.color.popup_bg));

                tabHomeClick.setImageResource(resIdNormaldList.get(0));
                tabBang.setImageResource(resIdNormaldList.get(1));
                tabFind.setImageResource(resIdClickdList.get(2));
                tabPersion.setImageResource(resIdNormaldList.get(3));

            } else if (arg0 == frameUsercenter) {
                if(ClassList.get(3)!=null)
                {

                    tvTab1.setTextColor(getResources().getColor(R.color.popup_bg));
                    tvTab2.setTextColor(getResources().getColor(R.color.popup_bg));
                    tvTab3.setTextColor(getResources().getColor(R.color.popup_bg));
                    tvTab4.setTextColor(getResources().getColor(R.color.colorPrimary));

                    tabHost.setCurrentTabByTag("4");
                    tabHomeClick.setImageResource(resIdNormaldList.get(0));
                    tabBang.setImageResource(resIdNormaldList.get(1));
                    tabFind.setImageResource(resIdNormaldList.get(2));
                    tabPersion.setImageResource(resIdClickdList.get(3));
                }
                else
                {
                    new AlertDialog.Builder(BaseTabActivity.this)
                            .setTitle("远程操作")
                            .setMessage("设备远程关机")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(BaseTabActivity.this, "已请求", Toast.LENGTH_SHORT).show();
                                }
                            }).setNegativeButton("取消",null)
                            .show();
                }


            }else if (arg0 == framePopmenu) {


                PopupMenuUtil.getInstance()._show(BaseTabActivity.this, findViewById(R.id.frame_popmenu));

//                basePopup.showPopupWindow();
//                if (null == mMoreWindow) {
//                    mMoreWindow = new MoreWindow(BaseTabActivity.this);
//                    mMoreWindow.init();
//                }
//
//                mMoreWindow.showMoreWindow(arg0,100);
            }
        }

    };






}