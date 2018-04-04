package app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActClock.ClockSet;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

import app.conqueror.com.zhengzaipai.App;
import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.base.BaseActivity;
import app.conqueror.com.zhengzaipai.util.SpUtil;
import butterknife.Bind;
import cn.qqtheme.framework.picker.TimePicker;

public class ClockSetActivity extends  BaseActivity<ClockSetPresenter, ClockSetModel> implements ClockSetContract.View, View.OnClickListener {


    @Bind(R.id.title_bar_status)
    View titleBarStatus;
    @Bind(R.id.lib_btn_back)
    ImageButton libBtnBack;
    @Bind(R.id.lib_tv_title)
    TextView libTvTitle;
    @Bind(R.id.btn_ok)
    TextView btnOk;
    @Bind(R.id.begin_time)
    TextView beginTime;
    @Bind(R.id.layout_begin)
    LinearLayout layoutBegin;
    @Bind(R.id.tv_repeat)
    TextView tvRepeat;
    @Bind(R.id.layout_repeat)
    LinearLayout layoutRepeat;

    AlertDialog alertDialog3;

    String[] timesp;
    boolean[] isCheck;

    String index;
    String clockmsg;
    String clock;
    @Override
    public int getLayoutId() {
        return R.layout.device_frag_clock_setting;
    }

    @Override
    public void initView() {




        Intent intent = getIntent();
         clock = intent.getStringExtra("clock");
        index = intent.getStringExtra("index");

        timesp= clock.split("-");
        beginTime.setText(timesp[0]);

        String type = null;
        if (timesp[2].equals("1")) {
            type = getString(R.string.tx_clock_type1);
            isCheck=new boolean[] { false, false, false, false, false,false,false };
        } else if (timesp[2].equals("2")) {
//            type = "每天";
            type = getString(R.string.tx_clock_type2);
            isCheck=new boolean[] { true, true, true, true, true,true,true };
        } else if (timesp[2].equals("3")) {
//            type = "自定义";
            type = getString(R.string.tx_clock_type3);
            isCheck=new boolean[7];
            char[] c=timesp[3].toCharArray();
            for (int i=0;i<c.length;i++)
                if(c[i]=='1')
                    isCheck[i]=true;
                else
                    isCheck[i]=false;

        }

        initMutilAlertDialog();

        tvRepeat.setText(type);

        btnOk.setOnClickListener(this);


        libBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        beginTime.setOnClickListener(this);
        layoutRepeat.setOnClickListener(this);
    }



    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel, this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.begin_time:
                onTimePicker(beginTime);
                break;
            case R.id.layout_repeat:
                alertDialog3.show();
                break;
            case R.id.btn_ok:
                if(null!=clockmsg)
            {
                List<String> clockList = SpUtil.getWatchUserList().get(SpUtil.getChoise()).clockList;
                clockList.set(Integer.parseInt(index), clockmsg);
                mPresenter.remind(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id, clockList);
            }


//                String msg =

//
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
                if(null==clockmsg)
                    clockmsg=clock;
                clockmsg=clockmsg.replace(clockmsg.split("-")[0],hour + ":" + minute);
            }
        });
        picker.show();
    }

    private void initMutilAlertDialog()
    {




        final String[] items = {getString(R.string.week01),getString(R.string.week02),getString(R.string.week03),getString(R.string.week04),getString(R.string.week05),getString(R.string.week06),getString(R.string.week07)};
        // 创建一个AlertDialog建造者
        AlertDialog.Builder alertDialogBuilder= new AlertDialog.Builder(this);
        // 设置标题
        alertDialogBuilder.setTitle(getString(R.string.repeat_time));
        // 参数介绍
        // 第一个参数：弹出框的信息集合，一般为字符串集合
        // 第二个参数：被默认选中的，一个布尔类型的数组
        // 第三个参数：勾选事件监听
        alertDialogBuilder.setMultiChoiceItems(items,isCheck, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {


                isCheck[which] = isChecked;
                // dialog：不常使用，弹出框接口
                // which：勾选或取消的是第几个
                // isChecked：是否勾选
//                if (isChecked) {
//
//                    // 选中
////                    Toast.makeText(ClockSetActivity.this, "选中"+items[which], Toast.LENGTH_SHORT).show();
//                }else {
//                    // 取消选中
////                    Toast.makeText(ClockSetActivity.this, "取消选中"+items[which], Toast.LENGTH_SHORT).show();
//                }

            }
        });
        alertDialogBuilder.setPositiveButton(getString(R.string.btn_sure), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                //TODO 业务逻辑代码
//                int num=0;
                StringBuffer dayNum = new StringBuffer();
                for (int i=0;i<isCheck.length;i++)
                    if(isCheck[i])
                        dayNum.append("1");
                    else
                        dayNum.append("0");
                if (dayNum.toString().equals("0000000"))
                {
                    tvRepeat.setText(getString(R.string.once));
                    clockmsg= beginTime.getText().toString() +"-"+"1"+"-"+"1";
                }
                else if (dayNum.toString().equals("1111111"))
                {
                    tvRepeat.setText(getString(R.string.every_day));
                    clockmsg= beginTime.getText().toString() +"-"+"1"+"-"+"2";
                }
                else
                {
                    tvRepeat.setText(getString(R.string.zdy));
                    clockmsg= beginTime.getText().toString() +"-"+"1"+"-"+"3"+"-"+dayNum.toString();
                }


                // 关闭提示框
                alertDialog3.dismiss();



            }
        });
        alertDialogBuilder.setNegativeButton(getString(R.string.btn_cancel), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                // TODO 业务逻辑代码

                // 关闭提示框
                alertDialog3.dismiss();
            }
        });
        alertDialog3 = alertDialogBuilder.create();

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
