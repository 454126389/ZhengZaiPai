package app.conqueror.com.zhengzaipai.mainfragment.watch.googlemap.trail;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import app.conqueror.com.zhengzaipai.App;
import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.base.BaseActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.Gps;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WatchPoiEntity;
import app.conqueror.com.zhengzaipai.util.DateUtils;
import app.conqueror.com.zhengzaipai.util.SpUtil;
import butterknife.Bind;
import butterknife.ButterKnife;
import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.picker.TimePicker;
import cn.qqtheme.framework.util.ConvertUtils;


public class GTrailActivity extends BaseActivity<GTrailPresenter, GTrailModel> implements GTrailContract.View, View.OnClickListener, OnMapReadyCallback {


    @Bind(R.id.title_bar_status)
    View titleBarStatus;
    @Bind(R.id.lib_btn_back)
    ImageButton libBtnBack;
    @Bind(R.id.lib_tv_title)
    TextView libTvTitle;
    @Bind(R.id.lbs_switch)
    SwitchCompat lbsSwitch;
    @Bind(R.id.date)
    TextView date;
    @Bind(R.id.begin_time)
    TextView beginTime;
    @Bind(R.id.layout_begin)
    LinearLayout layoutBegin;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.end_time)
    TextView endTime;
    @Bind(R.id.layout_end)
    LinearLayout layoutEnd;
    @Bind(R.id.map_type)
    ImageView mapType;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_date)
    TextView tvDate;
    @Bind(R.id.divider)
    View divider;
    @Bind(R.id.tv_position_datatype)
    TextView tvPositionDatatype;
    @Bind(R.id.tv_address)
    TextView tvAddress;
    @Bind(R.id.tv_address1)
    TextView tvAddress1;
    @Bind(R.id.layout_content)
    ConstraintLayout layoutContent;
    @Bind(R.id.replay)
    Button replay;
    @Bind(R.id.trail)
    Button trail;

    private GoogleMap mMap;
    private LatLng latlng;
    private Marker melbourne;
    private static final int DEFAULT_ZOOM = 15;

    @Override
    public void onClick(View v) {
        Calendar c = Calendar.getInstance();
        switch (v.getId())
        {
            case R.id.date:
                final DatePicker picker = new DatePicker(this);
                picker.setCanceledOnTouchOutside(true);
                picker.setUseWeight(true);
                picker.setTopPadding(ConvertUtils.toPx(this, 10));

                picker.setRangeStart(2017, 1, 1);

                picker.setRangeEnd(2111, 1, 11);

                picker.setSelectedItem(c.get(Calendar.YEAR),  c.get(Calendar.MONTH)+1, c.get(Calendar.DATE));
                picker.setResetWhileWheel(false);
                picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
                    @Override
                    public void onDatePicked(String year, String month, String day) {
//                        App.showText(year + "-" + month + "-" + day);
                        date.setText(year + "-" + month + "-" + day);
                    }
                });
                picker.setOnWheelListener(new DatePicker.OnWheelListener() {
                    @Override
                    public void onYearWheeled(int index, String year) {
                        picker.setTitleText(year + "-" + picker.getSelectedMonth() + "-" + picker.getSelectedDay());
                    }

                    @Override
                    public void onMonthWheeled(int index, String month) {
                        picker.setTitleText(picker.getSelectedYear() + "-" + month + "-" + picker.getSelectedDay());
                    }

                    @Override
                    public void onDayWheeled(int index, String day) {
                        picker.setTitleText(picker.getSelectedYear() + "-" + picker.getSelectedMonth() + "-" + day);
                    }
                });
                picker.show();
                break;

            case R.id.layout_begin:
                onTimePicker(beginTime);
                break;

            case R.id.layout_end:
                onTimePicker(endTime);
                break;

            case R.id.replay:
//                move(true);
//                "2014-06-14-16-09-00"

                String stime= DateUtils.dataTwo(date.getText().toString()+"-"+beginTime.getText().toString());
                String etime= DateUtils.dataTwo(date.getText().toString()+"-"+endTime.getText().toString());
//                mPresenter.getTrail(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id,"1504207907","1504569131",true);
                mPresenter.getTrail(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id,stime,etime,true);
                break;

            case R.id.trail:
