package app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActHealth.SetStep;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SwitchCompat;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;

import java.util.ArrayList;
import java.util.List;

import app.conqueror.com.zhengzaipai.App;
import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.base.BaseActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.Wlak;
import app.conqueror.com.zhengzaipai.mainfragment.watch.home.phone.AlertDialogFragment;
import app.conqueror.com.zhengzaipai.util.SpUtil;
import butterknife.Bind;
import butterknife.ButterKnife;

public class SetStepActivity extends BaseActivity<SetStepPresenter, SetStepModel> implements SetStepContract.View, View.OnClickListener {


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
    @Bind(R.id.step_cal_tv_time_slot)
    TextView stepCalTvTimeSlot;
    @Bind(R.id.layout_step_time)
    LinearLayout layoutStepTime;
    @Bind(R.id.step_cal_tv_step_length)
    TextView stepCalTvStepLength;
    @Bind(R.id.layout_step_length)
    LinearLayout layoutStepLength;
    @Bind(R.id.step_cal_tv_weight)
    TextView stepCalTvWeight;
    @Bind(R.id.layout_step_weight)
    LinearLayout layoutStepWeight;
    @Bind(R.id.step_cal_tv_target)
    TextView stepCalTvTarget;
    @Bind(R.id.layout_step_target)
    LinearLayout layoutStepTarget;
    @Bind(R.id.step_cal_cb_switch)
    SwitchCompat stepCalCbSwitch;
    @Bind(R.id.layout_step_cb)
    RelativeLayout layoutStepCb;
    @Bind(R.id.swipe_target)
    ScrollView swipeTarget;
    @Bind(R.id.refresh)
    SwipeToLoadLayout refreshLayout;

    Wlak wlakSet;

    @Override
    public int getLayoutId() {
        return R.layout.device_act_step_cal;
    }


    @Override
    public void initView() {
        libBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

//        "wlak": {
//            "wlakTime": [
//            "00:00-00:00",
//                    "00:00-00:00",
//                    "00:00-00:00"
//                ],
//            "pace": 35,
//                    "weight": 20,
//                    "onOff": false
//        },

        wlakSet= SpUtil.getWatchUserList().get(SpUtil.getChoise()).wlak;
        if(wlakSet==null)
        {
            stepCalTvTimeSlot.setText("00:00-00:00");
            stepCalTvStepLength.setText("35");
            stepCalTvWeight.setText("20");
//            stepCalTvTarget.setText("35");
            stepCalCbSwitch.setChecked(false);
        }else
        {
            if(wlakSet.wlakTimeList==null)
                stepCalTvTimeSlot.setText("00:00-00:00");
            else
                stepCalTvTimeSlot.setText(wlakSet.wlakTimeList.get(0));
            if(wlakSet.pace==null)
                stepCalTvStepLength.setText("35");
            else
                stepCalTvStepLength.setText(wlakSet.pace);

            if(wlakSet.weight==null)
                stepCalTvWeight.setText("20");
            else
                stepCalTvWeight.setText(wlakSet.weight);

            if(wlakSet.onOff==null)
                stepCalCbSwitch.setChecked(false);
            else {
                if(wlakSet.onOff.equals("false"))
                    stepCalCbSwitch.setChecked(false);
                else  if(wlakSet.onOff.equals("true"))
                    stepCalCbSwitch.setChecked(true);
            }
        }

        stepCalCbSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                    mPresenter.pedo(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id,"1");
                else
                    mPresenter.pedo(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id,"0");
            }
        });


        layoutStepTime.setOnClickListener(this);
        layoutStepLength.setOnClickListener(this);
        layoutStepWeight.setOnClickListener(this);


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
            case R.id.layout_step_time:
                ArrayList<String> wlakTime=new ArrayList<String>();
                wlakSet= SpUtil.getWatchUserList().get(SpUtil.getChoise()).wlak;
                if(wlakSet==null||wlakSet.wlakTimeList==null)
                {
                    wlakTime.add("00:00-00:00");
                    wlakTime.add("00:00-00:00");
                    wlakTime.add("00:00-00:00");
                }else
                {
                    wlakTime= (ArrayList<String>) wlakSet.getWlakTimeList();
                }

                TimeDialogFragment dialogFragment =TimeDialogFragment.newInstance(wlakTime);

                dialogFragment.setOnButtonClick(new TimeDialogFragment.OnButtonClick() {
                    @Override
                    public void onClick(String time) {
                        stepCalTvTimeSlot.setText(time.split(",")[0]);
                        mPresenter.walktime(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id,time);
                    }
                });

                dialogFragment.show(getFragmentManager(), "AlertDialog");

                break;

            case R.id.layout_step_length:
                final EditText inputServer_length = new EditText(this);
                inputServer_length.setInputType(InputType.TYPE_CLASS_NUMBER);
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setTitle(getString(R.string.tx_tip)).setIcon(android.R.drawable.ic_dialog_info).setView(inputServer_length)
                        .setNegativeButton(getString(R.string.btn_cancel), null);
                builder1.setPositiveButton(getString(R.string.btn_sure), new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        if(inputServer_length.getText().toString().equals(""))
                            App.showText(getString(R.string.tx_no_null));
                        else
                        {
                            stepCalTvStepLength.setText(inputServer_length.getText().toString());
                            mPresenter.setp(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id,inputServer_length.getText().toString());
                        }
                    }
                });
                builder1.show();
                break;
            case R.id.layout_step_weight:
                final EditText inputServer_weight = new EditText(this);
                inputServer_weight.setInputType(InputType.TYPE_CLASS_NUMBER);
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                builder2.setTitle(getString(R.string.tx_tip)).setIcon(android.R.drawable.ic_dialog_info).setView(inputServer_weight)
                        .setNegativeButton(getString(R.string.btn_cancel), null);
                builder2.setPositiveButton(getString(R.string.btn_sure), new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        if(inputServer_weight.getText().toString().equals(""))
                            App.showText(getString(R.string.tx_no_null));
                        else
                        {
                            stepCalTvWeight.setText(inputServer_weight.getText().toString());
                            mPresenter.weight(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id,inputServer_weight.getText().toString());

                        }

                    }
                });
                builder2.show();
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
    protected void onResume() {
        super.onResume();

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
