package app.conqueror.com.zhengzaipai.mainfragment.watch.map.trail;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.location.DPoint;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.CoordinateConverter;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.maps.utils.overlay.SmoothMoveMarker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import app.conqueror.com.zhengzaipai.App;
import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.base.BaseActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.Gps;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WatchPoiEntity;
import app.conqueror.com.zhengzaipai.util.DateUtils;
import app.conqueror.com.zhengzaipai.util.SpUtil;
import butterknife.Bind;
import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.picker.TimePicker;
import cn.qqtheme.framework.util.ConvertUtils;

import static app.conqueror.com.zhengzaipai.R.id.tv_time;





public class TrailActivity extends BaseActivity<TrailPresenter, TrailModel> implements TrailContract.View,View.OnClickListener {


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
    @Bind(tv_time)
    TextView tvTime;
    @Bind(R.id.end_time)
    TextView endTime;
    @Bind(R.id.layout_end)
    LinearLayout layoutEnd;
    @Bind(R.id.map_type)
    ImageView mapType;
    @Bind(R.id.map_view)
    MapView mapView;
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
    private AMap aMap;




    SmoothMoveMarker smoothMarker;


    @Override
    public int getLayoutId() {
        return R.layout.act_trail;
    }

    @Override
    public void initView() {

        libBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent intent = getIntent();
        WatchPoiEntity watchPoi = (WatchPoiEntity) intent.getSerializableExtra("watchPoi");

        mapView = (MapView) findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap = mapView.getMap();
        }

        layoutBegin.setOnClickListener(this);
        layoutEnd.setOnClickListener(this);
        date.setOnClickListener(this);
        replay.setOnClickListener(this);
        trail.setOnClickListener(this);

