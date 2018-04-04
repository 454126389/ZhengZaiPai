package app.conqueror.com.zhengzaipai.mainfragment.watch.map;

import android.animation.Animator;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import app.conqueror.com.zhengzaipai.App;
import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.base.BaseFragment;
import app.conqueror.com.zhengzaipai.mainfragment.LockDevice.mirror.shop.ServiceShopActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.MainContract;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.AddDevice.AddDeviceActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.DeviceFragment;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WatchPoiEntity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.map.trail.TrailActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.map.zonelist.ZoneListActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.me.MultipleItem;
import app.conqueror.com.zhengzaipai.mainfragment.watch.me.MultipleItemQuickAdapter;
import app.conqueror.com.zhengzaipai.util.Actions;
import app.conqueror.com.zhengzaipai.util.SpUtil;
import butterknife.ButterKnife;

public class MapFragment extends BaseFragment implements MainContract.View, View.OnClickListener, GeocodeSearch.OnGeocodeSearchListener {

    RecyclerView recyclerView;
    TextView btnStyle;
    TextView btnTrail;
    TextView btnSafeZone;
    ImageButton btn_refresh;


    private MapView mapView;
    private AMap aMap;
    private UiSettings mUiSettings;
    protected MainContract.Presenter mPresenter;

    ImageButton home_title_bar_btn_add;
    List<MultipleItem> list = new ArrayList<>();

    private ProgressDialog progDialog = null;
    private GeocodeSearch geocoderSearch;

    WatchPoiEntity watchPoi;
    ImageView home_title_bar_head_img;
    TextView home_title_bar_tv_name;
    ImageView btn_location;

    boolean isStyle=false;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        initview(savedInstanceState, view);
//        mPresenter.getWatchPoi("2016000891");

        home_title_bar_btn_add = (ImageButton) view.findViewById(R.id.home_title_bar_btn_add);
        btn_refresh = (ImageButton) view.findViewById(R.id.btn_refresh);
        home_title_bar_btn_add.setOnClickListener(this);
        home_title_bar_head_img = (ImageView) view.findViewById(R.id.home_title_bar_head_img);
        home_title_bar_tv_name = (TextView) view.findViewById(R.id.home_title_bar_tv_name);
        home_title_bar_head_img.setOnClickListener(this);
        btn_location = (ImageView) view.findViewById(R.id.btn_location);
        btn_location.setOnClickListener(this);
        btn_refresh.setOnClickListener(this);
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_map;
    }

    private void initview(Bundle savedInstanceState, View view) {
        mapView = (MapView) view.findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap = mapView.getMap();
            mUiSettings = aMap.getUiSettings();
        }

        mUiSettings.setZoomControlsEnabled(false);

        btnStyle = (TextView) view.findViewById(R.id.btn_style);
        btnTrail = (TextView) view.findViewById(R.id.btn_trail);
        btnSafeZone = (TextView) view.findViewById(R.id.btn_safe_zone);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));

        btnStyle.setOnClickListener(this);
        btnTrail.setOnClickListener(this);
        btnSafeZone.setOnClickListener(this);

        progDialog = new ProgressDialog(getActivity());
        geocoderSearch = new GeocodeSearch(getActivity());
        geocoderSearch.setOnGeocodeSearchListener(this);



    }


    /**
     * 必须重写以下方法
     */
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();


        //实例化BroadcastReceiver子类 &  IntentFilter
        Receiver mBroadcastReceiver = new Receiver();
        IntentFilter intentFilter = new IntentFilter();

        //设置接收广播的类型
        intentFilter.addAction(Actions.ACTION_CHANGE_GPS);

        //调用Context的registerReceiver（）方法进行动态注册
        getActivity().registerReceiver(mBroadcastReceiver, intentFilter);


    }

    public class Receiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Actions.ACTION_CHANGE_GPS))
            {
                WatchPoiEntity watchPoi = (WatchPoiEntity) intent.getExtras().get("watchPoi");
//            tvData.setText(step);
                aMap.clear();
                setWatchPoi(watchPoi);
            }
        }
    }
