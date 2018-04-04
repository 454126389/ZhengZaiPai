package app.conqueror.com.zhengzaipai.mainfragment.watch.googlemap.zonelist.addzone;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import app.conqueror.com.zhengzaipai.App;
import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.base.BaseActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WatchPoiEntity;
import app.conqueror.com.zhengzaipai.util.SpUtil;
import butterknife.Bind;
import butterknife.ButterKnife;

;

public class GAddZoneActivity extends BaseActivity<GAddZonePresenter, GAddZoneModel> implements GAddZoneContract.View, View.OnClickListener, OnMapReadyCallback,GoogleMap.OnMapClickListener {


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

    private LatLng latlng;
    private GoogleMap mMap;
    private static final int DEFAULT_ZOOM = 15;
    private Marker melbourne;
//    private Circle circle;

    List<String> railsList;
    String round;
    String[] tempzone=null;
    private Circle mCircle;

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_ok:

                if(round!=null)
                    for(int i=0;i<railsList.size();i++)
                        if(railsList.get(i).equals(round))
                            railsList.remove(i);


                String name=etName.getText().toString();

                if(name.equals("")||name.length()==0)
                    name=getString(R.string.tx_zone_name);
//                    App.showText("名称不能为空");

                if(tempzone==null)
                {
                    App.showText(getString(R.string.tx_zone_tip));
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


    @Override
    public void addZoneSuc() {
        finish();
    }

    @Override
    public int getLayoutId() {
        return R.layout.device_add_gsafe_zone;
    }

    @Override
    public void initView() {
        libBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_view);
        mapFragment.getMapAsync(this);




    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel, this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap=googleMap;

        mMap.setOnMapClickListener(this);

        Intent intent = getIntent();
        WatchPoiEntity watchPoi = (WatchPoiEntity) intent.getSerializableExtra("watchPoi");

        setWatchPoi(watchPoi);


        btnOk.setOnClickListener(this);
        btnScaleDown.setOnClickListener(this);
        btnScaleUp.setOnClickListener(this);


        safeAreaTvCurrentProgress.setText("200");

        railsList= SpUtil.getWatchUserList().get(SpUtil.getChoise()).railsList;
        if(railsList==null)
            railsList=new ArrayList<>();

        round =intent.getStringExtra("round");
        if(round!=null)
        {




//            1-自定义围栏-24.64763759244303-118.15626382827762-200-1
            LatLng center=new LatLng(Double.parseDouble(round.split("-")[2]),Double.parseDouble(round.split("-")[3]));
            if(!round.split("-")[1].equals(getString(R.string.tx_zone_name)))
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
                if(mCircle!=null)
                {
                mCircle.setRadius(i*100+200);
//                float zm=17-0.3f*i;
//                aMap.moveCamera(CameraUpdateFactory.zoomTo(zm));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mCircle.getCenter(),
                        DEFAULT_ZOOM));

                tempzone = new String[]{""+(railsList.size()+1),"",""+mCircle.getCenter().latitude,""+mCircle.getCenter().longitude,""+(i*100+200),"1"};
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
//        Marker marker;
//        View view = View.inflate(this,R.layout.layout_safe_marker, null);
//        Bitmap bm=convertViewToBitmap(view);
//        MarkerOptions markerOption = new MarkerOptions()
////                        .icon(BitmapDescriptorFactory.fromBitmap(decodeResource))
//                .icon(BitmapDescriptorFactory.fromBitmap(bm))
//                .position(new LatLng(Double.parseDouble(zone.split("-")[2]),Double.parseDouble(zone.split("-")[3]))).anchor(0.5f, 0.5f)
//                .draggable(false);
//
//        marker=aMap.addMarker(markerOption);
////            marker.showInfoWindow();
//
//
//        // 绘制一个圆形
//        aMap.addCircle(new CircleOptions().center(new LatLng(Double.parseDouble(zone.split("-")[2]),Double.parseDouble(zone.split("-")[3])))
//                .radius(200).strokeColor(Color.argb(100,252,204,159))
//                .fillColor(Color.argb(100,252,204,159)).strokeWidth(0));
//
//        TextView textView = (TextView) view.findViewById(R.id.label);
//        textView.setVisibility(View.VISIBLE);
//        textView.setText(zone.split("-")[1]);
//        bm=convertViewToBitmap(view);
//        marker.setIcon(BitmapDescriptorFactory.fromBitmap(bm));

        LatLng latLng=new LatLng(Double.parseDouble(zone.split("-")[2]),Double.parseDouble(zone.split("-")[3]));
        mCircle = mMap.addCircle(new CircleOptions()
                .center(latLng)
                .radius(Double.parseDouble(zone.split("-")[5]))
                .strokeWidth(0)
                .strokeColor(Color.argb(100, 252, 204, 159))
                .fillColor(Color.argb(100, 252, 204, 159))
                .clickable(true));

    }


    @Override
    public void onMapClick(LatLng latLng) {
        if(railsList==null)
            railsList=new ArrayList<String>();

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng,
                DEFAULT_ZOOM));

        if(round!=null&&tempzone==null)
        {

            mCircle = mMap.addCircle(new CircleOptions()
                    .center(latLng)
                    .radius(Double.parseDouble(round.split("-")[4]))
                    .strokeWidth(0)
                    .strokeColor(Color.argb(100, 252, 204, 159))
                    .fillColor(Color.argb(100, 252, 204, 159))
                    .clickable(true));
            tempzone = new String[]{""+(railsList.size()+1),"",""+latLng.latitude,""+latLng.longitude,""+Integer.parseInt(round.split("-")[4]),"1"};

            safeAreaTvCurrentProgress.setText(""+Integer.parseInt(round.split("-")[4]));
            seekbar.setProgress((Integer.parseInt(round.split("-")[4])-200)/100);
        }
        else if(railsList.size()>=3)
        {
            App.showText(getString(R.string.tx_zone_limit));
        }else if(tempzone==null)
        {


            mCircle = mMap.addCircle(new CircleOptions()
                    .center(latLng)
                    .radius(seekbar.getProgress()*100+200)
                    .strokeWidth(0)
                    .strokeColor(Color.argb(100, 252, 204, 159))
                    .fillColor(Color.argb(100, 252, 204, 159))
                    .clickable(true));


            // 绘制一个圆形
//            circle = aMap.addCircle(new CircleOptions().center(latLng)
//                    .radius(200).strokeColor(Color.argb(100,252,204,159))
//                    .fillColor(Color.argb(100,252,204,159)).strokeWidth(0));
            tempzone = new String[]{""+(railsList.size()+1),"",""+latLng.latitude,""+latLng.longitude,""+(seekbar.getProgress()*100+200),"1"};
        }else
        {
            tempzone[2]=""+latLng.latitude;
            tempzone[3]=""+latLng.longitude;
            mCircle.setCenter(latLng);
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
