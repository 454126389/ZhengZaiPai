package app.conqueror.com.zhengzaipai.mainfragment.watch.device;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.base.BaseFragment;
import app.conqueror.com.zhengzaipai.mainfragment.watch.MainContract;
import app.conqueror.com.zhengzaipai.mainfragment.watch.WatchTabActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActCallBack.CallBackActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActClock.ActClockActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActDnd.ActDndActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActFind.ActFindActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActFriend.ActFriendActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActHealth.ActHealthActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActMessage.ActMessageActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActRemoteCamera.ActCameraActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActSchedule.ActScheduleActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActSms.ActSmsActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActSwitch.ActSwitchActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActWifi.WifiActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.AddDevice.AddDeviceActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.PhoneBook.PhoneBookActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WatchPoiEntity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WatchUser;
import app.conqueror.com.zhengzaipai.mainfragment.watch.login.LoginActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.me.MultipleItem;
import app.conqueror.com.zhengzaipai.mainfragment.watch.me.MultipleItemQuickAdapter;
import app.conqueror.com.zhengzaipai.util.SpUtil;

public class DeviceFragment extends BaseFragment implements MainContract.View, View.OnClickListener {
    Context myContext;
    private RecyclerView recycler_view_extra;
    private ImageButton home_title_bar_btn_add;
    private TextView home_title_bar_btn_edit;
//    protected MainContract.Presenter mPresenter;
    private DeviceFragment.OnButtonClick onButtonClick;//2、定义接口成员变量


    ImageView home_title_bar_head_img;
    TextView home_title_bar_tv_name;
    TextView btn_ok;

    String pattern = null;

 /*   @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_device, container, false);
        ButterKnife.bind(this, view);

        App.showText(""+test.getAlt());
        return view;
    }*/


    @Override
    public void setPresenter(MainContract.Presenter presenter) {
//        this.mPresenter = presenter;
    }


    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        home_title_bar_head_img = (ImageView) view.findViewById(R.id.home_title_bar_head_img);
        home_title_bar_tv_name = (TextView) view.findViewById(R.id.home_title_bar_tv_name);
        btn_ok = (TextView) view.findViewById(R.id.btn_ok);

        home_title_bar_head_img.setOnClickListener(this);
        btn_ok.setOnClickListener(this);

        home_title_bar_btn_add = (ImageButton) view.findViewById(R.id.home_title_bar_btn_add);
        home_title_bar_btn_edit = (TextView) view.findViewById(R.id.home_title_bar_btn_edit);
        home_title_bar_btn_add.setOnClickListener(this);
        home_title_bar_btn_edit.setOnClickListener(this);
        recycler_view_extra = (RecyclerView) view.findViewById(R.id.recycler_view_extra);
        recycler_view_extra.setLayoutManager(new GridLayoutManager(myContext, 4));

        List<MultipleItem> list = new ArrayList<>();
        list.add(new MultipleItem(MultipleItem.ITEM_DEVICE_TITLE, MultipleItem.IMG_TEXT_SPAN_SIZE, getString(R.string.special_function), 0, getString(R.string.tx_introduce), true));
        list.add(new MultipleItem(MultipleItem.ITEM_DEVICE_ITEM, MultipleItem.IMG_SPAN_SIZE, getString(R.string.apply_health), R.mipmap.home_device_ic_health2, null, true));

            list.add(new MultipleItem(MultipleItem.ITEM_DEVICE_ITEM, 0, getString(R.string.remote_camera), R.mipmap.home_device_ic_remote_camera, null, true));
//            list.add(new MultipleItem(MultipleItem.ITEM_DEVICE_ITEM, MultipleItem.IMG_SPAN_SIZE, getString(R.string.remote_camera), R.mipmap.home_device_ic_remote_camera, null, true));

        list.add(new MultipleItem(MultipleItem.ITEM_DEVICE_ITEM, MultipleItem.IMG_SPAN_SIZE, getString(R.string.touch_friends), R.mipmap.home_device_ic_friends2, null, true));

