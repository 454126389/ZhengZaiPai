package app.conqueror.com.zhengzaipai.mainfragment.LockDevice.mirror.PoiRec;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;



import java.io.File;

import app.conqueror.com.zhengzaipai.App;
import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.app.SetFragment.SettingActivity;
import app.conqueror.com.zhengzaipai.app.map.FileListActivity;
import app.conqueror.com.zhengzaipai.base.BaseActivity;
import app.conqueror.com.zhengzaipai.mainfragment.LockDevice.LockModel;
import app.conqueror.com.zhengzaipai.mainfragment.LockDevice.LockPresenter;
import app.conqueror.com.zhengzaipai.util.AudioRecoderUtils;
import app.conqueror.com.zhengzaipai.util.DateUtils;
import app.conqueror.com.zhengzaipai.util.MyUitls;
import app.conqueror.com.zhengzaipai.util.PopupWindowFactory;
import app.conqueror.com.zhengzaipai.util.TimeUtils;
import app.conqueror.com.zhengzaipai.util.voice.VoiceCallBack;
import app.conqueror.com.zhengzaipai.util.voice.VoiceManager;
import butterknife.Bind;
import butterknife.ButterKnife;

import static app.conqueror.com.zhengzaipai.util.NetWorkUtil.fileUrl;


/**
 * 演示poi搜索功能
 */
