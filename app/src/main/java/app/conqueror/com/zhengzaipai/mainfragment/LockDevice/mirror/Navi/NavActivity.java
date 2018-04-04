package app.conqueror.com.zhengzaipai.mainfragment.LockDevice.mirror.Navi;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import app.conqueror.com.zhengzaipai.App;
import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.app.map.PoiAdapter;
import app.conqueror.com.zhengzaipai.base.BaseActivity;
import app.conqueror.com.zhengzaipai.mapbean.PoiOverlay;

public class NavActivity extends Activity {
/**
 * 演示poi搜索功能
 */
//public class NavActivity extends BaseActivity implements
//        OnGetPoiSearchResultListener, OnGetSuggestionResultListener {
//
//    private ListView lv_location_nearby = null;
//    private PoiSearch mPoiSearch = null;
//    private SuggestionSearch mSuggestionSearch = null;
//    private BaiduMap mBaiduMap = null;
//    private List<String> suggest;
//    /**
//     * 搜索关键字输入窗口
//     */
//    private EditText editCity = null;
//    private AutoCompleteTextView keyWorldsView = null;
//    private ArrayAdapter<String> sugAdapter = null;
//    private int loadIndex = 0;
//
//    LatLng center = new LatLng(39.92235, 116.380338);
//    int radius = 500;
//    LatLng southwest = new LatLng( 39.92235, 116.380338 );
//    LatLng northeast = new LatLng( 39.947246, 116.414977);
//    LatLngBounds searchbound = new LatLngBounds.Builder().include(southwest).include(northeast).build();
//
//    int searchType = 0;  // 搜索的类型，在显示时区分
//
//
//
//    // 定位相关
//    LocationClient mLocClient;
//    public MyLocationListenner myListener = new MyLocationListenner();
//    private MyLocationConfiguration.LocationMode mCurrentMode;
//    BitmapDescriptor mCurrentMarker;
//    private static final int accuracyCircleFillColor = 0xAAFFFF88;
//    private static final int accuracyCircleStrokeColor = 0xAA00FF00;
//    MapView mMapView;
//    boolean isFirstLoc = true; // 是否首次定位
//
//
//
//    Marker markermy;
//
//    /**
//     * 改变城市
//     */
//    public static final int MSG_UPDATE_CITY =0x001 ;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_poisearch);
//        // 初始化搜索模块，注册搜索事件监听
//
//    }
//
//    @Override
//    protected void onPause() {
//        mMapView.onPause();
//        super.onPause();
//    }
//
//    @Override
//    protected void onResume() {
//
//        mMapView.onResume();
//        super.onResume();
//    }
//
//    @Override
//    public int getLayoutId() {
//        return R.layout.activity_poisearch;
//    }
//
//    @Override
//    public void initView() {
//        bar_left.setVisibility(View.VISIBLE);
//
//        bar_left.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavActivity.this.finish();
//            }
//        });
//        bar_right.setVisibility(View.VISIBLE);
//        bar_right.setText("发送");
//
//        bar_right.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                App.showText("送到到设备"+markermy.getPosition());
//            }
//        });
//
//        mPoiSearch = PoiSearch.newInstance();
//        mPoiSearch.setOnGetPoiSearchResultListener(this);
//
//        // 初始化建议搜索模块，注册建议搜索事件监听
//        mSuggestionSearch = SuggestionSearch.newInstance();
//        mSuggestionSearch.setOnGetSuggestionResultListener(this);
//
//        editCity = (EditText) findViewById(R.id.city);
//        keyWorldsView = (AutoCompleteTextView) findViewById(R.id.searchkey);
//        sugAdapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_dropdown_item_1line);
//        keyWorldsView.setAdapter(sugAdapter);
//        keyWorldsView.setThreshold(1);
//        // 地图初始化
//        mMapView = (MapView) findViewById(R.id.map);
////        mBaiduMap = ((SupportMapFragment) (getSupportFragmentManager()
////                                                   .findFragmentById(R.id.map))).getBaiduMap();
//        mBaiduMap = mMapView.getMap();
//
//        lv_location_nearby= (ListView) findViewById(R.id.lv_location_nearby);
//
//        editCity.clearFocus();
//
//        /**
//         * 当输入关键字变化时，动态更新建议列表
//         */
//        keyWorldsView.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void afterTextChanged(Editable arg0) {
//
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence arg0, int arg1,
//                                          int arg2, int arg3) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence cs, int arg1, int arg2,
//                                      int arg3) {
//                if (cs.length() <= 0) {
//                    return;
//                }
//
//                /**
//                 * 使用建议搜索服务获取建议列表，结果在onSuggestionResult()中更新
//                 */
//                mSuggestionSearch
//                        .requestSuggestion((new SuggestionSearchOption())
//                                .keyword(cs.toString()).city(editCity.getText().toString()));
//            }
//        });
//
//        mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
//
////        mCurrentMode = MyLocationConfiguration.LocationMode.FOLLOWING;
////        mBaiduMap
////                .setMyLocationConfigeration(new MyLocationConfiguration(
////                        mCurrentMode, true, mCurrentMarker));
////        mCurrentMarker = null;
////        mBaiduMap
////                .setMyLocationConfigeration(new MyLocationConfiguration(
////                        mCurrentMode, true, null));
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
//                if(marker.getTitle().equals("poicenter"))
//                {
//                    center=marker.getPosition();
//
////                    markermy.setPosition(center);
//
//                    MapStatus.Builder builder = new MapStatus.Builder();
//                    builder.target(center);
//                    mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
//                    searchNearbyProcess();
//                }
//            }
//
//            @Override
//            public void onMarkerDragStart(Marker marker) {
//
//            }
//        });
//    }
//
//    @Override
//    public void initPresenter() {
//
//    }
//
//
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
//        mPoiSearch.destroy();
//        mSuggestionSearch.destroy();
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
//    /**
//     * 响应城市内搜索按钮点击事件
//     *
//     * @param v
//     */
//    public void searchButtonProcess(View v) {
//        searchType = 1;
//        String citystr = editCity.getText().toString();
//        String keystr = keyWorldsView.getText().toString();
//        mPoiSearch.searchInCity((new PoiCitySearchOption())
//                .city(citystr).keyword(keystr).pageNum(loadIndex));
//    }
//
//    /**
//     * 响应周边搜索按钮点击事件
//     *
//     *
//     */
//    public void  searchNearbyProcess() {
//        searchType = 2;
//        PoiNearbySearchOption nearbySearchOption = new PoiNearbySearchOption().keyword(keyWorldsView.getText()
//                .toString()).sortType(PoiSortType.distance_from_near_to_far).location(center)
//                .radius(radius).pageNum(loadIndex);
//        mPoiSearch.searchNearby(nearbySearchOption);
//    }
//
//    public void goToNextPage(View v) {
//        loadIndex++;
//        if(searchType==2)
//            searchNearbyProcess();
//        else
//            searchButtonProcess(null);
//    }
//
//    /**
//     * 响应区域搜索按钮点击事件
//     *
//     * @param v
//     */
//    public void searchBoundProcess(View v) {
//        searchType = 3;
//        mPoiSearch.searchInBound(new PoiBoundSearchOption().bound(searchbound)
//                .keyword(keyWorldsView.getText().toString()));
//
//    }
//
//
//    /**
//     * 获取POI搜索结果，包括searchInCity，searchNearby，searchInBound返回的搜索结果
//     * @param result
//     */
//    public void onGetPoiResult(PoiResult result) {
//        if (result == null || result.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {
//            Toast.makeText(NavActivity.this, "未找到结果", Toast.LENGTH_LONG)
//                    .show();
//            return;
//        }
//        if (result.error == SearchResult.ERRORNO.NO_ERROR) {
////            lv_location_nearby.setVisibility(View.VISIBLE);
//            PoiAdapter poiAdapter=new PoiAdapter(NavActivity.this,result.getAllPoi());
//            lv_location_nearby.setAdapter(poiAdapter);
//            lv_location_nearby.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
////                    App.showText(""+result.getAllPoi().get(i).location);
////                    markermy.setPosition(result.getAllPoi().get(i).location);
//                    center=result.getAllPoi().get(i).location;
//
//                    markermy.setPosition(center);
//
//                    MapStatus.Builder builder = new MapStatus.Builder();
//                    builder.target(center);
//                    mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
//                    searchNearbyProcess();
//                }
//            });
//
////            mBaiduMap.clear();
////            PoiOverlay overlay = new MyPoiOverlay(mBaiduMap);
////            mBaiduMap.setOnMarkerClickListener(overlay);
////            overlay.setData(result);
////            overlay.addToMap();
////            overlay.zoomToSpan();
//
//            switch( searchType ) {
//                case 2:
////                    showNearbyArea(center, radius);
//                    break;
//                case 3:
//                    showBound(searchbound);
//                    break;
//                default:
//                    break;
//            }
//
//            return;
//        }
//        if (result.error == SearchResult.ERRORNO.AMBIGUOUS_KEYWORD) {
//
//            // 当输入关键字在本市没有找到，但在其他城市找到时，返回包含该关键字信息的城市列表
//            String strInfo = "在";
//            for (CityInfo cityInfo : result.getSuggestCityList()) {
//                strInfo += cityInfo.city;
//                strInfo += ",";
//            }
//            strInfo += "找到结果";
//            Toast.makeText(NavActivity.this, strInfo, Toast.LENGTH_LONG)
//                    .show();
//        }
//    }
//
//    /**
//     * 获取POI详情搜索结果，得到searchPoiDetail返回的搜索结果
//     * @param result
//     */
//    public void onGetPoiDetailResult(PoiDetailResult result) {
//        if (result.error != SearchResult.ERRORNO.NO_ERROR) {
//            Toast.makeText(NavActivity.this, "抱歉，未找到结果", Toast.LENGTH_SHORT)
//                    .show();
//        } else {
//            Toast.makeText(NavActivity.this, result.getName() + ": " + result.getAddress(), Toast.LENGTH_SHORT)
//                    .show();
//        }
//    }
//
//    @Override
//    public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {
//
//    }
//
//    /**
//     * 获取在线建议搜索结果，得到requestSuggestion返回的搜索结果
//     * @param res
//     */
//    @Override
//    public void onGetSuggestionResult(SuggestionResult res) {
//        if (res == null || res.getAllSuggestions() == null) {
//            return;
//        }
//        suggest = new ArrayList<String>();
//        for (SuggestionResult.SuggestionInfo info : res.getAllSuggestions()) {
//            if (info.key != null) {
//                suggest.add(info.key);
//            }
//        }
//        sugAdapter = new ArrayAdapter<String>(NavActivity.this, android.R.layout.simple_dropdown_item_1line, suggest);
//        keyWorldsView.setAdapter(sugAdapter);
//        sugAdapter.notifyDataSetChanged();
//    }
//
//    private class MyPoiOverlay extends PoiOverlay {
//
//        public MyPoiOverlay(BaiduMap baiduMap) {
//            super(baiduMap);
//        }
//
//        @Override
//        public boolean onPoiClick(int index) {
//            super.onPoiClick(index);
//            PoiInfo poi = getPoiResult().getAllPoi().get(index);
//            // if (poi.hasCaterDetails) {
//            mPoiSearch.searchPoiDetail((new PoiDetailSearchOption())
//                    .poiUid(poi.uid));
//            // }
//            return true;
//        }
//    }
//
//    /**
//     * 对周边检索的范围进行绘制
//     * @param center
//     * @param radius
//     */
//    public void showNearbyArea(LatLng center, int radius) {
//        BitmapDescriptor centerBitmap = BitmapDescriptorFactory
//                .fromResource(R.mipmap.icon_geo);
//        MarkerOptions ooMarker = new MarkerOptions().position(center).icon(centerBitmap);
//        mBaiduMap.addOverlay(ooMarker);
//
//        OverlayOptions ooCircle = new CircleOptions().fillColor( 0xCCCCCC00 )
//                .center(center).stroke(new Stroke(5, 0xFFFF00FF ))
//                .radius(radius);
//        mBaiduMap.addOverlay(ooCircle);
//    }
//
//    /**
//     * 对区域检索的范围进行绘制
//     * @param bounds
//     */
//    public void showBound( LatLngBounds bounds) {
//        BitmapDescriptor bdGround = BitmapDescriptorFactory
//                .fromResource(R.mipmap.ground_overlay);
//
//        OverlayOptions ooGround = new GroundOverlayOptions()
//                .positionFromBounds(bounds).image(bdGround).transparency(0.8f);
//        mBaiduMap.addOverlay(ooGround);
//
//        MapStatusUpdate u = MapStatusUpdateFactory
//                .newLatLng(bounds.getCenter());
//        mBaiduMap.setMapStatus(u);
//
//        bdGround.recycle();
//    }
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
//          MyLocationData locData = new MyLocationData.Builder()
////                    .accuracy(location.getRadius())
//                    // 此处设置开发者获取到的方向信息，顺时针0-360
////                    .direction(100)
//                  .latitude(location.getLatitude())
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
//
////                //画标志
////                CoordinateConverter converter = new CoordinateConverter();
////                converter.coord(ll);
////                converter.from(CoordinateConverter.CoordType.COMMON);
////                LatLng convertLatLng = converter.convert();
////
////                OverlayOptions ooA = new MarkerOptions().position(ll).icon(BitmapDescriptorFactory.fromResource(R.mipmap.icon_geo));
//////                mCurrentMarker = BitmapDescriptorFactory
//////                        .fromResource(R.mipmap.icon_geo);
////                mBaiduMap.addOverlay(ooA);
//
//
//
////
////                MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(convertLatLng, 17.0f);
////                mBaiduMap.animateMapStatus(u);
////                searchNearbyProcess();
//
//                //定义Maker坐标点
//                BitmapDescriptor bitmap = BitmapDescriptorFactory
//                        .fromResource(R.mipmap.icon_gcoding);
//                OverlayOptions option = new MarkerOptions()
//                        .position(ll).draggable(true).title("poicenter")
//                        .icon(bitmap);
////                mBaiduMap.addOverlay(option);
//                 markermy = (Marker) (mBaiduMap.addOverlay(option));
//                center=ll;
//
//                searchNearbyProcess();
//
//
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
//            App.showText(s+"i="+i);
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
//            if(msg.what == MSG_UPDATE_CITY) {
//                //更新文件发送进度
//                String city =  msg.obj.toString();
//                editCity.setText(city);
//
//
//
////                searchType = 1;
////                String citystr = editCity.getText().toString();
////                String keystr = keyWorldsView.getText().toString();
////                mPoiSearch.searchInCity((new PoiCitySearchOption())
////                        .city(citystr).keyword(keystr).pageNum(loadIndex));
//
//            }
//        }
//    };


}
