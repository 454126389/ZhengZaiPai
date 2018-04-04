package app.conqueror.com.zhengzaipai.mainfragment.watch;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Environment;
import android.support.annotation.IdRes;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.List;

import app.conqueror.com.zhengzaipai.App;
import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.base.BaseActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.DeviceFragment;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WatchUser;
import app.conqueror.com.zhengzaipai.mainfragment.watch.googlemap.MapsActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.home.HomeFragment;
import app.conqueror.com.zhengzaipai.mainfragment.watch.login.LoginActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.map.MapFragment;
import app.conqueror.com.zhengzaipai.mainfragment.watch.me.MeFragment;
import app.conqueror.com.zhengzaipai.util.AudioRecoderUtils;
import app.conqueror.com.zhengzaipai.util.SpUtil;
import app.conqueror.com.zhengzaipai.view.pop.PopupMenuUtil;
//主要tab
public class WatchTabActivity extends BaseActivity<WatchTabPresenter, WatchTabModel>implements  WatchTabContract.View,RadioGroup.OnCheckedChangeListener {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private HomeFragment oneFragment;
    private DeviceFragment twoFragment;
//    private MapFragment threeFragment;
    private MapsActivity googleFragment;
    private MapFragment gdFragment;
    private FourFragment fourFragment;
    private MeFragment fiveFragment;

//    private MainPresenter mFragmentPresenter;

    private long exitTime = 0;

    @Override
    public int getLayoutId() {
        return R.layout.home_act_home;
    }

    @Override
    public void initView() {


//       String path= Environment.getExternalStorageDirectory()+"/xutils/2016000891_170912085802.amr";
//        AudioRecoderUtils mAudioRecoderUtils = new AudioRecoderUtils();
//        mAudioRecoderUtils.startPlaying(chatMsgList.get(position).msg);

        fragmentManager=getFragmentManager();
        ((RadioGroup)findViewById(R.id.tabs_group)).setOnCheckedChangeListener(this);


//        mFragmentPresenter = new MainPresenter(getApplicationContext());

/*        oneFragment = new HomeFragment();
        twoFragment=new DeviceFragment();
        threeFragment=new MapFragment();
        fourFragment=new FourFragment();
        fiveFragment=new MeFragment();
        fragmentTransaction.add(R.id.realtabcontent, oneFragment);
        fragmentTransaction.add(R.id.realtabcontent, twoFragment);
        fragmentTransaction.add(R.id.realtabcontent, threeFragment);
        fragmentTransaction.add(R.id.realtabcontent, fourFragment);
        fragmentTransaction.add(R.id.realtabcontent, fiveFragment);

        showFragment(1);*/




    }


    @Override
    public void initTab() {
//        ((RadioGroup)findViewById(R.id.tabs_group)).check(R.id.tab_homepage);/*默认先显示第一页*/


        fragmentTransaction=fragmentManager.beginTransaction();
        hideFragment(fragmentTransaction);/*想要显示一个fragment,先隐藏所有fragment，防止重叠*/
        fragmentTransaction.commitAllowingStateLoss();

        oneFragment = new HomeFragment();
        twoFragment=new DeviceFragment();


        googleFragment=new MapsActivity();
        gdFragment=new MapFragment();

        fourFragment=new FourFragment();
        fiveFragment=new MeFragment();
        fragmentTransaction.add(R.id.realtabcontent, oneFragment);
        fragmentTransaction.add(R.id.realtabcontent, twoFragment);
//        判断是否为台湾版本
        if(SpUtil.getLanguage().equals("zh-TW"))
            //采用谷歌地图
            fragmentTransaction.add(R.id.realtabcontent, googleFragment);
        else
//            采用高德地图
            fragmentTransaction.add(R.id.realtabcontent, gdFragment);

        fragmentTransaction.add(R.id.realtabcontent, fourFragment);
        fragmentTransaction.add(R.id.realtabcontent, fiveFragment);

        ((RadioGroup)findViewById(R.id.tabs_group)).check(R.id.tab_homepage);/*默认先显示第一页*/

        oneFragment.setOnButtonClick(new HomeFragment.OnButtonClick() {

            @Override
            public void onClick(int id) {
//                showFragment(3);
                switch (id)
                {
                    case 0:
                        ((RadioButton) findViewById(R.id.tab_location)).setChecked(true);
                        break;
                    case 1:
                        mPresenter.geocode("23.327018,120.40077",getString(R.string.google_maps_key));
                        break;
                    case 2:

                         mPresenter.findDevicesByUserPhone(SpUtil.getAppUser().phone);
                         mPresenter.getWatchPoi(SpUtil.getAppUser().deviceList.get(SpUtil.getChoise()).id);
                        break;
                }

            }
        });

        twoFragment.setOnButtonClick(new DeviceFragment.OnButtonClick(){

            @Override
            public void onClick(int position, String id, String content) {
                switch (position)
                {
                    case 9:
                        mPresenter.sos(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id,content);
                        break;
                    case 13:
                        mPresenter.upload(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id,content);
                        break;
                    case 14:


                        mPresenter.poweroff(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id);
                        break;
                    case 15:
                        mPresenter.unbunding(id,content);
                        break;
                    case 99:
                        mPresenter.findDevicesByUserPhone(SpUtil.getAppUser().phone);
                        break;
                }
            }


        });


        gdFragment.setOnButtonClick(new MapFragment.OnButtonClick() {
            @Override
            public void onClick(int position, String id, String content) {
             switch (position)
                {
                    case 0:
                        mPresenter.cr(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id);
                        break;

                }
            }
        });

        googleFragment.setOnButtonClick(new MapsActivity.OnButtonClick() {
            @Override
            public void onClick(int position, String id, String content) {
                switch (position)
                {
                    case 0:
                        mPresenter.cr(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id);
                        break;

                }
            }
        });



    }


