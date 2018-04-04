package app.conqueror.com.zhengzaipai.mainfragment.LockDevice.mirror.MonitorActivity;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.List;

import app.conqueror.com.zhengzaipai.App;
import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.app.map.FileListActivity;
import app.conqueror.com.zhengzaipai.base.BaseActivity;
import app.conqueror.com.zhengzaipai.entity.Device;
import app.conqueror.com.zhengzaipai.mainfragment.onway.GCFragment;
import app.conqueror.com.zhengzaipai.mainfragment.onway.TestFragment;
import app.conqueror.com.zhengzaipai.util.SpUtil;
import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * 演示poi搜索功能
 */
public class MonitorActivity extends BaseActivity<MonitorPresenter, MonitorModel> implements MonitorContract.View, View.OnClickListener {
    @Override
    public void onClick(View view) {

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
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initPresenter() {

    }

//
//
//    @Bind(R.id.speak_layout)
//    RelativeLayout speakLayout;
//    @Bind(R.id.takep_layout)
//    RelativeLayout takepLayout;
//    @Bind(R.id.takev_layout)
//    RelativeLayout takevLayout;
//    @Bind(R.id.content)
//    FrameLayout content;
//    @Bind(R.id.speak_text)
//    TextView speakText;
//    @Bind(R.id.takep_text)
//    TextView takepText;
//    @Bind(R.id.takev_text)
//    TextView takevText;
//    private BaiduMap mBaiduMap = null;
//
//
//
//    Fragment speakFragment,takepFragment,takevFragment;
//
//    LatLng center = new LatLng(39.92235, 116.380338);
//
//
//    int searchType = 0;  // 搜索的类型，在显示时区分
//
//
//    // 定位相关
//    LocationClient mLocClient;
//    public MyLocationListenner myListener = new MyLocationListenner();
//    private MyLocationConfiguration.LocationMode mCurrentMode;
//    BitmapDescriptor mCurrentMarker;
//    private static final int accuracyCircleFillColor = 0xAAFFFF88;
//    private static final int accuracyCircleStrokeColor = 0xAA00FF00;
//    TextureMapView mMapView;
//    boolean isFirstLoc = true; // 是否首次定位
//
//
//
//
//    /**
//     * 用于对Fragment进行管理
//     */
//    private FragmentManager fragmentManager;
//
//
//    Marker markermy;
//
//    WatchDevice device;
//
//    /**
//     * 改变城市
//     */
//    public static final int MSG_UPDATE_CITY = 0x001;
//
//
//    @Override
//    protected void onPause() {
//        mMapView.onPause();
////        mMapView.setVisibility(View.INVISIBLE);
//        super.onPause();
//    }
//
//    @Override
//    protected void onResume() {
//        mMapView.onResume();
////        mMapView.setVisibility(View.VISIBLE);
//        super.onResume();
//    }
//
//    @Override
//    public int getLayoutId() {
//        return R.layout.activity_monitor;
//    }
//
//    @Override
//    public void initView() {
//
//        getWindow().setFormat(PixelFormat.TRANSLUCENT);
//
//        device = SpUtil.getDevice();
//
//        bar_left.setVisibility(View.VISIBLE);
//
//        bar_left.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                MonitorActivity.this.finish();
//            }
//        });
////        bar_right.setVisibility(View.VISIBLE);
////        bar_right.setText("历史");
//
//        bar_right.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                App.showText("送到到设备" + markermy.getPosition());
//            }
//        });
//
//
//        // 地图初始化
//        mMapView = (TextureMapView) findViewById(R.id.map);
////        mBaiduMap = ((SupportMapFragment) (getSupportFragmentManager()
////                                                   .findFragmentById(R.id.map))).getBaiduMap();
//        mBaiduMap = mMapView.getMap();
//
//
//        mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
//
//
//        // 开启定位图层
//        mBaiduMap.setMyLocationEnabled(true);
//        // 定位初始化
//        mLocClient = new LocationClient(this);
//        mLocClient.registerLocationListener(myListener);
//        LocationClientOption option = new LocationClientOption();
//        option.setOpenGps(true); // 打开gps
//        option.setCoorType("bd09ll"); // 设置坐标类型
//        option.setScanSpan(1000);
//        option.setIsNeedAddress(true);
//        mLocClient.setLocOption(option);
//        mLocClient.start();
//
//
//        //对 marker 添加点击相应事件
//        //对 marker 添加点击相应事件
//        mBaiduMap.setOnMarkerDragListener(new BaiduMap.OnMarkerDragListener() {
//            @Override
//            public void onMarkerDrag(Marker marker) {
//
//            }
//
//            @Override
//            public void onMarkerDragEnd(Marker marker) {
//                if (marker.getTitle().equals("poicenter")) {
//                    center = marker.getPosition();
//
////                    markermy.setPosition(center);
//
//                    MapStatus.Builder builder = new MapStatus.Builder();
//                    builder.target(center);
//                    mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
//                }
//            }
//
//            @Override
//            public void onMarkerDragStart(Marker marker) {
//
//            }
//        });
//
//
//
//
//        fragmentManager = getSupportFragmentManager();
//        // 第一次启动时选中第0个tab
//        setTabSelection(0);
//
//        speakLayout.setOnClickListener(this);
//        takepLayout.setOnClickListener(this);
//        takevLayout.setOnClickListener(this);
//
//
////        // 获取TabHost对象
////        tabHost = (TabHost) findViewById(R.id.tabhost);
////
////
////
////        // 如果没有继承TabActivity时，通过该种方法加载启动tabHost
////        tabHost.setup();
////        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("语音")
////                .setContent(R.id.tab1));
////
////        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("拍照")
////                .setContent(R.id.tab2));
////
////        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("视频")
////                .setContent(R.id.tab3));
////
////
////        TabWidget tabWidget = tabHost.getTabWidget();
//////        tabWidget.setBackgroundResource(R.drawable.shape);
////        // Change strip(tab bottom line) color
////        for(int i=0; i < tabWidget.getChildCount(); i++){
////            tabWidget.getChildAt(i).setBackgroundResource(R.drawable.tab_indicator_ab_mmstyle);
////            TextView textView= (TextView) tabWidget.getChildAt(i).findViewById(android.R.id.title);
////            textView.setTextColor(getResources().getColor(R.color.white));
////        }
//
//
//    }
//
//    @Override
//    public void initPresenter() {
//        mPresenter.setVM(this, mModel, this);
//    }
//
//
//    @Override
//    protected void onDestroy() {
//
//        // 退出时销毁定位
//        mLocClient.stop();
//        // 关闭定位图层
//        mBaiduMap.setMyLocationEnabled(false);
//        mMapView.onDestroy();
//        mMapView = null;
//
//
//        super.onDestroy();
//    }
//
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//    }
//
//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//    }
//
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.speak_layout:
//                // 当点击了消息tab时，选中第1个tab
//                setTabSelection(0);
//                break;
//            case R.id.takep_layout:
//                // 当点击了联系人tab时，选中第2个tab
//                setTabSelection(1);
//                break;
//            case R.id.takev_layout:
//                // 当点击了动态tab时，选中第3个tab
//                setTabSelection(2);
//                break;
//            default:
//                break;
//        }
//    }
//
//    public void setTabSelection(int tabSelection) {
//        // 每次选中之前先清楚掉上次的选中状态
//        clearSelection();
//        // 开启一个Fragment事务
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
//        hideFragments(transaction);
//        switch (tabSelection) {
//            case 0:
//                // 当点击了消息tab时，改变控件的图片和文字颜色
//
//                speakLayout.setBackgroundResource(R.drawable.shap_left_check);
//                speakText.setTextColor(getResources().getColor(R.color.theme_black_7f));
//                if (speakFragment == null) {
//                    // 如果MessageFragment为空，则创建一个并添加到界面上
//                    speakFragment = new SpeakFragment();
//                    transaction.add(R.id.content, speakFragment);
//                } else {
////                     如果MessageFragment不为空，则直接将它显示出来
//                    transaction.show(speakFragment);
//                }
//                break;
//            case 1:
//                // 当点击了联系人tab时，改变控件的图片和文字颜色
//                takepLayout.setBackgroundResource(R.color.colorPrimary);
//                takepText.setTextColor(getResources().getColor(R.color.theme_black_7f));
//                if (takepFragment == null) {
//                    // 如果ContactsFragment为空，则创建一个并添加到界面上
//                    takepFragment = new TakePFragment();
//                    transaction.add(R.id.content, takepFragment);
//                } else {
//                    // 如果ContactsFragment不为空，则直接将它显示出来
//                    transaction.show(takepFragment);
//                }
//                break;
//            case 2:
//                // 当点击了动态tab时，改变控件的图片和文字颜色
//                takevLayout.setBackgroundResource(R.drawable.shap_right_check);
//                takevText.setTextColor(getResources().getColor(R.color.theme_black_7f));
//
//                if (takevFragment == null) {
//                    // 如果NewsFragment为空，则创建一个并添加到界面上
//                    takevFragment = new TakeVFragment();
//                    transaction.add(R.id.content, takevFragment);
//                } else {
//                    // 如果NewsFragment不为空，则直接将它显示出来
//                    transaction.show(takevFragment);
//                }
//                break;
//
//        }
//        transaction.commit();
//    }
//
//
//    /**
//     * 清除掉所有的选中状态。
//     */
//    private void clearSelection() {
//        speakLayout.setBackgroundResource(R.drawable.shap_left);
//        speakText.setTextColor(getResources().getColor(R.color.colorPrimary));
//
//        takepLayout.setBackgroundResource(R.color.theme_black_7f);
//        takepText.setTextColor(getResources().getColor(R.color.colorPrimary));
//
//        takevLayout.setBackgroundResource(R.drawable.shap_right);
//        takevText.setTextColor(getResources().getColor(R.color.colorPrimary));
//
//    }
//
//    /**
//     * 将所有的Fragment都置为隐藏状态。
//     *
//     * @param transaction
//     *            用于对Fragment执行操作的事务
//     */
//    private void hideFragments(FragmentTransaction transaction) {
//
//        if (speakFragment != null) {
//            transaction.hide(speakFragment);
//        }
//        if (takepFragment != null) {
//            transaction.hide(takepFragment);
//        }
//        if (takevFragment != null) {
//            transaction.hide(takevFragment);
//        }
//
//    }
//
//
//
//    /**
//     * 定位SDK监听函数
//     */
//    public class MyLocationListenner implements BDLocationListener {
//
//        @Override
//        public void onReceiveLocation(BDLocation location) {
//            // map view 销毁后不在处理新接收的位置
//            if (location == null || mMapView == null) {
//                return;
//            }
//
//
//            MyLocationData locData = new MyLocationData.Builder()
////                    .accuracy(location.getRadius())
//                    // 此处设置开发者获取到的方向信息，顺时针0-360
////                    .direction(100)
//                    .latitude(location.getLatitude())
//                    .longitude(location.getLongitude()).build();
//            mBaiduMap.setMyLocationData(locData);
//            if (isFirstLoc) {
//                isFirstLoc = false;
//                //居中和放大
//                LatLng ll = new LatLng(location.getLatitude(),
//                        location.getLongitude());
//                MapStatus.Builder builder = new MapStatus.Builder();
//                builder.target(ll).zoom(19);
//                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
//
//
//                //定义Maker坐标点
//                BitmapDescriptor bitmap = BitmapDescriptorFactory
//                        .fromResource(R.mipmap.icon_gcoding);
//                OverlayOptions option = new MarkerOptions()
//                        .position(ll).draggable(true).title("poicenter")
//                        .icon(bitmap);
////                mBaiduMap.addOverlay(option);
//                markermy = (Marker) (mBaiduMap.addOverlay(option));
//                center = ll;
//
//
//                Message msg = new Message();
//                msg.what = MSG_UPDATE_CITY;
//                msg.obj = location.getCity().toString();
//                mHandler.sendMessage(msg);
//
//
//            }
//        }
//
//        @Override
//        public void onConnectHotSpotMessage(String s, int i) {
//            App.showText(s + "i=" + i);
//
//        }
//
//        public void onReceivePoi(BDLocation poiLocation) {
//        }
//    }
//
//    private Handler mHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            if (msg.what == MSG_UPDATE_CITY) {
//                //更新文件发送进度
//                String city = msg.obj.toString();
//
//            }
//        }
//    };
//
//
// /*   public void onclick_do(View view) {
//        switch (view.getTag().toString()) {
//            case "send_msg":
//                if (sendmsgEt.getText().equals(""))
//                    showMsg("输入不能为空");
//                else {
//                    mPresenter.speekWords(SpUtil.getXGToken(), device.parameterid, sendmsgEt.getText().toString());
//                    sendmsgEt.setText("");
//                }
//                break;
//            case "get_photo":
//                mPresenter.takePhoto(SpUtil.getXGToken(), device.parameterid);
//                break;
//            case "photo_history":
//                Intent photoIntent = new Intent(MonitorActivity.this, FileListActivity.class);
//                //用Bundle携带数据
//                Bundle photoBundle = new Bundle();
//                //传递name参数为tinyphp
//                photoBundle.putString("type", "image");
//                photoIntent.putExtras(photoBundle);
//
//                startActivity(photoIntent);
//                break;
//            case "get_video":
//                mPresenter.takeVideo(SpUtil.getXGToken(), device.parameterid);
//                break;
//            case "video_history":
//                Intent videoIntent = new Intent(MonitorActivity.this, FileListActivity.class);
//                //用Bundle携带数据
//                Bundle videoBundle = new Bundle();
//                //传递name参数为tinyphp
//                videoBundle.putString("type", "video");
//                videoIntent.putExtras(videoBundle);
//
//                startActivity(videoIntent);
//                break;
//        }
//    }*/
//
//
//    @Override
//    public void showDialog() {
//        showBaseDialog();
//    }
//
//
//    @Override
//    public void hideDialog() {
//        hideBaseDialog();
//    }
//
//    @Override
//    public void showMsg(String msg) {
//        App.showText(msg);
//    }
//
//    @Override
//    public void initDialog() {
//        initBaseDialog();
//    }
}
