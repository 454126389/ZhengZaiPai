package app.conqueror.com.zhengzaipai.mainfragment.watch.googlemap.zonelist;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.chad.library.adapter.base.BaseQuickAdapter;
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
import app.conqueror.com.zhengzaipai.mainfragment.watch.googlemap.zonelist.addzone.GAddZoneActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.map.zonelist.ZoneListActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.me.MultipleItem;
import app.conqueror.com.zhengzaipai.mainfragment.watch.me.MultipleItemQuickAdapter;
import app.conqueror.com.zhengzaipai.util.SpUtil;
import butterknife.Bind;
import butterknife.ButterKnife;

public class GZoneListActivity extends BaseActivity<GZoneListPresenter, GZoneListModel> implements GZoneListContract.View,OnMapReadyCallback {


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

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    private GoogleMap mMap;
    WatchPoiEntity watchPoi;
    private LatLng latlng;
    private static final int DEFAULT_ZOOM = 15;
    private Marker melbourne;

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

//      private
        melbourne = mMap.addMarker(new MarkerOptions()
                .position(latlng)
                .title(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id)
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
        onResume();
    }

    @Override
    public int getLayoutId() {
        return R.layout.device_act_gsafe_zone;
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




        onResume();
    }

    List<MultipleItem> list;

    @Override
    protected void onResume() {

        super.onResume();

        if(mMap!=null)
        {
        mMap.clear();

        list = new ArrayList<>();


        /**
         * 围栏
         */
        List<String> rails = SpUtil.getWatchUserList().get(SpUtil.getChoise()).railsList;


        //同步数据
        if(null!=rails)
            for (int i = 0; i < rails.size(); i++) {
                addCircle(rails.get(i));
                if (rails.get(i).split("-")[5].equals("1"))
                    list.add(new MultipleItem(MultipleItem.ITEM_ZONE, MultipleItem.IMG_SPAN_SIZE, rails.get(i).split("-")[1], 0, rails.get(i).split("-")[4], true));
                else
                    list.add(new MultipleItem(MultipleItem.ITEM_ZONE, MultipleItem.IMG_SPAN_SIZE, rails.get(i).split("-")[1], 0, rails.get(i).split("-")[4], false));

            }


        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        final MultipleItemQuickAdapter multipleItemAdapter = new MultipleItemQuickAdapter(this, list);
        multipleItemAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                return list.get(position).getSpanSize();
            }
        });


        View footerView = getLayoutInflater().inflate(R.layout.device_layout_safe_zone_footer, (ViewGroup) recyclerView.getParent(), false);
        footerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(ZoneListActivity.this, AddZoneActivity.class));
                Intent intent = new Intent(GZoneListActivity.this, GAddZoneActivity.class);
//                String url = "http://sim.conqueror.cn/alipay/index.php/Shop?cut=4&did=12121212";
                // url可以是一号店或者淘宝等第三方的购物wap站点，在该网站的支付过程中，支付宝sdk完成拦截支付
                intent.putExtra("watchPoi", watchPoi);
                startActivity(intent);
//                startActivity(new Intent(getActivity(), ZoneListActivity.class));
            }
        });
        multipleItemAdapter.addFooterView(footerView, 0);
        recyclerView.setAdapter(multipleItemAdapter);


        multipleItemAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {

//                    private short id; //围栏id
//                    private String alias;
//                    private double lat;
//                    private double lon;
//                    private short radius;
//                    private short onOff; (0,1)

//                    "rails":["1-学校-24.651083-118.1513600-300-1"]}]}
                    case R.id.switch_compat:
                        List<String> railsList = SpUtil.getWatchUserList().get(SpUtil.getChoise()).railsList;

                        if (railsList == null)
                            railsList = new ArrayList<>();

                        StringBuffer wifMsg = new StringBuffer();
                        String[] temp = railsList.get(position).split("-");
                        if (((SwitchCompat) view).isChecked())
                            temp[5] = "1";
                        else
                            temp[5] = "0";

                        for (int i = 0; i < temp.length; i++) {
                            wifMsg.append(temp[i]);
                            if (i < temp.length - 1)
                                wifMsg.append("-");

                        }

                        railsList.set(position, wifMsg.toString());
                        mPresenter.addZone(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id, railsList);
                        break;

                    case R.id.root:
                        TextView name= (TextView) view.findViewById(R.id.tv_name);
                        TextView range= (TextView) view.findViewById(R.id.tv_range);
                        Intent intent = new Intent(GZoneListActivity.this, GAddZoneActivity.class);
                        intent.putExtra("watchPoi", watchPoi);
                        intent.putExtra("round", SpUtil.getWatchUserList().get(SpUtil.getChoise()).railsList.get(position));
                        startActivity(intent);
                        break;
                    case R.id.slide_hide_panel:
                        List<String> del_railsList = SpUtil.getWatchUserList().get(SpUtil.getChoise()).railsList;
                        del_railsList.remove(position);
                        mPresenter.addZone(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id, del_railsList);
                        list.remove(position);
                        multipleItemAdapter.notifyDataSetChanged();
                        break;


                }


            }
        });

            Intent intent = getIntent();
            watchPoi = (WatchPoiEntity) intent.getSerializableExtra("watchPoi");
            setWatchPoi(watchPoi);

        }

    }
    private Circle mCircle;
    private void addCircle(String zone) {
//        com.amap.api.maps.model.Marker marker;
//        Circle circle;
//        View view = View.inflate(this, R.layout.layout_safe_marker, null);
//        Bitmap bm = convertViewToBitmap(view);
//        com.amap.api.maps.model.MarkerOptions markerOption = new com.amap.api.maps.model.MarkerOptions()
////                        .icon(BitmapDescriptorFactory.fromBitmap(decodeResource))
//                .icon(com.amap.api.maps.model.BitmapDescriptorFactory.fromBitmap(bm))
//                .position(new com.amap.api.maps.model.LatLng(Double.parseDouble(zone.split("-")[2]), Double.parseDouble(zone.split("-")[3]))).anchor(0.5f, 0.5f)
//                .draggable(false);
//
//        marker = mMap.addMarker(markerOption);
////            marker.showInfoWindow();
//
//        CircleOptions circleOptions=new CircleOptions().center(new com.amap.api.maps.model.LatLng(Double.parseDouble(zone.split("-")[2]), Double.parseDouble(zone.split("-")[3])))
//                .radius(200).strokeColor(Color.argb(100, 252, 204, 159))
//                .fillColor(Color.argb(100, 252, 204, 159)).strokeWidth(0);
//
//        circleOptionsList.add(circleOptions);
//
//        // 绘制一个圆形
//        aMap.addCircle(circleOptions);
//
//
//
//
//        TextView textView = (TextView) view.findViewById(R.id.label);
//        textView.setVisibility(View.VISIBLE);
//        textView.setText(zone.split("-")[1]);
//        bm = convertViewToBitmap(view);
//        marker.setIcon(com.amap.api.maps.model.BitmapDescriptorFactory.fromBitmap(bm));

        LatLng center=new LatLng(Double.parseDouble(zone.split("-")[2]), Double.parseDouble(zone.split("-")[3]));
        mCircle = mMap.addCircle(new CircleOptions()
                .center(center)
                .radius(Double.parseDouble(zone.split("-")[4]))
                .strokeWidth(0)
                .strokeColor(Color.argb(100, 252, 204, 159))
                .fillColor(Color.argb(100, 252, 204, 159))
                .clickable(true));

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