    @Override
    public void set_placse(String address) {
        oneFragment.setPlacse(address);
    }

    @Override
    public void tip_suc() {
        App.showText(getString(R.string.tip_suc));
    }

    @Override
    public void unbind_suc() {
        List<WatchUser> watchUserList=SpUtil.getWatchUserList();
//        watchUserList.remove(SpUtil.getChoise());
//        SpUtil.setWatchUserList(watchUserList);
//        SpUtil.setChoise(0);

        if(watchUserList.size()>0)
        {
//            refresh();
            finish();
            Intent intent = new Intent(this, WatchTabActivity.class);
            startActivity(intent);
        }else
        {
            finish();
            startActivity(new Intent(this, LoginActivity.class));

        }
    }

    @Override
    public void need_one() {
        App.showText(getString(R.string.need_one));
    }

    @Override
    public void tip_fail() {
        App.showText(getString(R.string.tip_fail));
    }

    @Override
    public void tip_error() {
        App.showText(getString(R.string.tip_error));
    }


    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel, this);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
        switch (i){
            case R.id.tab_homepage:
                showFragment(1);
                break;
            case R.id.tab_device:
                showFragment(2);
                break;
            case R.id.tab_location:
                showFragment(3);
                break;
            case R.id.tab_find:
                showFragment(4);
                break;
            case R.id.tab_my:
                showFragment(5);
                break;
        }
    }

    /**
     * 显示fragment
     */
    private void showFragment(int index){
        fragmentTransaction=fragmentManager.beginTransaction();
        hideFragment(fragmentTransaction);/*想要显示一个fragment,先隐藏所有fragment，防止重叠*/
        switch (index){
            case 1:
                /*如果fragment1已经存在则将其显示出来*/
                if (oneFragment != null)
                    fragmentTransaction.show(oneFragment);
			    /*否则是第一次切换则添加fragment1，注意添加后是会显示出来的，replace方法也是先remove后add*/
                else {
                    oneFragment = new HomeFragment();
                    fragmentTransaction.add(R.id.realtabcontent, oneFragment);
                }

                break;
            case 2:
                if(twoFragment!=null)
                    fragmentTransaction.show(twoFragment);
                else{
                    twoFragment=new DeviceFragment();
                    fragmentTransaction.add(R.id.realtabcontent, twoFragment);
                }

//                mFragmentPresenter.setView(twoFragment);

                break;
            case 3:
                if(SpUtil.getLanguage().equals("zh-TW"))
                {
                    if(googleFragment!=null)
                        fragmentTransaction.show(googleFragment);
                    else{
                        googleFragment=new MapsActivity();
                        fragmentTransaction.add(R.id.realtabcontent, googleFragment);
                    }
                }
                else
                {
                    if(gdFragment!=null)
                        fragmentTransaction.show(gdFragment);
                    else{
                        gdFragment=new MapFragment();
                        fragmentTransaction.add(R.id.realtabcontent, gdFragment);
                    }
                }


//                mFragmentPresenter.setView(threeFragment);

                break;
            case 4:
                if(fourFragment!=null)
                    fragmentTransaction.show(fourFragment);
                else{
                    fourFragment=new FourFragment();
                    fragmentTransaction.add(R.id.realtabcontent, fourFragment);
                }
                break;
            case 5:
                if(fiveFragment!=null)
                    fragmentTransaction.show(fiveFragment);
                else{
                    fiveFragment=new MeFragment();
                    fragmentTransaction.add(R.id.realtabcontent, fiveFragment);
                }
                break;
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

    /**
     * 隐藏fragment
     */
    private void hideFragment(FragmentTransaction ft){
        if(oneFragment!=null)
            ft.hide(oneFragment);
        if(twoFragment!=null)
            ft.hide(twoFragment);

        if(SpUtil.getLanguage().equals("zh-TW"))
        {
            if(googleFragment!=null)
            ft.hide(googleFragment);
        }else
        {
            if(gdFragment!=null)
                ft.hide(gdFragment);
        }

        if(fourFragment!=null)
            ft.hide(fourFragment);
        if(fiveFragment!=null)
            ft.hide(fiveFragment);
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
    public void showDialog() {
        showBaseDialog();
    }

    @Override
    public void hideDialog() {
        hideBaseDialog();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            // 当popupWindow 正在展示的时候 按下返回键 关闭popupWindow 否则关闭activity
            if (PopupMenuUtil.getInstance()._isShowing()) {
                PopupMenuUtil.getInstance()._rlClickAction();
            }
            // 当popupWindow 正在展示的时候 按下返回键 关闭popupWindow 否则关闭activity
            else if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void sendAction(Intent intent) {
        sendOrderedBroadcast(intent, null);   //有序广播发送
    }


    /**
     * 刷新
     */
    private void refresh() {
        finish();
        Intent intent = new Intent(this, WatchTabActivity.class);
        startActivity(intent);
    }

}