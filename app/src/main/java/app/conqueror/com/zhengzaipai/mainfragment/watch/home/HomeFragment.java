package app.conqueror.com.zhengzaipai.mainfragment.watch.home;

import android.animation.Animator;
import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import java.util.ArrayList;
import java.util.List;

import app.conqueror.com.zhengzaipai.App;
import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.WebActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActDnd.ActDndActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.AddDevice.AddDeviceActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.LoveBons.LoveBonsActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.PhoneBook.PhoneBookActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WatchPoiEntity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.home.chatlist.ChatListActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.home.chatlist.chat.ChatActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.home.phone.PhoneActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.map.MapFragment;
import app.conqueror.com.zhengzaipai.mainfragment.watch.me.MultipleItem;
import app.conqueror.com.zhengzaipai.mainfragment.watch.me.MultipleItemQuickAdapter;
import app.conqueror.com.zhengzaipai.util.Actions;
import app.conqueror.com.zhengzaipai.util.DateUtils;
import app.conqueror.com.zhengzaipai.util.SpUtil;
import app.conqueror.com.zhengzaipai.util.TimeUtils;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment implements View.OnClickListener, GeocodeSearch.OnGeocodeSearchListener  {

    RollPagerView rollViewPager;

    Context myContext;

    RecyclerView recyclerViewHome;

    TextView tv_no_position_info;
    TextView tv_position_datatype;
    TextView tv_position_date;
    TextView tv_battery;
    TextView tv_city;
    TextView tv_template;
    TextView tv_weather;
    ImageView iv_refresh;
    ImageButton home_title_bar_btn_add;
    ImageView home_title_bar_head_img;
    TextView home_title_bar_tv_name;

    private ProgressDialog progDialog = null;
    private GeocodeSearch geocoderSearch;

    WatchPoiEntity watchPoi;


    private OnButtonClick onButtonClick;//2、定义接口成员变量

    Receiver mBroadcastReceiver;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_frag_home_page, container, false);



        rollViewPager = (RollPagerView) view.findViewById(R.id.roll_view_pager);
        recyclerViewHome = (RecyclerView) view.findViewById(R.id.recycler_view_home);
        home_title_bar_btn_add = (ImageButton) view.findViewById(R.id.home_title_bar_btn_add);
        home_title_bar_head_img = (ImageView) view.findViewById(R.id.home_title_bar_head_img);
        home_title_bar_tv_name = (TextView) view.findViewById(R.id.home_title_bar_tv_name);
        home_title_bar_btn_add.setOnClickListener(this);
        home_title_bar_head_img.setOnClickListener(this);


        tv_no_position_info = (TextView) view.findViewById(R.id.tv_no_position_info);
        tv_position_datatype = (TextView) view.findViewById(R.id.tv_position_datatype);
        tv_position_date = (TextView) view.findViewById(R.id.tv_position_date);
        tv_battery = (TextView) view.findViewById(R.id.tv_battery);

        tv_city = (TextView) view.findViewById(R.id.tv_city);
        tv_template = (TextView) view.findViewById(R.id.tv_template);
        tv_weather = (TextView) view.findViewById(R.id.tv_weather);
        iv_refresh = (ImageView) view.findViewById(R.id.iv_refresh);


