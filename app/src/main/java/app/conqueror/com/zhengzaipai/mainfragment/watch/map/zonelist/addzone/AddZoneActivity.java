package app.conqueror.com.zhengzaipai.mainfragment.watch.map.zonelist.addzone;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.Circle;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.core.PoiItem;

import java.util.ArrayList;
import java.util.List;

import app.conqueror.com.zhengzaipai.App;
import app.conqueror.com.zhengzaipai.MainActivity;
import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.base.BaseActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WatchPoiEntity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.map.zonelist.ZoneListContract;
import app.conqueror.com.zhengzaipai.mainfragment.watch.map.zonelist.ZoneListModel;
import app.conqueror.com.zhengzaipai.mainfragment.watch.map.zonelist.ZoneListPresenter;
import app.conqueror.com.zhengzaipai.mainfragment.watch.me.MultipleItem;
import app.conqueror.com.zhengzaipai.util.SpUtil;
import butterknife.Bind;
import butterknife.ButterKnife;

import static android.R.id.progress;

public class AddZoneActivity extends BaseActivity<AddZonePresenter, AddZoneModel> implements AddZoneContract.View,AMap.OnMapClickListener,View.OnClickListener {


    @Bind(R.id.title_bar_status)
    View titleBarStatus;
    @Bind(R.id.lib_btn_back)
    ImageButton libBtnBack;
    @Bind(R.id.lib_tv_title)
    TextView libTvTitle;
    @Bind(R.id.title_right_btn)
    Button titleRightBtn;
    @Bind(R.id.title_iv_headimg)
    ImageView titleIvHeadimg;
    @Bind(R.id.lib_tv_right)
    TextView libTvRight;
    @Bind(R.id.lib_bottom_line)
    View libBottomLine;
    @Bind(R.id.map_view)
    MapView mapView;
    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.btnScaleDown)
    ImageView btnScaleDown;
    @Bind(R.id.seekbar)
    SeekBar seekbar;
    @Bind(R.id.btnScaleUp)
    ImageView btnScaleUp;
    @Bind(R.id.safe_area_tv_current_progress)
    TextView safeAreaTvCurrentProgress;
    @Bind(R.id.btn_ok)
    TextView btnOk;

//    private Circle circle;
    private AMap aMap;

    Bitmap decodeResource ;

    List<PoiItem> poiItems = new ArrayList<PoiItem>();

    private Marker marker;
//    private Zone tempzone=null;
    private Circle circle;
    TextView textView;

    List<String> railsList;

    String[] tempzone=null;
//    List<Zone>  zoneList;

    String round;

    MarkerOptions markerOption;
    @Override
    public int getLayoutId() {
        return R.layout.device_add_safe_zone;
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




        decodeResource = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_marker_safe_zone);

        mapView = (MapView) findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap = mapView.getMap();
        }
        aMap.setOnMapClickListener(this);// 对amap添加单击地图事件监听器


        btnOk.setOnClickListener(this);
        btnScaleDown.setOnClickListener(this);
        btnScaleUp.setOnClickListener(this);



        setWatchPoi(watchPoi);

        railsList=SpUtil.getWatchUserList().get(SpUtil.getChoise()).railsList;
        if(railsList==null)
            railsList=new ArrayList<>();


        round =intent.getStringExtra("round");

        if(round!=null)
        {




//            1-自定义围栏-24.64763759244303-118.15626382827762-200-1
            LatLng center=new LatLng(Double.parseDouble(round.split("-")[2]),Double.parseDouble(round.split("-")[3]));
            if(!round.split("-")[1].equals("自定义围栏"))
                etName.setText(round.split("-")[1]);
            onMapClick(center);
            btnOk.setTextColor(getResources().getColor(R.color.colorPrimary));
            safeAreaTvCurrentProgress.setText(round.split("-")[4]);
            seekbar.setProgress((Integer.parseInt(round.split("-")[4])-200)/100);

        }else
        {

            if(railsList.size()<3)
                btnOk.setTextColor(getResources().getColor(R.color.colorPrimary));
            for(int i=0;i<railsList.size();i++)
            {
                addCircle(railsList.get(i));
            }
        }


        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                safeAreaTvCurrentProgress.setText(""+(i*100+200));

                if(circle!=null)
                {
                circle.setRadius(i*100+200);
//                float zm=17-0.3f*i;
//                aMap.moveCamera(CameraUpdateFactory.zoomTo(zm));

                aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(circle.getCenter(),
                        15));

                tempzone = new String[]{""+(railsList.size()+1),"",""+circle.getCenter().latitude,""+circle.getCenter().longitude,""+(i*100+200),"1"};
                }


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    private void addCircle(String zone)
    {
        Marker marker;
        View view = View.inflate(this,R.layout.layout_safe_marker, null);
        Bitmap bm=convertViewToBitmap(view);
        MarkerOptions markerOption = new MarkerOptions()
//                        .icon(BitmapDescriptorFactory.fromBitmap(decodeResource))
                .icon(BitmapDescriptorFactory.fromBitmap(bm))
                .position(new LatLng(Double.parseDouble(zone.split("-")[2]),Double.parseDouble(zone.split("-")[3]))).anchor(0.5f, 0.5f)
                .draggable(false);

        marker=aMap.addMarker(markerOption);
//            marker.showInfoWindow();


        // 绘制一个圆形
        aMap.addCircle(new CircleOptions().center(new LatLng(Double.parseDouble(zone.split("-")[2]),Double.parseDouble(zone.split("-")[3])))
                .radius(200).strokeColor(Color.argb(100,252,204,159))
                .fillColor(Color.argb(100,252,204,159)).strokeWidth(0));

        TextView textView = (TextView) view.findViewById(R.id.label);
        textView.setVisibility(View.VISIBLE);
        textView.setText(zone.split("-")[1]);
        bm=convertViewToBitmap(view);
        marker.setIcon(BitmapDescriptorFactory.fromBitmap(bm));

    }
