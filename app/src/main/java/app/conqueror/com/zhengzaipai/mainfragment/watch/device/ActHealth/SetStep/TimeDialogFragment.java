package app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActHealth.SetStep;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import app.conqueror.com.zhengzaipai.App;
import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.util.SpUtil;
import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.picker.TimePicker;
import cn.qqtheme.framework.util.ConvertUtils;

/**
 * Created by Administrator on 2017/10/23/023.
 */

public class TimeDialogFragment extends DialogFragment implements View.OnClickListener
{
    String name;
    String id;



    public interface DialogFragmentDataImp{//定义一个与Activity通信的接口，使用该DialogFragment的Activity须实现该接口
        void showMessage(String message);
    }

    //1、定义接口
    public interface OnButtonClick {
        public void onClick(String time);
    }
    private TimeDialogFragment.OnButtonClick onButtonClick;//2、定义接口成员变量

    //定义接口变量的get方法
    public TimeDialogFragment.OnButtonClick getOnButtonClick() {
        return onButtonClick;
    }
    //定义接口变量的set方法
    public void setOnButtonClick(TimeDialogFragment.OnButtonClick onButtonClick) {
        this.onButtonClick = onButtonClick;
    }

    public static TimeDialogFragment newInstance(ArrayList<String> wlakTime){
        //创建一个带有参数的Fragment实例
        TimeDialogFragment fragment = new TimeDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("wlakTime",wlakTime);
        fragment.setArguments(bundle);//把参数传递给该DialogFragment
        return fragment;
    }

    Button step_time_slot_begin_01,step_time_slot_end_01;
    Button step_time_slot_begin_02,step_time_slot_end_02;
    Button step_time_slot_begin_03,step_time_slot_end_03;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View customView = LayoutInflater.from(getActivity()).inflate(
                R.layout.device_step_time_slot, null);

        ArrayList<String> wlakTime = new ArrayList<String>();

        wlakTime = getArguments().getStringArrayList("wlakTime");

         step_time_slot_begin_01= (Button) customView.findViewById(R.id.step_time_slot_begin_01);
         step_time_slot_end_01= (Button) customView.findViewById(R.id.step_time_slot_end_01);
         step_time_slot_begin_02= (Button) customView.findViewById(R.id.step_time_slot_begin_02);
         step_time_slot_end_02= (Button) customView.findViewById(R.id.step_time_slot_end_02);
         step_time_slot_begin_03= (Button) customView.findViewById(R.id.step_time_slot_begin_03);
         step_time_slot_end_03= (Button) customView.findViewById(R.id.step_time_slot_end_03);

        step_time_slot_begin_01.setText(wlakTime.get(0).split("-")[0]);
        step_time_slot_end_01.setText(wlakTime.get(0).split("-")[1]);
        step_time_slot_begin_02.setText(wlakTime.get(1).split("-")[0]);
        step_time_slot_end_02.setText(wlakTime.get(1).split("-")[1]);
        step_time_slot_begin_03.setText(wlakTime.get(2).split("-")[0]);
        step_time_slot_end_03.setText(wlakTime.get(2).split("-")[1]);


        step_time_slot_begin_01.setOnClickListener(this);
        step_time_slot_end_01.setOnClickListener(this);
        step_time_slot_begin_02.setOnClickListener(this);
        step_time_slot_end_02.setOnClickListener(this);
        step_time_slot_begin_03.setOnClickListener(this);
        step_time_slot_end_03.setOnClickListener(this);

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
                String time=step_time_slot_begin_01.getText().toString()+"-"+step_time_slot_end_01.getText().toString()+","
                        +step_time_slot_begin_02.getText().toString()+"-"+step_time_slot_end_02.getText().toString()+","
                        +step_time_slot_begin_03.getText().toString()+"-"+step_time_slot_end_03.getText().toString();
                getOnButtonClick().onClick(time);
            }
        });
        picker.show();

    }



}