//        tv_city.setText(SpUtil.getWeather().city);
//        tv_template.setText(SpUtil.getWeather().temp);
//        tv_weather.setText(SpUtil.getWeather().weather);

        rollViewPager.setAnimationDurtion(500);   //设置切换时间
        rollViewPager.setAdapter(new TestLoopAdapter(rollViewPager)); //设置适配器
        rollViewPager.setHintView(new ColorPointHintView(myContext, Color.WHITE, Color.GRAY));// 设置圆点指示器颜色


        List<MultipleItem> list = new ArrayList<MultipleItem>();
        list.add(new MultipleItem(MultipleItem.ITEM_MENU_ITEM, MultipleItem.TEXT_SPAN_SIZE, getString(R.string.menu_wechat), R.mipmap.menu_wechat, null, true));
        list.add(new MultipleItem(MultipleItem.ITEM_MENU_ITEM, MultipleItem.TEXT_SPAN_SIZE, getString(R.string.menu_phone_list), R.mipmap.menu_phone_list, null, true));
        list.add(new MultipleItem(MultipleItem.ITEM_MENU_ITEM, MultipleItem.IMG_TEXT_SPAN_SIZE_MIN, getString(R.string.menu_phone), R.mipmap.menu_phone, null, true));
        list.add(new MultipleItem(MultipleItem.ITEM_MENU_ITEM, MultipleItem.IMG_TEXT_SPAN_SIZE_MIN, getString(R.string.menu_location), R.mipmap.menu_location, null, true));
        list.add(new MultipleItem(MultipleItem.ITEM_MENU_ITEM, MultipleItem.IMG_TEXT_SPAN_SIZE_MIN, getString(R.string.menu_dad), R.mipmap.menu_dad, null, true));

        final MultipleItemQuickAdapter multipleItemAdapter = new MultipleItemQuickAdapter(getActivity(), list);
        final GridLayoutManager manager = new GridLayoutManager(myContext, 6);
        multipleItemAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                return list.get(position).getSpanSize();
            }
        });
        recyclerViewHome.setLayoutManager(manager);
        recyclerViewHome.setAdapter(multipleItemAdapter);

        multipleItemAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(getActivity(), ChatListActivity.class));