//    private void addCircle(String zone)
//    {
//        Marker marker;
//        Circle circle;
//        View view = View.inflate(this,R.layout.layout_safe_marker, null);
//        Bitmap bm=convertViewToBitmap(view);
//        MarkerOptions markerOption = new MarkerOptions()
////                        .icon(BitmapDescriptorFactory.fromBitmap(decodeResource))
//                .icon(BitmapDescriptorFactory.fromBitmap(bm))
//                .position(new LatLng(Double.parseDouble(zone.getLat()),Double.parseDouble(zone.getLon()))).anchor(0.5f, 0.5f)
//                .draggable(false);
//
//        marker=aMap.addMarker(markerOption);
////            marker.showInfoWindow();
//
//
//        // 绘制一个圆形
//        aMap.addCircle(new CircleOptions().center(new LatLng(Double.parseDouble(zone.getLat()),Double.parseDouble(zone.getLon())))
//                .radius(200).strokeColor(Color.argb(100,252,204,159))
//                .fillColor(Color.argb(100,252,204,159)).strokeWidth(0));
//
//
//        TextView textView = (TextView) view.findViewById(R.id.label);
//        textView.setVisibility(View.VISIBLE);
//        textView.setText(zone.getAlias());
//        bm=convertViewToBitmap(view);
//        marker.setIcon(BitmapDescriptorFactory.fromBitmap(bm));
//
//    }


    @Override
    public void onMapClick(LatLng latLng) {

//       railsList=SpUtil.getWatchUserList().get(SpUtil.getChoise()).railsList;


//        aMap.moveCamera(CameraUpdateFactory.changeLatLng(latLng));
//        aMap.moveCamera(CameraUpdateFactory.zoomTo(17));
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,
                15));

        if(railsList==null)
            railsList=new ArrayList<String>();
        if(round!=null&&tempzone==null)
        {
            View view = View.inflate(this,R.layout.layout_safe_marker, null);
            Bitmap bm=convertViewToBitmap(view);
            MarkerOptions markerOption = new MarkerOptions()
//                        .icon(BitmapDescriptorFactory.fromBitmap(decodeResource))
                    .icon(BitmapDescriptorFactory.fromBitmap(bm))
                    .position(latLng).anchor(0.5f, 0.5f)
                    .draggable(false);
            ;
            marker=aMap.addMarker(markerOption);
//            marker.showInfoWindow();


            // 绘制一个圆形
            circle = aMap.addCircle(new CircleOptions().center(latLng)
                    .radius(Double.parseDouble(round.split("-")[4])).strokeColor(Color.argb(100,252,204,159))
                    .fillColor(Color.argb(100,252,204,159)).strokeWidth(0));
//            tempzone = new String[]{""+(railsList.size()+1),"",""+latLng.latitude,""+latLng.longitude,"200","1"};
            tempzone = new String[]{""+(railsList.size()+1),"",""+latLng.latitude,""+latLng.longitude,""+Integer.parseInt(round.split("-")[4]),"1"};
        }
        else if(railsList.size()>=3&&round==null)
        {
            App.showText("安全区域只能设置3个");
        }else if(tempzone==null)
        {


            View view = View.inflate(this,R.layout.layout_safe_marker, null);
            Bitmap bm=convertViewToBitmap(view);
                MarkerOptions markerOption = new MarkerOptions()
//                        .icon(BitmapDescriptorFactory.fromBitmap(decodeResource))
                        .icon(BitmapDescriptorFactory.fromBitmap(bm))
                        .position(latLng).anchor(0.5f, 0.5f)
                        .draggable(false);
            ;
            marker=aMap.addMarker(markerOption);
//            marker.showInfoWindow();


            // 绘制一个圆形
            circle = aMap.addCircle(new CircleOptions().center(latLng)
                    .radius(200).strokeColor(Color.argb(100,252,204,159))
                    .fillColor(Color.argb(100,252,204,159)).strokeWidth(0));
            tempzone = new String[]{""+(railsList.size()+1),"",""+latLng.latitude,""+latLng.longitude,"200","1"};
        }else
        {
            tempzone[2]=""+latLng.latitude;
            tempzone[3]=""+latLng.longitude;
            marker.setPosition(latLng);
            circle.setCenter(latLng);
        }




    }


    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel, this);
    }


    @Override
    public void showDialog() {
        showBaseDialog();
    }


    @Override
    public void hideDialog() {
        hideBaseDialog();
    }

    @Override
    public void showMsg(String msg) {
        App.showText(msg);
    }

    @Override
    public void initDialog() {
        initBaseDialog();
    }

    @Override
    protected void onResume() {
        super.onResume();
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
        switch (view.getId())
        {
            case R.id.btn_ok:

                if(round!=null)
                for(int i=0;i<railsList.size();i++)
                    if(railsList.get(i).equals(round))
                        railsList.remove(i);


                String name=etName.getText().toString();

                if(name.equals("")||name.length()==0)
                    name="自定义围栏";
//                    App.showText("名称不能为空");

               if(tempzone==null)
                {
                    App.showText("请点击地图中心生成安全区域");
                }else
                {
                    tempzone[1]=name;

                    StringBuffer wifMsg=new StringBuffer();


                    for (int i=0;i<tempzone.length;i++)
                    {
                        wifMsg.append(tempzone[i]);
                        if (i<tempzone.length-1)
                            wifMsg.append("-");

                    }

                    railsList.add(wifMsg.toString());
                    mPresenter.addZone(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id, railsList);
                }

                break;

            case R.id.btnScaleDown:
                if(seekbar.getProgress()>0)
                    seekbar.setProgress(seekbar.getProgress()-1);
                break;
            case R.id.btnScaleUp:
                if(seekbar.getProgress()<8)
                    seekbar.setProgress(seekbar.getProgress()+1);
                break;

        }
    }

    @Override
    public void addZoneSuc() {

        finish();

//        //设置文字
//        View tempview = View.inflate(this,R.layout.layout_safe_marker, null);
//        textView = (TextView) tempview.findViewById(R.id.label);
//        textView.setVisibility(View.VISIBLE);
//        textView.setText(etName.getText().toString());
//        Bitmap bm=convertViewToBitmap(tempview);
//        marker.setIcon(BitmapDescriptorFactory.fromBitmap(bm));
//
//
//        SpUtil.changeWatchUserListRailsList(SpUtil.getChoise(),list);
////        tempzone.setName(etName.getText().toString());
//        //保存
//        SpUtil.addZoneList(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id,tempzone);
//        if(null==SpUtil.getZoneList(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id)||SpUtil.getZoneList(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id).size()<3)
//            btnOk.setTextColor(getResources().getColor(R.color.colorPrimary));
////        清空
//        zoneList.add(tempzone);
//        etName.setText("");
//        tempzone=null;
    }



}
