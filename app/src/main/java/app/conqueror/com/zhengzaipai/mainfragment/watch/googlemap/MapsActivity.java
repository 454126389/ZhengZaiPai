package app.conqueror.com.zhengzaipai.mainfragment.watch.googlemap;

import android.Manifest;
import android.animation.Animator;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;

import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import app.conqueror.com.zhengzaipai.App;
import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.base.BaseFragment;
import app.conqueror.com.zhengzaipai.mainfragment.watch.MainContract;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.AddDevice.AddDeviceActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WatchPoiEntity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.googlemap.trail.GTrailActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.googlemap.zonelist.GZoneListActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.map.trail.TrailActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.map.zonelist.ZoneListActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.me.MultipleItem;
import app.conqueror.com.zhengzaipai.mainfragment.watch.me.MultipleItemQuickAdapter;
import app.conqueror.com.zhengzaipai.util.SpUtil;

import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBufferResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/10/010.
 */

public class MapsActivity extends BaseFragment
        implements MainContract.View,OnMapReadyCallback,View.OnClickListener,GeocodeSearch.OnGeocodeSearchListener  {

//    private static final String TAG = MapsActivity.class.getSimpleName();
    private GoogleMap mMap;
    private CameraPosition mCameraPosition;

    // The entry points to the Places API.
    private GeoDataClient mGeoDataClient;
    private PlaceDetectionClient mPlaceDetectionClient;

    // The entry point to the Fused Location Provider.
    private FusedLocationProviderClient mFusedLocationProviderClient;

    // A default location (Sydney, Australia) and default zoom to use when location permission is
    // not granted.
    private final LatLng mDefaultLocation = new LatLng(-33.8523341, 151.2106085);
    private static final int DEFAULT_ZOOM = 15;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private boolean mLocationPermissionGranted;

    // The geographical location where the device is currently located. That is, the last-known
    // location retrieved by the Fused Location Provider.
    private Location mLastKnownLocation;

    // Keys for storing activity state.
    private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LOCATION = "location";

    // Used for selecting the current place.
    private static final int M_MAX_ENTRIES = 5;
    private String[] mLikelyPlaceNames;
    private String[] mLikelyPlaceAddresses;
    private String[] mLikelyPlaceAttributions;
    private LatLng[] mLikelyPlaceLatLngs;

    boolean isStyle=false;

  /*  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Retrieve location and camera position from saved instance state.
        if (savedInstanceState != null) {
            mLastKnownLocation = savedInstanceState.getParcelable(KEY_LOCATION);
            mCameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION);
        }

        // Retrieve the content view that renders the map.
        setContentView(R.layout.activity_maps);

        // Construct a GeoDataClient.
        mGeoDataClient = Places.getGeoDataClient(this, null);

        // Construct a PlaceDetectionClient.
        mPlaceDetectionClient = Places.getPlaceDetectionClient(this, null);

        // Construct a FusedLocationProviderClient.
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        // Build the map.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    *//**
     * Saves the state of the map when the activity is paused.
     *//*
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (mMap != null) {
            outState.putParcelable(KEY_CAMERA_POSITION, mMap.getCameraPosition());
            outState.putParcelable(KEY_LOCATION, mLastKnownLocation);
            super.onSaveInstanceState(outState);
        }
    }

    *//**
     * Sets up the options menu.
     * @param menu The options menu.
     * @return Boolean.
     *//*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.current_place_menu, menu);
        return true;
    }*/

    /**
     * Handles a click on the menu option to get a place.
     * @param item The menu item to handle.
     * @return Boolean.
     */
    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.option_get_place) {
            showCurrentPlace();
        }
        return true;
    }*/

    /**
     * Manipulates the map when it's available.
     * This callback is triggered when the map is ready to be used.
     */
    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;

        // Use a custom info window adapter to handle multiple lines of text in the
        // info window contents.
        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            @Override
            // Return null here, so that getInfoContents() is called next.
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                // Inflate the layouts for the info window, title and snippet.
                View infoWindow = getActivity().getLayoutInflater().inflate(R.layout.custom_info_contents,
                        (FrameLayout) getActivity().findViewById(R.id.map), false);

                TextView title = ((TextView) infoWindow.findViewById(R.id.title));
                title.setText(marker.getTitle());

                TextView snippet = ((TextView) infoWindow.findViewById(R.id.snippet));
                snippet.setText(marker.getSnippet());

                return infoWindow;
            }
        });

        // Prompt the user for permission.
        getLocationPermission();

        // Turn on the My Location layer and the related control on the map.
        updateLocationUI();

        // Get the current location of the device and set the position of the map.
        getDeviceLocation();

        if(SpUtil.getWatchUserList().size()>0)
            setWatchPoi(SpUtil.getWatchPoiEntity(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id));

    }

    /**
     * Gets the current location of the device, and positions the map's camera.
     */
    private void getDeviceLocation() {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        try {
            if (mLocationPermissionGranted) {
                Task<Location> locationResult = mFusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(getActivity(), new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            // Set the map's camera position to the current location of the device.
                            mLastKnownLocation = task.getResult();
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                    new LatLng(mLastKnownLocation.getLatitude(),
                                            mLastKnownLocation.getLongitude()), DEFAULT_ZOOM));
                        } else {
                            Log.d("TAG", "Current location is null. Using defaults.");
                            Log.e("TAG", "Exception: %s", task.getException());
                            mMap.moveCamera(CameraUpdateFactory
                                    .newLatLngZoom(mDefaultLocation, DEFAULT_ZOOM));
                            mMap.getUiSettings().setMyLocationButtonEnabled(false);
                        }
                    }
                });
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }


    /**
     * Prompts the user for permission to use the device location.
     */
    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    /**
     * Handles the result of the request for location permissions.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }
        updateLocationUI();
    }

    /**
     * Prompts the user to select the current place from a list of likely places, and shows the
     * current place on the map - provided the user has granted location permission.
     */
    private void showCurrentPlace() {
        if (mMap == null) {
            return;
        }

        if (mLocationPermissionGranted) {
            // Get the likely places - that is, the businesses and other points of interest that
            // are the best match for the device's current location.
            @SuppressWarnings("MissingPermission") final
            Task<PlaceLikelihoodBufferResponse> placeResult =
                    mPlaceDetectionClient.getCurrentPlace(null);
            placeResult.addOnCompleteListener
                    (new OnCompleteListener<PlaceLikelihoodBufferResponse>() {
                        @Override
                        public void onComplete(@NonNull Task<PlaceLikelihoodBufferResponse> task) {
                            if (task.isSuccessful() && task.getResult() != null) {
                                PlaceLikelihoodBufferResponse likelyPlaces = task.getResult();

                                // Set the count, handling cases where less than 5 entries are returned.
                                int count;
                                if (likelyPlaces.getCount() < M_MAX_ENTRIES) {
                                    count = likelyPlaces.getCount();
                                } else {
                                    count = M_MAX_ENTRIES;
                                }

                                int i = 0;
                                mLikelyPlaceNames = new String[count];
                                mLikelyPlaceAddresses = new String[count];
                                mLikelyPlaceAttributions = new String[count];
                                mLikelyPlaceLatLngs = new LatLng[count];

                                for (PlaceLikelihood placeLikelihood : likelyPlaces) {
                                    // Build a list of likely places to show the user.
                                    mLikelyPlaceNames[i] = (String) placeLikelihood.getPlace().getName();
                                    mLikelyPlaceAddresses[i] = (String) placeLikelihood.getPlace()
                                            .getAddress();
                                    mLikelyPlaceAttributions[i] = (String) placeLikelihood.getPlace()
                                            .getAttributions();
                                    mLikelyPlaceLatLngs[i] = placeLikelihood.getPlace().getLatLng();

                                    i++;
                                    if (i > (count - 1)) {
                                        break;
                                    }
                                }

                                // Release the place likelihood buffer, to avoid memory leaks.
                                likelyPlaces.release();

                                // Show a dialog offering the user the list of likely places, and add a
                                // marker at the selected place.
                                openPlacesDialog();

                            } else {
                                Log.e("TAG", "Exception: %s", task.getException());
                            }
                        }
                    });
        } else {
            // The user has not granted permission.
            Log.i("TAG", "The user did not grant location permission.");

            // Add a default marker, because the user hasn't selected a place.
            mMap.addMarker(new MarkerOptions()
                    .title("Default Location")
                    .position(mDefaultLocation)
                    .snippet("No places found, because location permission is disabled"));

            // Prompt the user for permission.
            getLocationPermission();
        }
    }

    /**
     * Displays a form allowing the user to select a place from a list of likely places.
     */
    private void openPlacesDialog() {
        // Ask the user to choose the place where they are now.
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // The "which" argument contains the position of the selected item.
                LatLng markerLatLng = mLikelyPlaceLatLngs[which];
                String markerSnippet = mLikelyPlaceAddresses[which];
                if (mLikelyPlaceAttributions[which] != null) {
                    markerSnippet = markerSnippet + "\n" + mLikelyPlaceAttributions[which];
                }

                // Add a marker for the selected place, with an info window
                // showing information about that place.
                mMap.addMarker(new MarkerOptions()
                        .title(mLikelyPlaceNames[which])
                        .position(markerLatLng)
                        .snippet(markerSnippet));

                // Position the map's camera at the location of the marker.
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(markerLatLng,
                        DEFAULT_ZOOM));
            }
        };

        // Display the dialog.
        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setTitle("Choose a plac")
                .setItems(mLikelyPlaceNames, listener)
                .show();
    }

    /**
     * Updates the map's UI settings based on whether the user has granted location permission.
     */
    private void updateLocationUI() {
        if (mMap == null) {
            return;
        }
        try {
            if (mLocationPermissionGranted) {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
            } else {
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                mLastKnownLocation = null;
                getLocationPermission();
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    private void showPopupMenu(View view) {
        // View当前PopupMenu显示的相对View的位置
        PopupMenu popupMenu = new PopupMenu(getActivity(), view);

        Menu menu = popupMenu.getMenu();

        for (int i = 0; i< SpUtil.getAppUser().deviceList.size(); i++)
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


    ImageButton home_title_bar_btn_add;
    ImageView home_title_bar_head_img;
    TextView home_title_bar_tv_name;
    ImageView btn_location;
    TextView btnStyle;
    TextView btnTrail;
    TextView btnSafeZone;
    WatchPoiEntity watchPoi;
    protected MainContract.Presenter mPresenter;
    private LatLng latlng;

    RecyclerView recyclerView;
    private GeocodeSearch geocoderSearch;
    List<MultipleItem> list = new ArrayList<>();


    ImageButton btn_refresh;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        // Retrieve location and camera position from saved instance state.
        if (savedInstanceState != null) {
            mLastKnownLocation = savedInstanceState.getParcelable(KEY_LOCATION);
            mCameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION);
        }

        // Retrieve the content view that renders the map.

        // Construct a GeoDataClient.
        mGeoDataClient = Places.getGeoDataClient(getActivity(), null);

        // Construct a PlaceDetectionClient.
        mPlaceDetectionClient = Places.getPlaceDetectionClient(getActivity(), null);

        // Construct a FusedLocationProviderClient.
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());

//        SupportMapFragment mapFragment =(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

//        Build the map.
//        ((MapFragment) getActivity().getFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);

        MapView mMapView = (MapView) view.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
//        MapFragment fragment=(MapFragment) getChildFragmentManager().findFragmentById(R.id.map);
//
        mMapView.getMapAsync(this);

//        GoogleMap map = ((SupportMapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();


        home_title_bar_btn_add = (ImageButton) view.findViewById(R.id.home_title_bar_btn_add);
        home_title_bar_btn_add.setOnClickListener(this);
        home_title_bar_head_img = (ImageView) view.findViewById(R.id.home_title_bar_head_img);
        home_title_bar_tv_name = (TextView) view.findViewById(R.id.home_title_bar_tv_name);
        btn_refresh = (ImageButton) view.findViewById(R.id.btn_refresh);
        home_title_bar_head_img.setOnClickListener(this);
        btn_location = (ImageView) view.findViewById(R.id.btn_location);
        btn_location.setOnClickListener(this);
        btn_refresh.setOnClickListener(this);


        btnStyle = (TextView) view.findViewById(R.id.btn_style);
        btnTrail = (TextView) view.findViewById(R.id.btn_trail);
        btnSafeZone = (TextView) view.findViewById(R.id.btn_safe_zone);


        btnStyle.setOnClickListener(this);
        btnTrail.setOnClickListener(this);
        btnSafeZone.setOnClickListener(this);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));

        geocoderSearch = new GeocodeSearch(getActivity());
        geocoderSearch.setOnGeocodeSearchListener(this);

    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_maps;
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void setWatchPoi(WatchPoiEntity poi) {
        home_title_bar_tv_name.setText(SpUtil.getAppUser().deviceList.get(SpUtil.getChoise()).nickName);
        latlng =new LatLng(poi.getLat(), poi.getLng());
//        aMap.moveCamera(com.amap.api.maps.CameraUpdateFactory.changeLatLng(latlng));
//        aMap.moveCamera(com.amap.api.maps.CameraUpdateFactory.zoomTo(18));
        addMarkersToMap(latlng);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng,
                DEFAULT_ZOOM));

        watchPoi = poi;

        getAddress(new LatLonPoint(poi.getLat(), poi.getLng()));
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

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {



            case R.id.btn_style:
//                if(isStyle)
//                {
//                    mMap.clear();
//                    addMarkersToMap(latlng);
//                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng,DEFAULT_ZOOM));
//
//                }
//                else
//                {
//                    if(SpUtil.getAppUser().deviceList.size()>1)
//                    {
//                        mMap.clear();
//                        LatLngBounds.Builder boundsBuilder=new LatLngBounds.Builder();
//                        for (int i=0;i<SpUtil.getAppUser().deviceList.size();i++)
//                        {
//                            WatchPoiEntity watchPoiEntity= SpUtil.getWatchPoiEntity(SpUtil.getAppUser().deviceList.get(i).id);
//                            addMarkersToMap(new LatLng(watchPoiEntity.getLat(), watchPoiEntity.getLng()));
//                            boundsBuilder.include(new LatLng(watchPoiEntity.getLat(), watchPoiEntity.getLng()));
//                        }
//
//
//                        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(boundsBuilder.build(),20));
//                        isStyle=!isStyle;
//                    }else
//                    {
//                        mMap.clear();
//                        addMarkersToMap(latlng);
//                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng,DEFAULT_ZOOM));
//                    }
//
//                }
                if(SpUtil.getWatchUserList().size()>0)
                {
                    //顺移一位
                    if(SpUtil.getChoise()<SpUtil.getWatchUserList().size()-1)
                    {
                        SpUtil.setChoise((SpUtil.getChoise()+1));
                    }else  if(SpUtil.getChoise()==SpUtil.getWatchUserList().size()-1)
                    {
                        SpUtil.setChoise(0);
                    }

                    setWatchPoi(SpUtil.getWatchPoiEntity(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id));
                }
//                setWatchPoiMsg(SpUtil.getWatchPoiEntity(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id));


                break;


//            setWatchPoi(SpUtil.getWatchPoiEntity(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id));
            case R.id.btn_refresh:
//                http://61.131.68.156:8081/order/cr?id=2016000894
                getOnButtonClick().onClick(0,null,null);
                break;
            case R.id.btn_location:
//                mMap.getCameraPosition();
                Location location=mMap.getMyLocation();
//                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()),
//                        DEFAULT_ZOOM));

                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng,DEFAULT_ZOOM));

