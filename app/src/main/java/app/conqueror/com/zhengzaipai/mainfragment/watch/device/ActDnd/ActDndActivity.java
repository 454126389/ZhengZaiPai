package app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActDnd;

import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import app.conqueror.com.zhengzaipai.App;
import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.base.BaseActivity;
import app.conqueror.com.zhengzaipai.util.DialogUtil;
import app.conqueror.com.zhengzaipai.util.SpUtil;
import butterknife.Bind;
import butterknife.ButterKnife;
import cn.qqtheme.framework.picker.TimePicker;

public class ActDndActivity extends BaseActivity<ActDndPresenter, ActDndModel> implements ActDndContract.View, View.OnClickListener {


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
    @Bind(R.id.tip)
    TextView tip;
    @Bind(R.id.iv_dnd_time1)
    ImageView ivDndTime1;
    @Bind(R.id.begin1)
    TextView begin1;
    @Bind(R.id.v_dnd1)
    ImageView vDnd1;
    @Bind(R.id.end1)
    TextView end1;
    @Bind(R.id.switch_compat_1)
    SwitchCompat switchCompat1;
    @Bind(R.id.ll_dnd1)
    LinearLayout llDnd1;
    @Bind(R.id.iv_dnd_time2)
    ImageView ivDndTime2;
    @Bind(R.id.begin2)
    TextView begin2;
    @Bind(R.id.v_dnd2)
    ImageView vDnd2;
    @Bind(R.id.end2)
    TextView end2;
    @Bind(R.id.switch_compat_2)
    SwitchCompat switchCompat2;
    @Bind(R.id.ll_dnd2)
    LinearLayout llDnd2;
    @Bind(R.id.iv_dnd_time3)
    ImageView ivDndTime3;
    @Bind(R.id.begin3)
    TextView begin3;
    @Bind(R.id.v_dnd3)
    ImageView vDnd3;
    @Bind(R.id.end3)
    TextView end3;
    @Bind(R.id.switch_compat_3)
    SwitchCompat switchCompat3;
    @Bind(R.id.ll_dnd3)
    LinearLayout llDnd3;
    @Bind(R.id.tip1)
    TextView tip1;
    @Bind(R.id.tip2)
    TextView tip2;

    @Override
    public int getLayoutId() {
        return R.layout.device_act_dnd;
    }

    @Override
    public void initView() {

        libBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        titleRightBtn.setVisibility(View.VISIBLE);
        titleRightBtn.setText(getString(R.string.btn_sure));
        titleRightBtn.setOnClickListener(this);

        begin1.setOnClickListener(this);
        begin2.setOnClickListener(this);
        begin3.setOnClickListener(this);
        end1.setOnClickListener(this);
        end2.setOnClickListener(this);
        end3.setOnClickListener(this);

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
        switch (view.getId()) {
            case R.id.title_right_btn:
                List<String> temp = new ArrayList<>();
                List<String> temp2 = new ArrayList<>();
                List<String> sw = new ArrayList<>();
                if (switchCompat1.isChecked())
                {
                    sw.add("1");
                    temp.add(begin1.getText() + "-" + end1.getText());
                }
                else
                {
                    sw.add("0");
                    temp.add("00:00" + "-" + "00:00");
                }
                if (switchCompat2.isChecked())
                {
                    sw.add("1");
                    temp.add(begin2.getText() + "-" + end2.getText());
                }
                else
                {
                    sw.add("0");
                    temp.add("00:00" + "-" + "00:00");
                }
                if (switchCompat3.isChecked())
                {
                    sw.add("1");
                    temp.add(begin3.getText() + "-" + end3.getText());
                }
                else
                {
                    sw.add("0");
                    temp.add("00:00" + "-" + "00:00");
                }

                SpUtil.setDissw(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id,sw);

                temp2.add(begin1.getText() + "-" + end1.getText());
                temp2.add(begin2.getText() + "-" + end2.getText());
                temp2.add(begin3.getText() + "-" + end3.getText());
                SpUtil.changeWatchUserListDisable(SpUtil.getChoise(),temp2);



                if (temp.size() > 0)
                    mPresenter.silenceTime(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id, (temp.toString()).substring(1, temp.toString().length() - 1).replaceAll(" ", ""), temp);
                break;
            case R.id.begin1:
                onTimePicker(begin1);
                break;
            case R.id.begin2:
                onTimePicker(begin2);
                break;
            case R.id.begin3:
                onTimePicker(begin3);
                break;
            case R.id.end1:
                onTimePicker(end1);
                break;
            case R.id.end2:
                onTimePicker(end2);
                break;
            case R.id.end3:
                onTimePicker(end3);
                break;
        }
    }


    public void onTimePicker(TextView tv) {
        TimePicker picker = new TimePicker(this, TimePicker.HOUR_24);
        picker.setUseWeight(true);
        picker.setCycleDisable(false);
        picker.setRangeStart(0, 0);//00:00
        picker.setRangeEnd(23, 59);//23:59
        int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int currentMinute = Calendar.getInstance().get(Calendar.MINUTE);

        picker.setSelectedItem(currentHour, currentMinute);
        picker.setTopLineVisible(false);
        picker.setOnTimePickListener(new TimePicker.OnTimePickListener() {
            @Override
            public void onTimePicked(String hour, String minute) {
                tv.setText(hour + ":" + minute);

            }
        });
        picker.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<String> swlist=SpUtil.getDissw(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id);
        if(swlist==null)
        {
            swlist=new ArrayList<>();
            swlist.add("0");
            swlist.add("0");
            swlist.add("0");
        }

        List<String> disableList = SpUtil.getWatchUserList().get(SpUtil.getChoise()).disableList;
        if (disableList != null) {
            for (int i = 0; i < disableList.size(); i++) {
                if (i == 0) {
                    ivDndTime1.setImageResource(R.mipmap.device_dnd_time_checked);
                    begin1.setText(disableList.get(i).split("-")[0]);
                    end1.setText(disableList.get(i).split("-")[1]);


//                    if (disableList.get(i).split("-")[0].equals("00:00") && disableList.get(i).split("-")[1].equals("00:00"))
                    if(swlist.get(i).equals("0"))
                        switchCompat1.setChecked(false);
                    else
                        switchCompat1.setChecked(true);


                } else if (i == 1) {
                    ivDndTime2.setImageResource(R.mipmap.device_dnd_time_checked);
                    begin2.setText(disableList.get(i).split("-")[0]);
                    end2.setText(disableList.get(i).split("-")[1]);
//                    if (disableList.get(i).split("-")[0].equals("00:00") && disableList.get(i).split("-")[1].equals("00:00"))
                    if(swlist.get(i).equals("0"))
                        switchCompat2.setChecked(false);

                    else
                        switchCompat2.setChecked(true);
                } else if (i == 2) {
                    ivDndTime3.setImageResource(R.mipmap.device_dnd_time_checked);
                    begin3.setText(disableList.get(i).split("-")[0]);
                    end3.setText(disableList.get(i).split("-")[1]);
//                    if (disableList.get(i).split("-")[0].equals("00:00") && disableList.get(i).split("-")[1].equals("00:00"))
                    if(swlist.get(i).equals("0"))
                        switchCompat3.setChecked(false);
                    else
                        switchCompat3.setChecked(true);
                }
            }
        }
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