//                        startActivity(new Intent(getActivity(), ChatActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(getActivity(), PhoneBookActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(getActivity(), PhoneActivity.class));
                        break;
                    case 3:
                        getOnButtonClick().onClick(0);
                        break;
                    case 4:
                        startActivity(new Intent(getActivity(), ActDndActivity.class));
                        break;
                }
            }
        });


        view.findViewById(R.id.btn_add).setOnClickListener(this);
        view.findViewById(R.id.btn_science).setOnClickListener(this);
        view.findViewById(R.id.btn_love_bonus).setOnClickListener(this);


        progDialog = new ProgressDialog(getActivity());
        geocoderSearch = new GeocodeSearch(getActivity());
        geocoderSearch.setOnGeocodeSearchListener(this);





        return view;
    }


    @Override
    public Animator onCreateAnimator(int transit, boolean enter, int nextAnim) {

        if(enter)
        {
            if(SpUtil.getWatchUserList().size()>0&&SpUtil.getAppUser().deviceList!=null)
            {
//                Log.d("test","resout=setWatchPoiMsg");
                setWatchPoiMsg(SpUtil.getWatchPoiEntity(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id));
            }
        }
        return super.onCreateAnimator(transit, enter, nextAnim);
    }


    private void setWatchPoiMsg(WatchPoiEntity watchPoiEntity) {
        watchPoi=watchPoiEntity;
//        23.327018,120.40077
//        Log.d("test","resout=getAddress");
        getAddress(new LatLonPoint(watchPoi.getLat(),watchPoi.getLng()));

        home_title_bar_tv_name.setText(SpUtil.getAppUser().deviceList.get(SpUtil.getChoise()).nickName);
    }

    /**
     * 响应逆地理编码
     */
    public void getAddress(final LatLonPoint latLonPoint) {
//        showDialog();
        RegeocodeQuery query = new RegeocodeQuery(latLonPoint, 200,
                GeocodeSearch.AMAP);// 第一个参数表示一个Latlng，第二参数表示范围多少米，第三个参数表示是火系坐标系还是GPS原生坐标系
        geocoderSearch.getFromLocationAsyn(query);// 设置异步逆地理编码请求
//        getOnButtonClick().onClick(1);

    }


    @Override
    public void onAttach(Activity activity) {
        myContext = (FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn_science:
                startActivity(new Intent(getActivity(), WebActivity.class).putExtra("url","http://www.iautodaily.com/"));
                break;
            case R.id.btn_add:
                startActivity(new Intent(getActivity(), AddDeviceActivity.class));
                break;
            case R.id.btn_love_bonus:
                startActivity(new Intent(getActivity(), LoveBonsActivity.class));
                break;
            case R.id.home_title_bar_btn_add:
                startActivity(new Intent(getActivity(), AddDeviceActivity.class));
                break;
            case R.id.home_title_bar_head_img:
                showPopupMenu(view);
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
                setWatchPoiMsg(SpUtil.getWatchPoiEntity(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id));
                return false;
            }
        });
        // PopupMenu关闭事件
        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {
//             Toast.makeText(getActivity(), "关闭PopupMenu", Toast.LENGTH_SHORT).show();
            }
        });
        popupMenu.show();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onRegeocodeSearched(RegeocodeResult result, int rCode) {
        dismissDialog();
        if (rCode == AMapException.CODE_AMAP_SUCCESS) {
            if (result != null && result.getRegeocodeAddress() != null
                    && result.getRegeocodeAddress().getFormatAddress() != null) {
//                Log.d("test","resout="+result.getRegeocodeAddress().getFormatAddress());
                  setPlacse(result.getRegeocodeAddress().getFormatAddress());

            } else {
                App.showText("no_result");
                ;
            }
        } else {
//            App.showText("rCode="+rCode);;
        }
    }

    public void setPlacse(String address)
    {
        String addressName = address+ getString(R.string.tx_near);

        String type=null;
        if(watchPoi.getType()==0)
            type=getString(R.string.tx_local_type_0);
        else  if(watchPoi.getType()==1)
            type=getString(R.string.tx_local_type_1);
        else  if(watchPoi.getType()==2)
            type=getString(R.string.tx_local_type_2);
        else  if(watchPoi.getType()==3)
            type=getString(R.string.tx_local_type_2);

        tv_no_position_info.setText(type);
        tv_position_datatype.setText(addressName);
        tv_position_date.setText( DateUtils.times(watchPoi.getTime()));
//                        .data(watchPoi.getTime());
        tv_battery.setText(watchPoi.getElec()+"%");

    }


    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

    }

    private class TestLoopAdapter extends LoopPagerAdapter {
        private int[] imgs = {R.mipmap.a, R.mipmap.b, R.mipmap.c};  // 本地图片
        private int count = imgs.length;  // banner上图片的数量

        public TestLoopAdapter(RollPagerView viewPager) {
            super(viewPager);
        }

        @Override
        public View getView(ViewGroup container, int position) {
            final int picNo = position + 1;
            ImageView view = new ImageView(container.getContext());
            view.setImageResource(imgs[position]);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            view.setOnClickListener(new View.OnClickListener()      // 点击事件
            {
                @Override
                public void onClick(View v) {
//                    App.showText("点击了第" + picNo + "张图片");
                }
            });

            return view;
        }

        @Override
        public int getRealCount() {
            return count;
        }

    }

    /**
     * 显示进度条对话框
     */
    public void showDialog() {
//        progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        progDialog.setIndeterminate(false);
//        progDialog.setCancelable(true);
//        progDialog.setMessage("正在获取地址");
//        progDialog.show();
    }

    /**
     * 隐藏进度条对话框
     */
    public void dismissDialog() {
//        if (progDialog != null) {
//            progDialog.dismiss();
//        }
    }

    //定义接口变量的get方法
    public OnButtonClick getOnButtonClick() {
        return onButtonClick;
    }
    //定义接口变量的set方法
    public void setOnButtonClick(OnButtonClick onButtonClick) {
        this.onButtonClick = onButtonClick;
    }
    //1、定义接口
    public interface OnButtonClick{
        public void onClick(int id);
    }


    @Override
    public void onResume() {
        super.onResume();





        //实例化BroadcastReceiver子类 &  IntentFilter
        Receiver mBroadcastReceiver = new Receiver();
        IntentFilter intentFilter = new IntentFilter();

        //设置接收广播的类型
        intentFilter.addAction(Actions.ACTION_CHANGE_GPS);

        //调用Context的registerReceiver（）方法进行动态注册
        getActivity().registerReceiver(mBroadcastReceiver, intentFilter);

        if(getOnButtonClick()!=null&&SpUtil.getAppUser().deviceList!=null)
        getOnButtonClick().onClick(2);
//        getOnButtonClick().onClick(0, SpUtil.getWatchUserList().get(SpUtil.getChoise()).id,tvDate.getText().toString(),tvData);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mBroadcastReceiver != null) {
            getActivity().unregisterReceiver(mBroadcastReceiver);
            mBroadcastReceiver = null;
        }
    }


    public class Receiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Actions.ACTION_CHANGE_GPS))
            {
            WatchPoiEntity watchPoi = (WatchPoiEntity) intent.getExtras().get("watchPoi");
//            tvData.setText(step);
            setWatchPoiMsg(watchPoi);
            }
        }
    }


}