        date.setText(DateUtils.getYearTodayDateTimes());
        setWatchPoi(watchPoi);

    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel, this);
    }


    @Override
    public void showMsg(String msg) {

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
        LatLng latlng = new LatLng(poi.getLat(), poi.getLng());
        aMap.moveCamera(CameraUpdateFactory.changeLatLng(latlng));
        aMap.moveCamera(CameraUpdateFactory.zoomTo(18));
        addMarkersToMap(latlng);
    }



    /**
     * 在地图上添加marker
     */
    private void addMarkersToMap(LatLng latlng) {

        View view = View.inflate(this,R.layout.layout_marker, null);

        TextView label = (TextView) view.findViewById(R.id.label);
        ImageView background = (ImageView) view.findViewById(R.id.background);
        ImageView headimg = (ImageView) view.findViewById(R.id.headimg);

        Bitmap bm=convertViewToBitmap(view);
        MarkerOptions markerOption = new MarkerOptions()
//                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .icon(BitmapDescriptorFactory.fromBitmap(bm))
                .position(latlng)
                .draggable(false);
        aMap.addMarker(markerOption);
    }


    //view 转bitmap

    public static Bitmap convertViewToBitmap(View view) {

        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

        view.buildDrawingCache();

        Bitmap bitmap = view.getDrawingCache();

        return bitmap;

    }

    @Override
    public void onClick(View view) {
        Calendar c = Calendar.getInstance();

        switch (view.getId())
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
    public void move(List<Gps> gpsList, Boolean isplay) {
        aMap.clear();

        // 获取轨迹坐标点
        List<LatLng> points = readLatLngs(gpsList);

        if(((ArrayList) points).size()==0)
        {
            trailnull();
        }else
        {
            addPolylineInPlayGround(points);

            LatLngBounds.Builder b = LatLngBounds.builder();
            for (int i = 0 ; i < points.size(); i++) {
                b.include(points.get(i));
            }
            LatLngBounds bounds = b.build();
            aMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100));

            smoothMarker = new SmoothMoveMarker(aMap);
            // 设置滑动的图标
            smoothMarker.setDescriptor(BitmapDescriptorFactory.fromResource(R.mipmap.icon_marker));

        /*
        //当移动Marker的当前位置不在轨迹起点，先从当前位置移动到轨迹上，再开始平滑移动
        // LatLng drivePoint = points.get(0);//设置小车当前位置，可以是任意点，这里直接设置为轨迹起点
        LatLng drivePoint = new LatLng(39.980521,116.351905);//设置小车当前位置，可以是任意点
        Pair<Integer, LatLng> pair = PointsUtil.calShortestDistancePoint(points, drivePoint);
        points.set(pair.first, drivePoint);
        List<LatLng> subList = points.subList(pair.first, points.size());
        // 设置滑动的轨迹左边点
        smoothMarker.setPoints(subList);*/

            smoothMarker.setPoints(points);//设置平滑移动的轨迹list
            smoothMarker.setTotalDuration(40);//设置平滑移动的总时间

            aMap.setInfoWindowAdapter(infoWindowAdapter);
            smoothMarker.setMoveListener(
                    new SmoothMoveMarker.MoveListener() {
                        @Override
                        public void move(final double distance) {

                            Log.i("MY","distance:  "+distance);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (infoWindowLayout != null && title != null && smoothMarker.getMarker().isInfoWindowShown()) {
                                        title.setText("距离终点还有： " + (int) distance + "米");
                                    }
                                    if(distance == 0){
                                        smoothMarker.getMarker().hideInfoWindow();
                                    }
                                }
                            });
                        }
                    });
            if (isplay)
            {
                smoothMarker.getMarker().showInfoWindow();
                smoothMarker.startSmoothMove();
            }
        }


    }



    AMap.InfoWindowAdapter infoWindowAdapter = new AMap.InfoWindowAdapter() {
        @Override
        public View getInfoWindow(Marker marker) {

            return getInfoWindowView(marker);
        }

        @Override
        public View getInfoContents(Marker marker) {


            return getInfoWindowView(marker);
        }
    };

    LinearLayout infoWindowLayout;
    TextView title;
    TextView snippet;

    private View getInfoWindowView(Marker marker) {
        if (infoWindowLayout == null) {
            infoWindowLayout = new LinearLayout(TrailActivity.this);
            infoWindowLayout.setOrientation(LinearLayout.VERTICAL);
            title = new TextView(TrailActivity.this);
            snippet = new TextView(TrailActivity.this);
            title.setTextColor(Color.BLACK);
            snippet.setTextColor(Color.BLACK);
            infoWindowLayout.setBackgroundResource(R.drawable.infowindow_bg);

            infoWindowLayout.addView(title);
            infoWindowLayout.addView(snippet);
        }

        return infoWindowLayout;
    }

    private void addPolylineInPlayGround(List<LatLng> list) {
//        List<LatLng> list = readLatLngs();
        List<Integer> colorList = new ArrayList<Integer>();

        aMap.addPolyline(new PolylineOptions().setCustomTexture(BitmapDescriptorFactory.fromResource(R.drawable.custtexture)) //setCustomTextureList(bitmapDescriptors)
                .addAll(list)
                .useGradient(true)
                .width(18));
    }

    private List<LatLng> readLatLngs(List<Gps> gpsList) {
        List<LatLng> points = new ArrayList<LatLng>();
        for (int i = 0; i < gpsList.size(); i++) {

            LatLng sourceLatLng=new LatLng(Double.parseDouble(gpsList.get(i).getGps().split(",")[2]), Double.parseDouble(gpsList.get(i).getGps().split(",")[4]));

            CoordinateConverter converter  = new CoordinateConverter(TrailActivity.this);
            // CoordType.GPS 待转换坐标类型
            converter.from(CoordinateConverter.CoordType.GPS);
            // sourceLatLng待转换坐标点 DPoint类型
            converter.coord(sourceLatLng);
            // 执行转换操作
            LatLng desLatLng = converter.convert();

            if(lbsSwitch.isChecked())
            {

                points.add(desLatLng);


                //基站定位关闭，基站地位点不保存
            }else
            {
                //基站定位关闭，基站地位点不保存
                if(gpsList.get(i).getGps().split(",")[1].equals("1"))
                {
//                    com.google.android.gms.maps.model.LatLng latLng=new com.google.android.gms.maps.model.LatLng(Double.parseDouble(gpsList.get(i).getGps().split(",")[2]), Double.parseDouble(gpsList.get(i).getGps().split(",")[4]));
//                    points.add(latLng);
//                    boundsBuilder.include(latLng);
                    points.add(desLatLng);
                }
            }
        }
        return points;
    }