//                move(false);
//                mPresenter.getTrail("2016000891","1504207907","1504569131",false);
//                String stime= DateUtils.dataTwo(date.getText().toString()+"-"+beginTime.getText().toString());
//                String etime= DateUtils.dataTwo(date.getText().toString()+"-"+endTime.getText().toString());
//                mPresenter.getTrail(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id,"1504207907","1504569131",true);
                mPresenter.getTrail(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id,DateUtils.dataTwo(date.getText().toString()+"-"+beginTime.getText().toString()),DateUtils.dataTwo(date.getText().toString()+"-"+endTime.getText().toString()),false);
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
    public void setWatchPoi(WatchPoiEntity poi) {
        latlng = new LatLng(poi.getLat(), poi.getLng());
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng,
                DEFAULT_ZOOM));

        addMarkersToMap();
    }


    /**
     * 在地图上添加marker
     */
    private void addMarkersToMap() {

        View view = View.inflate(this,R.layout.layout_marker, null);

        TextView label = (TextView) view.findViewById(R.id.label);
        ImageView background = (ImageView) view.findViewById(R.id.background);
        ImageView headimg = (ImageView) view.findViewById(R.id.headimg);

        Bitmap bm=convertViewToBitmap(view);
//        markerOption = new MarkerOptions()
////                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
//                .icon(BitmapDescriptorFactory.fromBitmap(bm))
//                .position(latlng)
//                .draggable(false);
//        aMap.addMarker(markerOption);


//      private static final LatLng MELBOURNE = new LatLng(-37.813, 144.962);
//      private
        melbourne = mMap.addMarker(new MarkerOptions()
                .position(latlng)
                .title(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id)
//                .title("Melbourne")
//                .snippet("Population: 4,137,400")
                .icon(BitmapDescriptorFactory.fromBitmap(bm)));

    }



    //view 转bitmap

    public static Bitmap convertViewToBitmap(View view) {

        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

        view.buildDrawingCache();

        Bitmap bitmap = view.getDrawingCache();

        return bitmap;

    }

    Iterable<LatLng> points;

    boolean playflag = true;
    @Override
    public void move(List<Gps> gpsList, Boolean isplay) {
        playflag=false;
        i=0;
        mMap.clear();

        // 获取轨迹坐标点
//        List<LatLng> points
         points= readLatLngs(gpsList);
        if(((ArrayList) points).size()==0)
        {
            trailnull();
        }else
        {
            mMap.addPolyline(new PolylineOptions().addAll(points)
                    .width(INITIAL_STROKE_WIDTH_PX)
                    .color(Color.BLUE)
                    .geodesic(true)
                    .clickable(true));
            addMarkersToMap();

            if(isplay)
            {
                playflag=true;
                mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(boundsBuilder.build(),20));
                handler.postDelayed(runnable, TIME); //每隔1s执行
            }
        }


    }
    private int i = 0;
    private int TIME = 1000;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {

        @Override
        public void run() {
            if (playflag){
            // handler自带方法实现定时器
            try {

                handler.postDelayed(this, TIME);
                if(i<((ArrayList) points).size())
                {
                    LatLng latLng= (LatLng) ((ArrayList) points).get(i);
                    i++;
                    melbourne.setPosition(latLng);

//                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(points.iterator().next(),
//                        DEFAULT_ZOOM));
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println("exception...");
            }
            }
        }
    };
    LatLngBounds.Builder boundsBuilder;
    private List<LatLng> readLatLngs(List<Gps> gpsList) {
        boundsBuilder = new LatLngBounds.Builder();
        List<LatLng> points = new ArrayList<LatLng>();
        for (int i = 0; i < gpsList.size(); i++) {
            if(!lbsSwitch.isChecked())
            {
                //基站定位关闭，基站地位点不保存
                if(gpsList.get(i).getGps().split(",")[2].equals("1"))
                {
                    LatLng latLng=new LatLng(Double.parseDouble(gpsList.get(i).getGps().split(",")[2]), Double.parseDouble(gpsList.get(i).getGps().split(",")[4]));
                    points.add(latLng);
                    boundsBuilder.include(latLng);
                }
            }else
            {
                LatLng latLng=new LatLng(Double.parseDouble(gpsList.get(i).getGps().split(",")[2]), Double.parseDouble(gpsList.get(i).getGps().split(",")[4]));
                points.add(latLng);

                boundsBuilder.include(latLng);
            }
        }
        return points;
    }

    @Override
    public int getLayoutId() {
        return R.layout.act_gtrail;
    }

    @Override
    public void initView() {


        libBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        layoutBegin.setOnClickListener(this);
        layoutEnd.setOnClickListener(this);
        date.setOnClickListener(this);
        replay.setOnClickListener(this);
        trail.setOnClickListener(this);


        date.setText(DateUtils.getYearTodayDateTimes());



        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_view);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel, this);
    }

    private static final LatLng AKL = new LatLng(-37.006254, 174.783018);
    private static final LatLng JFK = new LatLng(40.641051, -73.777485);
    private static final LatLng LAX = new LatLng(33.936524, -118.377686);
    private static final LatLng LHR = new LatLng(51.471547, -0.460052);
    private static final int INITIAL_STROKE_WIDTH_PX = 5;
    private Polyline mMutablePolyline;

    private static final LatLng ADELAIDE = new LatLng(-34.92873, 138.59995);
    private static final LatLng DARWIN = new LatLng(-12.4258647, 130.7932231);
    private static final LatLng MELBOURNE = new LatLng(-37.81319, 144.96298);
    private static final LatLng PERTH = new LatLng(-31.95285, 115.85734);

    @Override
    public void onMapReady(GoogleMap map) {
        mMap=map;
        // Override the default content description on the view, for accessibility mode.
//        map.setContentDescription("polyline_demo_description");
//
//        // A geodesic polyline that goes around the world.
//        map.addPolyline(new PolylineOptions()
//                .add(LHR, AKL, LAX, JFK, LHR)
//                .width(INITIAL_STROKE_WIDTH_PX)
//                .color(Color.BLUE)
//                .geodesic(true)
//                .clickable(true));
//
//        // A simple polyline across Australia. This polyline will be mutable.
////        int color = Color.HSVToColor(
////                mAlphaBar.getProgress(), new float[]{mHueBar.getProgress(), 1, 1});
//
//        mMutablePolyline = map.addPolyline(new PolylineOptions()
//                .color(R.color.red_500)
//                .width(50)
//                .clickable(true)
//                .add(MELBOURNE, ADELAIDE, PERTH, DARWIN));

//        mHueBar.setOnSeekBarChangeListener(this);
//        mAlphaBar.setOnSeekBarChangeListener(this);
//        mWidthBar.setOnSeekBarChangeListener(this);
//
//        mStartCapSpinner.setOnItemSelectedListener(this);
//        mEndCapSpinner.setOnItemSelectedListener(this);
//        mJointTypeSpinner.setOnItemSelectedListener(this);
//        mPatternSpinner.setOnItemSelectedListener(this);

//        mMutablePolyline.setStartCap(getSelectedCap(mStartCapSpinner.getSelectedItemPosition()));
//        mMutablePolyline.setEndCap(getSelectedCap(mEndCapSpinner.getSelectedItemPosition()));
//        mMutablePolyline.setJointType(getSelectedJointType(mJointTypeSpinner.getSelectedItemPosition()));
//        mMutablePolyline.setPattern(getSelectedPattern(mPatternSpinner.getSelectedItemPosition()));

        // Move the map so that it is centered on the mutable polyline.
//        map.moveCamera(CameraUpdateFactory.newLatLngZoom(MELBOURNE, 3));
//
//        // Add a listener for polyline clicks that changes the clicked polyline's color.
//        map.setOnPolylineClickListener(new GoogleMap.OnPolylineClickListener() {
//            @Override
//            public void onPolylineClick(Polyline polyline) {
//                // Flip the values of the red, green and blue components of the polyline's color.
//                polyline.setColor(polyline.getColor() ^ 0x00ffffff);
//            }
//        });

        Intent intent = getIntent();
        WatchPoiEntity watchPoi = (WatchPoiEntity) intent.getSerializableExtra("watchPoi");
        setWatchPoi(watchPoi);
    }
    @Override
    public void suc() {
        App.showText(getString(R.string.tip_suc));
    }

    @Override
    public void fail() {
        App.showText(getString(R.string.tip_fail));
    }

    @Override
    public void trailnull() {
        App.showText(getString(R.string.tip_trail_null));
    }

}
