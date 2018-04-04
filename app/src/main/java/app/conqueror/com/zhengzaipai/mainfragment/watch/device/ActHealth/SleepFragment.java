package app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActHealth;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;

import java.util.ArrayList;
import java.util.List;

import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActHealth.SetSleep.SetSleepActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.Health;
import app.conqueror.com.zhengzaipai.util.Actions;
import app.conqueror.com.zhengzaipai.util.DateUtils;
import app.conqueror.com.zhengzaipai.util.SpUtil;
import butterknife.Bind;
import butterknife.ButterKnife;


public class SleepFragment extends Fragment implements View.OnClickListener {

    @Bind(R.id.tv_date)
    TextView tvDate;
    @Bind(R.id.btn_pre)
    ImageButton btnPre;
    @Bind(R.id.btn_next)
    ImageButton btnNext;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.tv_data)
    TextView tvData;
    @Bind(R.id.bg)
    RelativeLayout bg;
    @Bind(R.id.btn_setting)
    ImageButton btnSetting;
    private String s;
    private SleepFragment.OnButtonClick onButtonClick;//2、定义接口成员变量
    Receiver mBroadcastReceiver;
    @Bind(R.id.line_chart1)
    LineChart lineChart1;
    LineChartManager lineChartManager1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.device_frag_sleep, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

    }

    public void initView() {

        tvDate.setText(DateUtils.getYearTodayDateTimes());
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SetSleepActivity.class));
            }
        });

        btnPre.setOnClickListener(this);
        btnNext.setOnClickListener(this);

        lineChartManager1 = new LineChartManager(lineChart1);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }




    //定义接口变量的get方法
    public SleepFragment.OnButtonClick getOnButtonClick() {
        return onButtonClick;
    }

    //定义接口变量的set方法
    public void setOnButtonClick(SleepFragment.OnButtonClick onButtonClick) {
        this.onButtonClick = onButtonClick;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_pre:
                tvDate.setText(DateUtils.getBeforeDay(tvDate.getText().toString()));
                getOnButtonClick().onClick(1, SpUtil.getWatchUserList().get(SpUtil.getChoise()).id,tvDate.getText().toString(),tvData);
                break;
            case R.id.btn_next:
                tvDate.setText(DateUtils.getAfterDay(tvDate.getText().toString()));
                getOnButtonClick().onClick(1, SpUtil.getWatchUserList().get(SpUtil.getChoise()).id,tvDate.getText().toString(),tvData);
                break;
        }
    }


    //1、定义接口
    public interface OnButtonClick {
        public void onClick(int position,String id,String content,TextView textView);
    }

    @Override
    public void onResume() {
        super.onResume();

        //实例化BroadcastReceiver子类 &  IntentFilter
        Receiver mBroadcastReceiver = new Receiver();
        IntentFilter intentFilter = new IntentFilter();

        //设置接收广播的类型
        intentFilter.addAction(Actions.ACTION_CHANGE_SLEEP);

        //调用Context的registerReceiver（）方法进行动态注册
        getActivity().registerReceiver(mBroadcastReceiver, intentFilter);

        getOnButtonClick().onClick(1, SpUtil.getWatchUserList().get(SpUtil.getChoise()).id,tvDate.getText().toString(),tvData);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mBroadcastReceiver != null) {
            getActivity().unregisterReceiver(mBroadcastReceiver);
            mBroadcastReceiver = null;
        }
    }

    public class Receiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            List<Health> healthList = (List<Health>) intent.getSerializableExtra("sleep");//通过key来获取你传输的

            //设置x轴的数据
            ArrayList<Float> xValues = new ArrayList<>();
            for (int i = 0; i <= 24; i++) {
                xValues.add((float) i);
            }
            List<Float> ylist = new ArrayList<>();
            for (int i = 0; i <= 24; i++) {
                ylist.add(0f);
            }
            int total=0;
            int max=0;
            for (int i=0;i<healthList.size();i++)
            {
                total=total+Integer.parseInt(healthList.get(i).getNum());
                if (max<Integer.parseInt(healthList.get(i).getNum()))
                    max=Integer.parseInt(healthList.get(i).getNum());
                ylist.set(Integer.parseInt(healthList.get(i).getHour()),Float.parseFloat(healthList.get(i).getNum()));
            }

            tvData.setText(""+total);

            //颜色集合
            List<Integer> colours = new ArrayList<>();
            colours.add(Color.GREEN);
            colours.add(Color.BLUE);
            colours.add(Color.RED);
            colours.add(Color.CYAN);

            //线的名字集合
            List<String> names = new ArrayList<>();
            names.add("折线一");
            names.add("翻转");
            names.add("折线三");
            names.add("折线四");


            //创建多条折线的图表
            lineChartManager1.showLineChart(xValues, ylist, names.get(1), colours.get(3));
            lineChartManager1.setDescription("翻转次数");
            lineChartManager1.setYAxis((max+10), 0, 11);


        }
    }

}