//    onCreateAnimation


    @Override
    public Animator onCreateAnimator(int transit, boolean enter, int nextAnim) {

        if(enter)
        {
            if(SpUtil.getAppUser().deviceList!=null)
            {aMap.clear();
            setWatchPoi(SpUtil.getWatchPoiEntity(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id));
            }
        }
        return super.onCreateAnimator(transit, enter, nextAnim);
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_refresh:
//                http://61.131.68.156:8081/order/cr?id=2016000894
                getOnButtonClick().onClick(0,null,null);

                break;
            case R.id.btn_style:
                if(isStyle)
                {
                    aMap.clear();
                    addMarkersToMap(latlng);
                    aMap.moveCamera(CameraUpdateFactory.changeLatLng(latlng));
                }
                else
                {

//                    if (SpUtil.getAppUser().deviceList.size()>1)
//                    {
                        aMap.clear();
                        LatLngBounds.Builder boundsBuilder=new LatLngBounds.Builder();
                        for (int i=0;i<SpUtil.getAppUser().deviceList.size();i++)
                        {
                            WatchPoiEntity watchPoiEntity= SpUtil.getWatchPoiEntity(SpUtil.getAppUser().deviceList.get(i).id);
                            addMarkersToMap(new LatLng(watchPoiEntity.getLat(), watchPoiEntity.getLng()));
                            boundsBuilder.include(new LatLng(watchPoiEntity.getLat(), watchPoiEntity.getLng()));
                        }
                            boundsBuilder.include(latlng);
                        aMap.moveCamera(CameraUpdateFactory.newLatLngBounds(boundsBuilder.build(),20));
                        isStyle=!isStyle;
//                    }else
//                    {
//                        aMap.clear();
//                        addMarkersToMap(latlng);
//                        aMap.moveCamera(CameraUpdateFactory.changeLatLng(latlng));
//                    }

                }

            break;

            case R.id.btn_trail:
                startActivity(new Intent(getActivity(), TrailActivity.class).putExtra("watchPoi", watchPoi));
                break;

            case R.id.btn_safe_zone:
                Intent intent = new Intent(getActivity(), ZoneListActivity.class);
//                String url = "http://sim.conqueror.cn/alipay/index.php/Shop?cut=4&did=12121212";
                // url可以是一号店或者淘宝等第三方的购物wap站点，在该网站的支付过程中，支付宝sdk完成拦截支付
                intent.putExtra("watchPoi", watchPoi);
                startActivity(intent);
//                startActivity(new Intent(getActivity(), ZoneListActivity.class));
                break;
            case R.id.home_title_bar_btn_add:
                startActivity(new Intent(getActivity(), AddDeviceActivity.class));
                break;
            case R.id.home_title_bar_head_img:
                showPopupMenu(view);
                break;
            case R.id.btn_location:
                aMap.moveCamera(CameraUpdateFactory.changeLatLng(latlng));
                break;

        }
    }



    private void showPopupMenu(View view) {
        // View当前PopupMenu显示的相对View的位置
        PopupMenu popupMenu = new PopupMenu(getActivity(), view);

        Menu menu = popupMenu.getMenu();

        for (int i=0;i<SpUtil.getAppUser().deviceList.size();i++)
        {
            menu.add(Menu.NONE, Menu.FIRST + i, i, SpUtil.getAppUser().deviceList.get(i).nickName);
        }
        // 通过代码添加菜单项
//        menu.add(Menu.NONE, Menu.FIRST + 0, 0, "复制");
//        menu.add(Menu.NONE, Menu.FIRST + 1, 1, "粘贴");

        // menu布局
        popupMenu.getMenuInflater().inflate(R.menu.popupmenu, popupMenu.getMenu());
        // menu的item点击事件
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(getActivity(), item.getTitle(), Toast.LENGTH_SHORT).show();
                SpUtil.setChoise(item.getItemId()-1);
//                setWatchPoiMsg(SpUtil.getWatchPoiEntity(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id));
                setWatchPoi(SpUtil.getWatchPoiEntity(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id));
                return false;
            }
        });
        // PopupMenu关闭事件
        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {
//                Toast.makeText(getActivity(), "关闭PopupMenu", Toast.LENGTH_SHORT).show();
            }
        });
        popupMenu.show();
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.mPresenter = presenter;
    }


    private MarkerOptions markerOption;
    private LatLng latlng;