        list.add(new MultipleItem(MultipleItem.ITEM_DEVICE_ITEM, 0, getString(R.string.schedule), R.mipmap.home_device_ic_schedule, null, true));
        list.add(new MultipleItem(MultipleItem.DIVIDER, MultipleItem.IMG_TEXT_SPAN_SIZE, null, 0, null, false));
        list.add(new MultipleItem(MultipleItem.ITEM_DEVICE_TITLE, MultipleItem.IMG_TEXT_SPAN_SIZE, getString(R.string.base_function), 0, getString(R.string.tx_introduce), true));
        list.add(new MultipleItem(MultipleItem.ITEM_DEVICE_ITEM, MultipleItem.IMG_SPAN_SIZE, getString(R.string.monitor1), R.mipmap.home_device_ic_monitor2, null, true));
        list.add(new MultipleItem(MultipleItem.ITEM_DEVICE_ITEM, 0, getString(R.string.wlan), R.mipmap.home_device_ic_wifi, null, true));
        list.add(new MultipleItem(MultipleItem.ITEM_DEVICE_ITEM, MultipleItem.IMG_SPAN_SIZE, getString(R.string.sos_family), R.mipmap.home_device_ic_sos2, null, true));
        list.add(new MultipleItem(MultipleItem.ITEM_DEVICE_ITEM, MultipleItem.IMG_SPAN_SIZE, getString(R.string.alarm_clock), R.mipmap.home_device_ic_clock2, null, true));

        list.add(new MultipleItem(MultipleItem.ITEM_DEVICE_ITEM, MultipleItem.IMG_SPAN_SIZE, getString(R.string.sms_alart), R.mipmap.home_device_ic_sms2, null, true));
        list.add(new MultipleItem(MultipleItem.ITEM_DEVICE_ITEM, 0, getString(R.string.push_switch), R.mipmap.home_device_ic_profile2, null, true));
        list.add(new MultipleItem(MultipleItem.ITEM_DEVICE_ITEM, MultipleItem.IMG_SPAN_SIZE, getString(R.string.workmode), R.mipmap.home_device_ic_workmode2, null, true));
        list.add(new MultipleItem(MultipleItem.ITEM_DEVICE_ITEM, MultipleItem.IMG_SPAN_SIZE, getString(R.string.remote_shutdown), R.mipmap.home_device_ic_remote_shutdown2, null, true));

