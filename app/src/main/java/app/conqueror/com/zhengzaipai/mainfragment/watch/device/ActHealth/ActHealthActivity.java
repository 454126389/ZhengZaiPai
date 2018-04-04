package app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActHealth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import app.conqueror.com.zhengzaipai.App;
import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.base.BaseActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.DeviceFragment;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.LoveBons.LoveBonsContract;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.LoveBons.LoveBonsModel;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.LoveBons.LoveBonsPresenter;
import app.conqueror.com.zhengzaipai.util.SpUtil;
import app.conqueror.com.zhengzaipai.view.viewpagerindicator.TabPageIndicator;
import butterknife.Bind;
import butterknife.ButterKnife;

public class ActHealthActivity extends BaseActivity<ActHealthPresenter, ActHealthModel> implements ActHealthContract.View, View.OnClickListener {

    private  String[] CONTENT ;
    @Bind(R.id.title_bar_status)
    View titleBarStatus;
    @Bind(R.id.lib_btn_back)
    ImageButton libBtnBack;
    @Bind(R.id.lib_tv_title)
    TextView libTvTitle;
    @Bind(R.id.title_right_btn)
    Button titleRightBtn;
    @Bind(R.id.title_right_iv)
    ImageView titleRightIv;
    @Bind(R.id.lib_tv_right)
    TextView libTvRight;
    @Bind(R.id.lib_bottom_line)
    View libBottomLine;
    @Bind(R.id.indicator)
    TabPageIndicator indicator;
    @Bind(R.id.pager)
    ViewPager pager;
    private List<Fragment> list = new ArrayList<Fragment>();


    TextView mtvData;

    @Override
    public int getLayoutId() {
        return R.layout.device_act_health;
    }


    @Override
    public void initView() {

        CONTENT = new String[]{getString(R.string.step), getString(R.string.sleep)};

        libBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        FragmentPagerAdapter adapter = new ActHealthActivity.GoogleMusicAdapter(getSupportFragmentManager());

        ViewPager pager = (ViewPager)findViewById(R.id.pager);
        pager.setAdapter(adapter);

        TabPageIndicator indicator = (TabPageIndicator)findViewById(R.id.indicator);
        indicator.setViewPager(pager);


    }


    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel, this);
    }


    @Override
    public void showMsg(String msg) {
        App.showText(msg);
    }

    @Override
    public void initDialog() {

    }

    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public TextView getTvdata() {
        return mtvData;
    }

    @Override
    public void sendAction(Intent intent) {
        sendOrderedBroadcast(intent, null);   //有序广播发送
    }


    class GoogleMusicAdapter extends FragmentPagerAdapter {
        public GoogleMusicAdapter(FragmentManager fm) {
            super(fm);


            StepFragment stepFragment=new StepFragment();
            SleepFragment sleepFragment=new SleepFragment();
            stepFragment.setOnButtonClick(new StepFragment.OnButtonClick() {
                @Override
                public void onClick(int position, String id, String content,TextView tvData) {
                    mtvData=tvData;
                    switch (position)
                    {
                        case 0:
                            mPresenter.getWlakByIdAndTime(id,content);
                            break;
                        case 1:
                            mPresenter.getSleepByIdAndTime(id,content);
                            break;
                    }

                }
            });

            sleepFragment.setOnButtonClick(new SleepFragment.OnButtonClick() {
                @Override
                public void onClick(int position, String id, String content,TextView tvData) {
                    mtvData=tvData;
                    switch (position)
                    {
                        case 0:
                            mPresenter.getWlakByIdAndTime(id,content);
                            break;
                        case 1:
                            mPresenter.getSleepByIdAndTime(id,content);
                            break;
                    }

                }
            });


            list.add(stepFragment);
            list.add(sleepFragment);

        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return CONTENT[position % CONTENT.length].toUpperCase();
        }

        @Override
        public int getCount() {
            return CONTENT.length;
        }
    }

}