//            = new LatLng(39.761, 116.434);

    @Override
    public void setWatchPoi(WatchPoiEntity poi) {


        aMap.clear();

        home_title_bar_tv_name.setText(SpUtil.getAppUser().deviceList.get(SpUtil.getChoise()).nickName);
        latlng = new LatLng(poi.getLat(), poi.getLng());
        aMap.moveCamera(CameraUpdateFactory.changeLatLng(latlng));
        aMap.moveCamera(CameraUpdateFactory.zoomTo(18));
        addMarkersToMap(latlng);

        watchPoi = poi;

        getAddress(new LatLonPoint(poi.getLat(), poi.getLng()));


    }

    /**
     * 在地图上添加marker
     */
    private void addMarkersToMap(LatLng tlatlng) {

        View view = View.inflate(getActivity(),R.layout.layout_marker, null);

        TextView label = (TextView) view.findViewById(R.id.label);
        ImageView background = (ImageView) view.findViewById(R.id.background);
        ImageView headimg = (ImageView) view.findViewById(R.id.headimg);

//        tv_price.setText(positionEneityList.get(i).getPrice());

        Bitmap bm=convertViewToBitmap(view);
        markerOption = new MarkerOptions()
//                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .icon(BitmapDescriptorFactory.fromBitmap(bm))
                .position(tlatlng)
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


    /**
     * 响应逆地理编码
     */
    public void getAddress(final LatLonPoint latLonPoint) {
//        showDialog();
        RegeocodeQuery query = new RegeocodeQuery(latLonPoint, 200,
                GeocodeSearch.AMAP);// 第一个参数表示一个Latlng，第二参数表示范围多少米，第三个参数表示是火系坐标系还是GPS原生坐标系
        geocoderSearch.getFromLocationAsyn(query);// 设置异步逆地理编码请求
    }


    /**
     * 显示进度条对话框
     */
    public void showDialog() {
        progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progDialog.setIndeterminate(false);
        progDialog.setCancelable(true);
        progDialog.setMessage(getString(R.string.tip_searching));
        progDialog.show();
    }

    /**
     * 隐藏进度条对话框
     */
    public void dismissDialog() {
        if (progDialog != null) {
            progDialog.dismiss();
        }
    }


//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // TODO: inflate a fragment view
//        View rootView = super.onCreateView(inflater, container, savedInstanceState);
//        ButterKnife.bind(this, rootView);
//        return rootView;
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // TODO: inflate a fragment view
//        View rootView = super.onCreateView(inflater, container, savedInstanceState);
//        ButterKnife.bind(this, rootView);
//        return rootView;
//    }

    @Override
    public void onRegeocodeSearched(RegeocodeResult result, int rCode) {
        dismissDialog();
        if (rCode == AMapException.CODE_AMAP_SUCCESS) {
            if (result != null && result.getRegeocodeAddress() != null
                    && result.getRegeocodeAddress().getFormatAddress() != null) {
                String addressName = result.getRegeocodeAddress().getFormatAddress()
                        + getString(R.string.tip_near);
//                aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
//                        AMapUtil.convertToLatLng(latLonPoint), 15));
//                regeoMarker.setPosition(AMapUtil.convertToLatLng(latLonPoint));
//                App.showText(addressName);

                list.clear();
                list.add(new MultipleItem(MultipleItem.ITEM_MAP, MultipleItem.IMG_SPAN_SIZE, watchPoi, getString(R.string.tip_watch_poi), addressName));
                final MultipleItemQuickAdapter multipleItemAdapter = new MultipleItemQuickAdapter(getActivity(), list);
                multipleItemAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                        return list.get(position).getSpanSize();
                    }
                });

                recyclerView.setAdapter(multipleItemAdapter);


            } else {
                App.showText("no_result");
                ;
            }
        } else {
//            App.showText("rCode="+rCode);;
        }
    }

    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

    }


    private MapFragment.OnButtonClick onButtonClick;//2、定义接口成员变量


    //定义接口变量的get方法
    public MapFragment.OnButtonClick getOnButtonClick() {
        return onButtonClick;
    }

    //定义接口变量的set方法
    public void setOnButtonClick(MapFragment.OnButtonClick onButtonClick) {
        this.onButtonClick = onButtonClick;
    }


    //1、定义接口
    public interface OnButtonClick {
        public void onClick(int position, String id, String content);
    }



}
