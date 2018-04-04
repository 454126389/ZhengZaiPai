package app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActHealth.SetSleep;

import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;

import java.util.Calendar;

import app.conqueror.com.zhengzaipai.App;
import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.base.BaseActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActHealth.SetStep.TimeDialogFragment;
import app.conqueror.com.zhengzaipai.util.SpUtil;
import butterknife.Bind;
import butterknife.ButterKnife;
import cn.qqtheme.framework.picker.TimePicker;

public class SetSleepActivity extends BaseActivity<SetSleepPresenter, SetSleepModel> implements SetSleepContract.View, View.OnClickListener {


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
    @Bind(R.id.googleProgress)
    ProgressBar googleProgress;
    @Bind(R.id.sleep_cal_tv_time_slot)
    TextView sleepCalTvTimeSlot;
    @Bind(R.id.layout_sleep_cal_time_slot)
    RelativeLayout layoutSleepCalTimeSlot;
    @Bind(R.id.layout_time)
    LinearLayout layoutTime;
    @Bind(R.id.sleep_cal_cb_switch)
    SwitchCompat sleepCalCbSwitch;
    @Bind(R.id.layout_switch)
    RelativeLayout layoutSwitch;
    @Bind(R.id.swipe_target)
    ScrollView swipeTarget;
    @Bind(R.id.refresh)
    SwipeToLoadLayout refreshLayout;

    @Override
    public int getLayoutId() {
        return R.layout.device_act_sleep_cal;
    }


    @Override
    public void initView() {
        libBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        String sleepSet=SpUtil.getWatchUserList().get(SpUtil.getChoise()).sleep;
        if(sleepSet==null)
        {
            sleepCalTvTimeSlot.setText("00:00-00:00");
            sleepCalCbSwitch.setChecked(false);
        }else
        {
            sleepCalTvTimeSlot.setText(sleepSet.split(",")[0]);
            if(Integer.parseInt(sleepSet.split(",")[1])==0)
                sleepCalCbSwitch.setChecked(false);
            if(Integer.parseInt(sleepSet.split(",")[1])==1)
                sleepCalCbSwitch.setChecked(true);
        }

        sleepCalCbSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                    mPresenter.sleepTime(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id,sleepCalTvTimeSlot.getText().toString()+",1");
                else
                    mPresenter.sleepTime(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id,sleepCalTvTimeSlot.getText().toString()+",0");
            }
        });

        layoutSleepCalTimeSlot.setOnClickListener(this);



        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
//                for (int i = 0; i < 20; i++) {
//                    list.add(0,"刷新获得的数据");
//                }
//                myAdapter.notifyDataSetChanged();
                //设置下拉刷新结束
                refreshLayout.setRefreshing(false);
            }
        });


    }


    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel, this);
    }


    @Override
    public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.layout_sleep_cal_time_slot:

                    String sleep=SpUtil.getWatchUserList().get(SpUtil.getChoise()).sleep;

                    SleepTimeDialogFragment dialogFragment =SleepTimeDialogFragment.newInstance(sleep);

                    dialogFragment.setOnButtonClick(new SleepTimeDialogFragment.OnButtonClick() {
                        @Override
                        public void onClick(String time) {
                            sleepCalTvTimeSlot.setText(time.split(",")[0]);
//                            if(time.equals("00:00-00:00"))
//                                time=time+",0";
//                            else
//                                time=time+",1";
                            if(sleepCalCbSwitch.isChecked())
                                time=time+",1";
                            else
                                time=time+",0";
                            mPresenter.sleepTime(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id,time);
                        }
                    });

                    dialogFragment.show(getFragmentManager(), "AlertDialog");
                    break;

            }
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
    public void suc() {
        App.showText(getString(R.string.tip_suc));
    }

    @Override
    public void fail() {
        App.showText(getString(R.string.tip_fail));
    }
}
