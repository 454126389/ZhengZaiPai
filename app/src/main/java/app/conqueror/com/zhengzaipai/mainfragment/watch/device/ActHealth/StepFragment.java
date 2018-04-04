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
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActHealth.SetStep.SetStepActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.Health;
import app.conqueror.com.zhengzaipai.util.Actions;
import app.conqueror.com.zhengzaipai.util.DateUtils;
import app.conqueror.com.zhengzaipai.util.SpUtil;
import butterknife.Bind;
import butterknife.ButterKnife;


public class StepFragment extends Fragment implements View.OnClickListener {

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
    @Bind(R.id.line_chart1)
    LineChart lineChart1;
    LineChartManager lineChartManager1;

    private String s;
    private OnButtonClick onButtonClick;//2、定义接口成员变量
    Receiver mBroadcastReceiver;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.device_frag_step, container, false);
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
                startActivity(new Intent(getActivity(), SetStepActivity.class));
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
    public OnButtonClick getOnButtonClick() {
        return onButtonClick;
    }

    //定义接口变量的set方法
    public void setOnButtonClick(OnButtonClick onButtonClick) {
        this.onButtonClick = onButtonClick;
    }


    //1、定义接口
    public interface OnButtonClick {
        public void onClick(int position, String id, String content, TextView tvData);
    }


    @Override
    public void onResume() {
        super.onResume();


        //实例化BroadcastReceiver子类 &  IntentFilter
        Receiver mBroadcastReceiver = new Receiver();
        IntentFilter intentFilter = new IntentFilter();

        //设置接收广播的类型
        intentFilter.addAction(Actions.ACTION_CHANGE_STIP);

        //调用Context的registerReceiver（）方法进行动态注册
        getActivity().registerReceiver(mBroadcastReceiver, intentFilter);


        getOnButtonClick().onClick(0, SpUtil.getWatchUserList().get(SpUtil.getChoise()).id, tvDate.getText().toString(), tvData);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mBroadcastReceiver != null) {
            getActivity().unregisterReceiver(mBroadcastReceiver);
            mBroadcastReceiver = null;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_pre:
                tvDate.setText(DateUtils.getBeforeDay(tvDate.getText().toString()));
                getOnButtonClick().onClick(0, SpUtil.getWatchUserList().get(SpUtil.getChoise()).id, tvDate.getText().toString(), tvData);
                break;
            case R.id.btn_next:
                tvDate.setText(DateUtils.getAfterDay(tvDate.getText().toString()));
                getOnButtonClick().onClick(0, SpUtil.getWatchUserList().get(SpUtil.getChoise()).id, tvDate.getText().toString(), tvData);
                break;
        }
    }


    public class Receiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
//            String step = intent.getExtras().getString("step");
//            tvData.setText(step);

            List<Health> healthList = (List<Health>) intent.getSerializableExtra("step");//通过key来获取你传输的



            //设置x轴的数据
            ArrayList<Float> xValues = new ArrayList<>();
            for (int i = 0; i <= 24; i++) {
                xValues.add((float) i);
            }

            //设置y轴的数据()
//            List<List<Float>> yValues = new ArrayList<>();
//            for (int i = 0; i < 4; i++) {
//                List<Float> yValue = new ArrayList<>();
//                for (int j = 0; j <= 24; j++) {
//                    yValue.add((float) (Math.random() * 80));
//                }
//                yValues.add(yValue);
//            }

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
            names.add("步数");
            names.add("折线三");
            names.add("折线四");

//        lineChart1

            //创建多条折线的图表
            lineChartManager1.showLineChart(xValues, ylist, names.get(1), colours.get(3));
            lineChartManager1.setDescription("步数");
            lineChartManager1.setYAxis((max+50), 0, 11);
//        lineChartManager1.setHightLimitLine(70,"高温报警",Color.RED);


//            final String[] xdata = new String[]{"06-11","06-12","06-13","06-14","06-15","06-16","06-17"};
//            float[] ydata = new float[7];
//            LineChartData lineChartData = LineChartData.builder()
//                    .setXdata(xdata)//x轴数据
//                    .setYdata(ydata)//y轴数据
//                    .setYpCount(7)//y轴刻度数量
//                    .setCoordinatesColor(getResources().getColor(android.R.color.holo_orange_dark))
//                    //.setXXX()
//                    .setAnimType(Anim.ANIM_ALPHA)//动画效果，目前仅支持两种
//                    .build();
//            linechart.setChartData(lineChartData);

//            String[] xdate = new String[healthList.size()];
//            String[] ydata = new String[3];
//            float[] data1 = new float[healthList.size()];
//            int max=0;
//            for (int j = 0; j <healthList.size(); j++) {
//               if(max<Float.parseFloat(healthList.get(j).getNum()))
//                    max=Integer.parseInt(healthList.get(j).getNum());
//
//                xdate[j]=healthList.get(j).getHour();
//                data1[j]=Float.parseFloat(healthList.get(j).getNum());
//            }
//
//
//            ydata[0]=0+"";
//            ydata[1]=(max+50)/2+"";
//            ydata[2]=max+50+"";


//            String[] xdate = new String[]{"1-1", "1-2", "1-3", "1-4", "1-5", "1-6", "1-7"};
//            String[] ydata = lineChartView.getFundWeekYdata("5.00", "1.00");


//            scoreTrend.setScore(healthList.size(),score,monthText);


//            Random ramdom = new Random();
//            for (int i = 0; i < 6; i++) {
//                score[i] = ramdom.nextInt(max) % (max - min + 1) + min;
//            }
//            scoreTrend.setScore(score);


        }
    }


}
