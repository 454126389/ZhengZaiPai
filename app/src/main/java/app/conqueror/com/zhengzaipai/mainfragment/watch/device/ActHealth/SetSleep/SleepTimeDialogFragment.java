package app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActHealth.SetSleep;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Calendar;

import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActHealth.SetStep.TimeDialogFragment;
import cn.qqtheme.framework.picker.TimePicker;

/**
 * Created by Administrator on 2017/10/23/023.
 */

public class SleepTimeDialogFragment extends DialogFragment implements View.OnClickListener
{




    public interface DialogFragmentDataImp{//定义一个与Activity通信的接口，使用该DialogFragment的Activity须实现该接口
        void showMessage(String message);
    }

    //1、定义接口
    public interface OnButtonClick {
        public void onClick(String time);
    }
    private SleepTimeDialogFragment.OnButtonClick onButtonClick;//2、定义接口成员变量

    //定义接口变量的get方法
    public SleepTimeDialogFragment.OnButtonClick getOnButtonClick() {
        return onButtonClick;
    }
    //定义接口变量的set方法
    public void setOnButtonClick(SleepTimeDialogFragment.OnButtonClick onButtonClick) {
        this.onButtonClick = onButtonClick;
    }

    public static SleepTimeDialogFragment newInstance(String sleep){
        //创建一个带有参数的Fragment实例
        SleepTimeDialogFragment fragment = new SleepTimeDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("sleep",sleep);
        fragment.setArguments(bundle);//把参数传递给该DialogFragment
        return fragment;
    }

    Button sleep_time_slot_begin_01,sleep_time_slot_end_01;



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View customView = LayoutInflater.from(getActivity()).inflate(
                R.layout.device_sleep_time_slot, null);


        String sleep = getArguments().getString("sleep");

        sleep_time_slot_begin_01= (Button) customView.findViewById(R.id.sleep_time_slot_begin_01);
        sleep_time_slot_end_01= (Button) customView.findViewById(R.id.sleep_time_slot_end_01);


        if(sleep==null)
            sleep="00:00-00:00,0";
        sleep_time_slot_begin_01.setText(sleep.split(",")[0].split("-")[0]);
        sleep_time_slot_end_01.setText(sleep.split(",")[0].split("-")[1]);



        sleep_time_slot_begin_01.setOnClickListener(this);
        sleep_time_slot_end_01.setOnClickListener(this);


        return new AlertDialog.Builder(getActivity()).setView(customView)
                .create();
    }

    @Override
    public void onClick(View view) {

        TimePicker picker = new TimePicker(getActivity(), TimePicker.HOUR_24);
        picker.setUseWeight(false);
        picker.setCycleDisable(false);
        picker.setRangeStart(0, 0);//00:00
        picker.setRangeEnd(23, 59);//23:59
        int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int currentMinute = Calendar.getInstance().get(Calendar.MINUTE);
        picker.setSelectedItem(currentHour, currentMinute);
        picker.setTopLineVisible(false);
//        picker.setTextPadding(ConvertUtils.toPx(getActivity(), 15));
        picker.setOnTimePickListener(new TimePicker.OnTimePickListener() {
            @Override
            public void onTimePicked(String hour, String minute) {
                ((Button) view).setText(hour + ":" + minute);

//                String time=8:10-9:30,10:10-11:30,12:10-13:30;
                String time=sleep_time_slot_begin_01.getText().toString()+"-"+sleep_time_slot_end_01.getText().toString();
                getOnButtonClick().onClick(time);
            }
        });
        picker.show();

    }



}