/*    private double[] coords = {116.3499049793749, 39.97617053371078,
            116.34978804908442, 39.97619854213431, 116.349674596623,
            39.97623045687959, 116.34955525200917, 39.97626931100656,
            116.34943728748914, 39.976285626595036, 116.34930864705592,
            39.97628129172198, 116.34918981582413, 39.976260803938594,
            116.34906721558868, 39.97623535890678, 116.34895185151584,
            39.976214717128855, 116.34886935936889, 39.976280148755315,
            116.34873954611332, 39.97628182112874, 116.34860763527448,
            39.97626038855863, 116.3484658907622, 39.976306080391836,
            116.34834585430347, 39.976358252119745, 116.34831166130878,
            39.97645709321835, 116.34827643560175, 39.97655231226543,
            116.34824186261169, 39.976658372925556, 116.34825080406188,
            39.9767570732376, 116.34825631960626, 39.976869087779995,
            116.34822111635201, 39.97698451764595, 116.34822901510276,
            39.977079745909876, 116.34822234337618, 39.97718701787645,
            116.34821627457707, 39.97730766147824, 116.34820593515043,
            39.977417746816776, 116.34821013897107, 39.97753930933358
            , 116.34821304891533, 39.977652209132174, 116.34820923399242,
            39.977764016531076, 116.3482045955917, 39.97786190186833,
            116.34822159449203, 39.977958856930286, 116.3482256370537,
            39.97807288885813, 116.3482098441266, 39.978170063673524,
            116.34819564465377, 39.978266951404066, 116.34820541974412,
            39.978380693859116, 116.34819672351216, 39.97848741209275,
            116.34816588867105, 39.978593409607825, 116.34818489339459,
            39.97870216883567, 116.34818473446943, 39.978797222300166,
            116.34817728972234, 39.978893492422685, 116.34816491505472,
            39.978997133775266, 116.34815408537773, 39.97911413849568,
            116.34812908154862, 39.97920553614499, 116.34809495907906,
            39.979308267469264, 116.34805113358091, 39.97939658036473,
            116.3480310509613, 39.979491697188685, 116.3480082124968,
            39.979588529006875, 116.34799530586834, 39.979685789111635,
            116.34798818413954, 39.979801430587926, 116.3479996420353,
            39.97990758587515, 116.34798697544538, 39.980000796262615,
            116.3479912988137, 39.980116318796085, 116.34799204219203,
            39.98021407403913, 116.34798535084123, 39.980325006125696,
            116.34797702460183, 39.98042511477518, 116.34796288754136,
            39.98054129336908, 116.34797509821901, 39.980656820423505,
            116.34793922017285, 39.98074576792626, 116.34792586413015,
            39.98085620772756, 116.3478962642899, 39.98098214824056,
            116.34782449883967, 39.98108306010269, 116.34774758827285,
            39.98115277119176, 116.34761476652932, 39.98115430642997,
            116.34749135408349, 39.98114590845294, 116.34734772765582,
            39.98114337322547, 116.34722082902628, 39.98115066909245,
            116.34708205250223, 39.98114532232906, 116.346963237696,
            39.98112245161927, 116.34681500222743, 39.981136637759604,
            116.34669622104072, 39.981146248090866, 116.34658043260109,
            39.98112495260716, 116.34643721418927, 39.9811107163792,
            116.34631638374302, 39.981085081075676, 116.34614782996252,
            39.98108046779486, 116.3460256053666, 39.981049089345206,
            116.34588814050122, 39.98104839362087, 116.34575119741586,
            39.9810544889668, 116.34562885420186, 39.981040940565734,
            116.34549232235582, 39.98105271658809, 116.34537348820508,
            39.981052294975264, 116.3453513775533, 39.980956549928244
    };*/

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