//                mMap.setMyLocationEnabled(true);
                break;

            case R.id.home_title_bar_btn_add:
                startActivity(new Intent(getActivity(), AddDeviceActivity.class));
                break;
            case R.id.home_title_bar_head_img:
                showPopupMenu(v);
                break;
            case R.id.btn_trail:
                startActivity(new Intent(getActivity(), GTrailActivity.class).putExtra("watchPoi", watchPoi));
                break;
            case R.id.btn_safe_zone:
                Intent intent = new Intent(getActivity(), GZoneListActivity.class);
//                String url = "http://sim.conqueror.cn/alipay/index.php/Shop?cut=4&did=12121212";
                // url可以是一号店或者淘宝等第三方的购物wap站点，在该网站的支付过程中，支付宝sdk完成拦截支付
                intent.putExtra("watchPoi", watchPoi);
                startActivity(intent);
//                startActivity(new Intent(getActivity(), ZoneListActivity.class));
                break;
        }
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
//        markerOption = new MarkerOptions()
////                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
//                .icon(BitmapDescriptorFactory.fromBitmap(bm))
//                .position(latlng)
//                .draggable(false);
//        aMap.addMarker(markerOption);


//        private static final LatLng MELBOURNE = new LatLng(-37.813, 144.962);
//        private
        Marker melbourne = mMap.addMarker(new MarkerOptions()
                .position(tlatlng)
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
    public Animator onCreateAnimator(int transit, boolean enter, int nextAnim) {

        if(enter&&mMap!=null)
        {
            setWatchPoi(SpUtil.getWatchPoiEntity(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id));
        }
        return super.onCreateAnimator(transit, enter, nextAnim);
    }

    @Override
    public void onRegeocodeSearched(RegeocodeResult result, int rCode) {
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

    private MapsActivity.OnButtonClick onButtonClick;//2、定义接口成员变量


    //定义接口变量的get方法
    public MapsActivity.OnButtonClick getOnButtonClick() {
        return onButtonClick;
    }

    //定义接口变量的set方法
    public void setOnButtonClick(MapsActivity.OnButtonClick onButtonClick) {
        this.onButtonClick = onButtonClick;
    }


    //1、定义接口
    public interface OnButtonClick {
        public void onClick(int position, String id, String content);
    }

}