        final MultipleItemQuickAdapter multipleItemAdapter = new MultipleItemQuickAdapter(myContext, list);
//        final GridLayoutManager manager = new GridLayoutManager(myContext, 4);
//        recycler_view_extra.setLayoutManager(manager);
        multipleItemAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                return list.get(position).getSpanSize();
            }
        });
        recycler_view_extra.setAdapter(multipleItemAdapter);


        multipleItemAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                Toast.makeText(getActivity(), "position:" + position, Toast.LENGTH_LONG).show();
                switch (position) {
                    case 1:
//                        String order="RY*2016000984*0008*FLOWER,5";
//                        String salt = "CFC3C3C5C8F0D2E4BFC6BCBC";
//                        order = md5Hex(order + salt);
                        startActivity(new Intent(getActivity(), ActHealthActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(getActivity(), ActCameraActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(getActivity(), ActFriendActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(getActivity(), ActScheduleActivity.class));

                        break;


                    case 7:
                        startActivity(new Intent(getActivity(), CallBackActivity.class));
                        break;
                    case 8:
                        startActivity(new Intent(getActivity(), WifiActivity.class));
                        break;
                    case 9:

                        SosDialogFragment dialogFragment =SosDialogFragment.newInstance();
                        dialogFragment.setOnButtonClick(new SosDialogFragment.OnButtonClick() {
                            @Override
                            public void onClick( StringBuffer stringBuffer,int type ) {
                                if (type==0)
                                getOnButtonClick().onClick(position, SpUtil.getWatchUserList().get(SpUtil.getChoise()).id, stringBuffer.toString());
                                else if (type==99)
                                {
                                    if(getOnButtonClick()!=null&&SpUtil.getAppUser().deviceList!=null)
                                        getOnButtonClick().onClick(99, "","");
                                }
//                                mPresenter.monitor(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id,SpUtil.getPhone(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id));
                            }
                        });
                        dialogFragment.show(getFragmentManager(), "AlertDialog");

                      /*  final View dialog_sos = LayoutInflater.from(getActivity()).inflate(R.layout.device_setting_sos, null);//这里必须是final的
                        EditText setting_sos_num_01, setting_sos_num_02, setting_sos_num_03;
                        setting_sos_num_01 = (EditText) dialog_sos.findViewById(R.id.setting_sos_num_01);
                        setting_sos_num_02 = (EditText) dialog_sos.findViewById(R.id.setting_sos_num_02);
                        setting_sos_num_03 = (EditText) dialog_sos.findViewById(R.id.setting_sos_num_03);

                        List<String> sosList = SpUtil.getWatchUserList().get(SpUtil.getChoise()).sosList;
                        if(sosList!=null)
                        {
                            for (int i = 0; i < sosList.size(); i++) {
                                if (i == 0)
                                    setting_sos_num_01.setText(sosList.get(0).toString());
                                if (i == 1)
                                    setting_sos_num_02.setText(sosList.get(1).toString());
                                if (i == 2)
                                    setting_sos_num_03.setText(sosList.get(2).toString());
                            }
                        }

                        new AlertDialog.Builder(getActivity())
                                .setView(dialog_sos)
                                .setPositiveButton(getString(R.string.btn_sure),//提示框的两个按钮
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog,
                                                                int which) {
                                                StringBuffer stringBuffer = new StringBuffer();
                                                if (setting_sos_num_01.getText().length() > 0)
                                                    stringBuffer.append(setting_sos_num_01.getText().toString());
                                                if (setting_sos_num_02.getText().length() > 0) {
                                                    if (stringBuffer.length() > 0)
                                                        stringBuffer.append(",");
                                                    stringBuffer.append(setting_sos_num_02.getText().toString());
                                                }
                                                if (setting_sos_num_03.getText().length() > 0)
                                                    if (stringBuffer.length() > 0)
                                                        stringBuffer.append(",");
                                                stringBuffer.append(setting_sos_num_03.getText().toString());


//                                                App.showText("sos_mum="+stringBuffer.toString());
                                                getOnButtonClick().onClick(position, SpUtil.getWatchUserList().get(SpUtil.getChoise()).id, stringBuffer.toString());
                                            }
                                        }).setNegativeButton(getString(R.string.btn_cancel), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).create().show();*/

                        break;

                    case 10:
                        startActivity(new Intent(getActivity(), ActClockActivity.class));
                        break;
                    case 11:
                        startActivity(new Intent(getActivity(), ActSmsActivity.class));
                        break;
                    case 12:
                        startActivity(new Intent(getActivity(), ActSwitchActivity.class));
                        break;
                    case 13:

                        WorkDialogFragment workDialogFragment =WorkDialogFragment.newInstance();
                        workDialogFragment.setOnButtonClick(new WorkDialogFragment.OnButtonClick() {
                            @Override
                            public void onClick(String id, int type) {
                                if (type==0)
                                    getOnButtonClick().onClick(position, SpUtil.getWatchUserList().get(SpUtil.getChoise()).id, pattern);
                                else   if (type==99)
                                {
                                    if(getOnButtonClick()!=null&&SpUtil.getAppUser().deviceList!=null)
                                        getOnButtonClick().onClick(99, "","");
                                }
//                                getOnButtonClick().onClick(position, SpUtil.getWatchUserList().get(SpUtil.getChoise()).id, pattern);
//                                mPresenter.monitor(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id,SpUtil.getPhone(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id));
                            }
                        });
                        workDialogFragment.show(getFragmentManager(), "AlertDialog");



//                        final View dialog_work = LayoutInflater.from(getActivity()).inflate(R.layout.view_setting_work_mode, null);//这里必须是final的
//                        RadioButton radio1, radio2, radio3;
//                        radio1 = (RadioButton) dialog_work.findViewById(R.id.setting_work_mode_rb_01);
//                        radio2 = (RadioButton) dialog_work.findViewById(R.id.setting_work_mode_rb_02);
//                        radio3 = (RadioButton) dialog_work.findViewById(R.id.setting_work_mode_rb_03);
//
//
//                        if (SpUtil.getWatchUserList().get(SpUtil.getChoise()).pattern.equals("10")) {
//                            radio1.setChecked(true);
//                            pattern = "10";
//
//                        } else if (SpUtil.getWatchUserList().get(SpUtil.getChoise()).pattern.equals("60")) {
//                            radio2.setChecked(true);
//                            pattern = "60";
//                        } else if (SpUtil.getWatchUserList().get(SpUtil.getChoise()).pattern.equals("1")) {
//                            radio3.setChecked(true);
//                            pattern = "1";
//                        }
//
//
//                        View.OnClickListener onClickListener = new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                switch (view.getId()) {
//                                    case R.id.setting_work_mode_rb_01:
//                                        radio2.setChecked(false);
//                                        radio3.setChecked(false);
//                                        pattern = "10";
//                                        break;
//                                    case R.id.setting_work_mode_rb_02:
//                                        radio1.setChecked(false);
//                                        radio3.setChecked(false);
//                                        pattern = "60";
//                                        break;
//                                    case R.id.setting_work_mode_rb_03:
//                                        radio1.setChecked(false);
//                                        radio2.setChecked(false);
//                                        pattern = "1";
//                                        break;
//                                }
//                            }
//                        };
//                        radio1.setOnClickListener(onClickListener);
//                        radio2.setOnClickListener(onClickListener);
//                        radio3.setOnClickListener(onClickListener);
//
//
//                        new AlertDialog.Builder(getActivity())
//                                .setView(dialog_work)
//                                .setPositiveButton(getString(R.string.btn_sure),//提示框的两个按钮
//                                        new DialogInterface.OnClickListener() {
//                                            @Override
//                                            public void onClick(DialogInterface dialog,
//                                                                int which) {
//
//                                                getOnButtonClick().onClick(position, SpUtil.getWatchUserList().get(SpUtil.getChoise()).id, pattern);
//
//                                            }
//                                        }).setNegativeButton(getString(R.string.btn_cancel), new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//
//                            }
//                        }).create().show();
                        break;
                    case 14:
                        new android.app.AlertDialog.Builder(getActivity())
                                .setTitle(getString(R.string.tx_remote))
                                .setMessage(getString(R.string.remote_shutdown))
                                .setPositiveButton(getString(R.string.btn_sure), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
//                                        Toast.makeText(getActivity(), "已请求", Toast.LENGTH_SHORT).show();
//                                        mPresenter.getWatchPoi();
                                        getOnButtonClick().onClick(position, null, null);
                                    }
                                }).setNegativeButton(getString(R.string.btn_cancel), null)
                                .show();
                        break;


                }

            }
        });


        view.findViewById(R.id.btn_phone_book).setOnClickListener(this);
        view.findViewById(R.id.btn_dnd).setOnClickListener(this);
        view.findViewById(R.id.btn_find).setOnClickListener(this);


    }


    @Override
    public void onResume() {
        super.onResume();








    }


    @Override
    public Animator onCreateAnimator(int transit, boolean enter, int nextAnim) {

        if(enter)
        {
            if(SpUtil.getAppUser().deviceList!=null)
            home_title_bar_tv_name.setText(SpUtil.getAppUser().deviceList.get(SpUtil.getChoise()).nickName);

            if(getOnButtonClick()!=null&&SpUtil.getAppUser().deviceList!=null)
                getOnButtonClick().onClick(99, "","");

        }
        return super.onCreateAnimator(transit, enter, nextAnim);
    }

    @Override
    public void initPresenter() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_device;
    }

    @Override
    public void onAttach(Activity activity) {
        myContext = (FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_phone_book:
                startActivity(new Intent(getActivity(), PhoneBookActivity.class));
                break;
            case R.id.btn_dnd:
                startActivity(new Intent(getActivity(), ActDndActivity.class));
                break;
            case R.id.btn_find:
                startActivity(new Intent(getActivity(), ActFindActivity.class));
                break;
            case R.id.home_title_bar_btn_add:
                startActivity(new Intent(getActivity(), AddDeviceActivity.class));
                break;
            case R.id.home_title_bar_btn_edit:
                startActivity(new Intent(getActivity(), ActMessageActivity.class));
                break;
            case R.id.home_title_bar_head_img:
                showPopupMenu(view);
                break;
            case R.id.btn_ok:

                new android.app.AlertDialog.Builder(getActivity())
                        .setTitle(getString(R.string.unbind))
                        .setMessage(getString(R.string.unbind)+SpUtil.getWatchUserList().get(SpUtil.getChoise()).id)
                        .setPositiveButton(getString(R.string.btn_sure), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

//                                List<WatchUser> watchUserList=SpUtil.getWatchUserList();
//                                SpUtil.setChoise(0);
//                                if(watchUserList.size()>1)
//                                {
//
//                                    home_title_bar_tv_name.setText(SpUtil.getAppUser().deviceList.get(SpUtil.getChoise()).nickName);
//                                }
                                getOnButtonClick().onClick(15,SpUtil.getAppUser().phone, SpUtil.getWatchUserList().get(SpUtil.getChoise()).id);


                            }
                        }).setNegativeButton(getString(R.string.btn_cancel), null)
                        .show();



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
                home_title_bar_tv_name.setText(SpUtil.getAppUser().deviceList.get(SpUtil.getChoise()).nickName);
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
    public void setWatchPoi(WatchPoiEntity poi) {

    }


    //定义接口变量的get方法
    public DeviceFragment.OnButtonClick getOnButtonClick() {
        return onButtonClick;
    }

    //定义接口变量的set方法
    public void setOnButtonClick(DeviceFragment.OnButtonClick onButtonClick) {
        this.onButtonClick = onButtonClick;
    }


    //1、定义接口
    public interface OnButtonClick {
        public void onClick(int position, String id, String content);
    }




}