public class PoiRecActivity extends BaseActivity<LockPresenter, LockModel> {
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
//    @Bind(R.id.rec_do)
//    Button recDo;
//    @Bind(R.id.rl)
//    LinearLayout rl;
//
//    ImageView buttonRec;
//    private LinearLayout mLayoutRecord;
//    private LinearLayout mLayoutPlay;
//    private VoiceManager voiceManager;
//    private String mRecPath = "";
//
//
//    private BaiduMap mBaiduMap = null;
//
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
//    Marker markermy;
//
//    /**
//     * 改变城市
//     */
//    public static final int MSG_UPDATE_CITY = 0x001;
//
//
//    private InfoWindow mInfoWindow;
//    static final int VOICE_REQUEST_CODE = 66;
//    private PopupWindowFactory mPop;
//    private AudioRecoderUtils mAudioRecoderUtils;
//    private ImageView mImageView;
//    private TextView mTextView;
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
//        return R.layout.activity_poirec;
//    }
//
//    @Override
//    public void initView() {
//        getWindow().setFormat(PixelFormat.TRANSLUCENT);
//        mAudioRecoderUtils = new AudioRecoderUtils();
//
//        //PopupWindow的布局文件
//        View recView = View.inflate(this, R.layout.layout_microphone, null);
//        mPop = new PopupWindowFactory(this, recView);
//        //PopupWindow布局文件里面的控件
//        mImageView = (ImageView) recView.findViewById(R.id.iv_recording_icon);
//        mTextView = (TextView) recView.findViewById(R.id.tv_recording_time);
//
//
//        mLayoutRecord = (LinearLayout) findViewById(R.id.layout_record);
//        mLayoutPlay = (LinearLayout) findViewById(R.id.layout_play);
//        String fileName=Environment.getExternalStorageDirectory()+"/cache/";
//
//        voiceManager = new VoiceManager(this, fileName);
//        voiceManager.setVoiceListener(new VoiceCallBack() {
//            @Override
//            public void voicePath(String path) {
//                mRecPath = path;
//            }
//
//            @Override
//            public void recFinish() {
////                mBtPlay.setVisibility(View.VISIBLE);
//                buttonRec.setVisibility(View.GONE);
//                buttonRec.setVisibility(View.VISIBLE);
//            }
//        });
//
//
//
//        buttonRec = (ImageView) findViewById(R.id.button_rec);
//        buttonRec.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mLayoutRecord.setVisibility(View.VISIBLE);
//                buttonRec.setVisibility(View.GONE);
//
//
//
//                voiceManager.sessionRecord(true);
//            }
//        });
//
//        //录音回调
//        mAudioRecoderUtils.setOnAudioStatusUpdateListener(new AudioRecoderUtils.OnAudioStatusUpdateListener() {
//
//            //录音中....db为声音分贝，time为录音时长
//            @Override
//            public void onUpdate(double db, long time) {
//                mImageView.getDrawable().setLevel((int) (3000 + 6000 * db / 100));
//                mTextView.setText(TimeUtils.long2String(time));
//            }
//
//            //录音结束，filePath为保存路径
//            @Override
//            public void onStop(String filePath) {
//
//                new AlertDialog.Builder(PoiRecActivity.this)
//                        .setTitle("确认")
//                        .setMessage("是否保存并上传该数据")
//                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//
//                                Toast.makeText(PoiRecActivity.this, "录音保存在：" + filePath, Toast.LENGTH_SHORT).show();
//                                mTextView.setText(TimeUtils.long2String(0));
//
////                        MarkerOptions ooA = new MarkerOptions().position(nowPoi).icon(bdA)
////                                .zIndex(9).draggable(true);
////                        mMarkerA = (Marker) (mBaiduMap.addOverlay(ooA));
////                        mMarkerA.setTitle(filePath);
//                                //定义Maker坐标点
//                                BitmapDescriptor bitmap = BitmapDescriptorFactory
//                                        .fromResource(R.mipmap.icon_gcoding);
//                                OverlayOptions option = new MarkerOptions()
//                                        .position(center).draggable(true).title("filePath")
//                                        .icon(bitmap);
//                                mBaiduMap.addOverlay(option);
//                            }
//                        }).setNegativeButton("取消", null)
//                        .show();
//
//
//            }
//
//
//        });
//
//        //6.0以上需要权限申请
//        requestPermissions();
//
//
//        bar_left.setVisibility(View.VISIBLE);
//
//        bar_left.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                PoiRecActivity.this.finish();
//            }
//        });
////        bar_right.setVisibility(View.VISIBLE);
////        bar_right.setText("历史");
//
//        bar_right.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(PoiRecActivity.this, FileListActivity.class);
//                //用Bundle携带数据
//                Bundle bundle = new Bundle();
//                //传递name参数为tinyphp
//                bundle.putString("type", "sound");
//                intent.putExtras(bundle);
//
//                startActivity(intent);
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
//        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
//            @Override
//            public boolean onMarkerClick(Marker marker) {
//
//                if (marker.getTitle() != null) {
//
//                    Button button = new Button(getApplicationContext());
//                    button.setBackgroundResource(R.mipmap.popup);
//                    InfoWindow.OnInfoWindowClickListener listener = null;
//                    button.setText("播放录音");
//                    listener = new InfoWindow.OnInfoWindowClickListener() {
//                        public void onInfoWindowClick() {
//                            Intent it = MyUitls.getAudioFileIntent(marker.getTitle());
//                            startActivity(it);
//                            mBaiduMap.hideInfoWindow();
//                        }
//                    };
//                    LatLng ll = marker.getPosition();
//                    mInfoWindow = new InfoWindow(BitmapDescriptorFactory.fromView(button), ll, -47, listener);
//                    mBaiduMap.showInfoWindow(mInfoWindow);
//                }
//
//                return true;
//            }
//        });
//
//    }
//
//    @Override
//    public void initPresenter() {
//
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
//    public void onclick_do(View view) {
//        switch (view.getId())
//        {
///*            case R.id.button_rec:
//                mLayoutRecord.setVisibility(View.VISIBLE);
////                mLayoutPlay.setVisibility(View.GONE);
////                mBtPlay.setVisibility(View.INVISIBLE);
////
////                voiceManager.sessionRecord(true);
//                break;*/
//        }
//    /*    switch (view.getTag().toString()) {
//            case "rec_setting":
//                Intent settingIntent = new Intent(PoiRecActivity.this, SettingActivity.class);
//                //用Bundle携带数据
//                Bundle settingBundle = new Bundle();
//                //传递name参数为tinyphp
//                settingBundle.putString("settype", "poirecset");
//                settingIntent.putExtras(settingBundle);
//                startActivity(settingIntent);
//                break;
//        }*/
//    }
//
//
//    /**
//     * 开启扫描之前判断权限是否打开
//     */
//    private void requestPermissions() {
//        //判断是否开启摄像头权限
//        if ((ContextCompat.checkSelfPermission(PoiRecActivity.this,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) &&
//                (ContextCompat.checkSelfPermission(PoiRecActivity.this,
//                        Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED)
//                ) {
//            StartListener();
//
//            //判断是否开启语音权限
//        } else {
//            //请求获取摄像头权限
//            ActivityCompat.requestPermissions((Activity) PoiRecActivity.this,
//                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, VOICE_REQUEST_CODE);
//        }
//
//    }
//
//
//    public void StartListener() {
//        //Button的touch监听
//        recDo.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//                switch (event.getAction()) {
//
//                    case MotionEvent.ACTION_DOWN:
//
//                        mPop.showAtLocation(rl, Gravity.CENTER, 0, 0);
//
//                        recDo.setText("松开保存");
//                        mAudioRecoderUtils.startRecord();
//
//
//                        break;
//
//                    case MotionEvent.ACTION_UP:
//
//                        mAudioRecoderUtils.stopRecord();        //结束录音（保存录音文件）
////                        mAudioRecoderUtils.cancelRecord();    //取消录音（不保存录音文件）
//                        mPop.dismiss();
//                        recDo.setText("按住说话");
//
//                        break;
//                }
//                return true;
//            }
//        });
//
//    }
}