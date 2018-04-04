package app.conqueror.com.zhengzaipai.mainfragment.watch;

import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WatchPoiEntity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WatchUser;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.Weather;
import app.conqueror.com.zhengzaipai.util.Actions;
import app.conqueror.com.zhengzaipai.util.SpUtil;

/**
 * Created by Administrator on 2017/7/20.
 */

public class WatchTabPresenter extends WatchTabContract.Presenter {

    boolean isfirst=true;

    @Override
    public void onStart() {
        mView.initDialog();
        mView.showDialog();
        //获得当前设备定位信息

        if(SpUtil.getChoise()>=SpUtil.getAppUser().deviceList.size())
            SpUtil.setChoise(0);

        for (int i=0;i<SpUtil.getAppUser().deviceList.size();i++)
        {


            getWatchPoi(SpUtil.getAppUser().deviceList.get(i).id);
            if(i==SpUtil.getAppUser().deviceList.size()-1)
                findDevicesByUserPhone(SpUtil.getAppUser().phone);
        }




    }
    @Override
    public void getWatchPoi(String id) {
//        mView.showDialog();
        mRxManage.add(mModel.getWatchPoi(id).subscribe(res -> {
//                    mView.hideDialog();
                    if (res.success.equals("success")) {
                        WatchPoiEntity watchPoi = new WatchPoiEntity();
                        String[] subres = (res.mes).split(",");
                        watchPoi.setTime(""+(Long.parseLong(subres[0])+28800));
                        watchPoi.setType(Integer.parseInt(subres[1]));
                        watchPoi.setLat(Double.parseDouble(subres[2]));
                        watchPoi.setLng(Double.parseDouble(subres[4]));

                        watchPoi.setSpeed(Float.parseFloat(subres[6]));
                        watchPoi.setDirec(Float.parseFloat(subres[7]));
                        watchPoi.setAlt(Float.parseFloat(subres[8]));
                        watchPoi.setGSM(Integer.parseInt(subres[9]));
                        watchPoi.setGSM_SENCE(Integer.parseInt(subres[10]));
                        watchPoi.setElec(Integer.parseInt(subres[11]));
                        watchPoi.setStep(Integer.parseInt(subres[12]));
                        watchPoi.setTrun(Integer.parseInt(subres[13]));

                        SpUtil.setWatchPoiEntity(watchPoi,id);

                        Intent intent = new Intent();
                        intent.setAction(Actions.ACTION_CHANGE_GPS);
                        intent.putExtra("watchPoi",watchPoi);
                        mView.sendAction(intent);

                        if(isfirst==true)
                        {
                            isfirst=false;

                            tw(""+watchPoi.getLat(),""+watchPoi.getLng());
                        }

                    } else {
//                        mView.showMsg("失败!");
                        mView.tip_fail();
                    }
                }, e -> {
                    mView.tip_error();

                    WatchPoiEntity watchPoi = new WatchPoiEntity();
                    String mes="1504027253,1,24.648415,N,118.1561693,E,1.54,77.6,0.0,8,80,5,0,0,00000019";
                    String[] subres = mes.split(",");
                    watchPoi.setTime(""+(Long.parseLong(subres[0])+28800));
                    watchPoi.setType(Integer.parseInt(subres[1]));
                    watchPoi.setLat(Double.parseDouble(subres[2]));
                    watchPoi.setLng(Double.parseDouble(subres[4]));

                    watchPoi.setSpeed(Float.parseFloat(subres[6]));
                    watchPoi.setDirec(Float.parseFloat(subres[7]));
                    watchPoi.setAlt(Float.parseFloat(subres[8]));
                    watchPoi.setGSM(Integer.parseInt(subres[9]));
                    watchPoi.setGSM_SENCE(Integer.parseInt(subres[10]));
                    watchPoi.setElec(Integer.parseInt(subres[11]));
                    watchPoi.setStep(Integer.parseInt(subres[12]));
                    watchPoi.setTrun(Integer.parseInt(subres[13]));

                    SpUtil.setWatchPoiEntity(watchPoi,id);

                    Intent intent = new Intent();
                    intent.setAction(Actions.ACTION_CHANGE_GPS);
                    intent.putExtra("watchPoi",watchPoi);
                    mView.sendAction(intent);
//                    mView.showMsg("错误!" + e);
//                    mView.hideDialog();
                }

        ));
    }

    @Override
    public void findDevicesByUserPhone(String phone) {
//        mView.showDialog();
        mRxManage.add(mModel.findDevicesByUserPhone(phone).subscribe(res -> {
//                    mView.hideDialog();
                    if (res.success.equals("success")) {

                        SpUtil.setWatchUserList(res.list);


                    } else {
//                        mView.showMsg("失败!");
                        mView.tip_fail();
                    }
                }, e -> {
//                    mView.showMsg("错误!" + e);
                    mView.tip_error();
                    mView.hideDialog();
                }

        ));
    }

    @Override
    public void tw(String lat, String lon) {
//        mView.showDialog();
        mRxManage.add(mModel.tw(lat,lon).subscribe(res -> {
                    mView.hideDialog();
                    if (res.success.equals("success")) {

                        Weather weather=new Weather(res.mes.split(",")[0],res.mes.split(",")[1]+"℃",res.mes.split(",")[2],res.mes.split(",")[3]+"-"+res.mes.split(",")[4]);
                        SpUtil.setWeather(weather);


                    } else {
//                        mView.showMsg("失败!");
                        mView.tip_fail();
                    }
            mView.initTab();
                }, e -> {
//                    mView.showMsg("失败!" + e);
            try {
                mView.tip_error();
                mView.hideDialog();
                mView.initTab();
            }catch (Exception e1)
            {
                e.printStackTrace();
            }

                }

        ));
    }

    @Override
    public void unbunding(String phone, String id) {
        mView.showDialog();
        mRxManage.add(mModel.unbunding(phone,id).subscribe(res -> {
                    mView.hideDialog();
                    if (res.success.equals("success")) {

//                        Weather weather=new Weather(res.mes.split(",")[0],res.mes.split(",")[1]+"℃",res.mes.split(",")[2],res.mes.split(",")[3]+"-"+res.mes.split(",")[4]);
//                        SpUtil.setWeather(weather);


                        List<WatchUser> watchuserlist=SpUtil.getWatchUserList();
                        watchuserlist.remove(SpUtil.getChoise());
                        SpUtil.setWatchUserList(watchuserlist);
                        SpUtil.setChoise(0);
                        login(SpUtil.getUsername(),SpUtil.getPassword());





                    } else {
//                        mView.showMsg("失败!");
                        mView.tip_fail();
                    }
                    mView.initTab();
                }, e -> {
//                    mView.showMsg("失败!" + e);
                    mView.tip_error();
                    mView.hideDialog();
                    mView.initTab();
                }

        ));
    }

    @Override
    public void geocode(String latlng, String key) {
        mRxManage.add(mModel.geocode(latlng,key).subscribe(res -> {
//                    mView.hideDialog();
                    if (res.status.equals("OK")) {

//                        Weather weather=new Weather(res.mes.split(",")[0],res.mes.split(",")[1]+"℃",res.mes.split(",")[2],res.mes.split(",")[3]+"-"+res.mes.split(",")[4]);
//                        SpUtil.setWeather(weather);

                        mView.set_placse(res.addressResult.get(0).formatted_address);
                        mView.tip_suc();


                    } else {
//                        mView.showMsg("失败!");
                        mView.tip_fail();
                    }
                    mView.initTab();
                }, e -> {
//                    mView.showMsg("失败!" + e);
                    mView.tip_error();
//                    mView.hideDialog();
                    mView.initTab();
                }

        ));
    }


//    @Override
//    public void findUserByPhone(String phone) {
//        mView.showDialog();
//        mRxManage.add(mModel.findUserByPhone(phone).subscribe(res -> {
//                    mView.hideDialog();
//                    if (res.success.equals("success")) {
//
//                        SpUtil.setAppUser(res.appUser);
//                        mView.showMsg("suc!");
//                    } else {
//                        mView.showMsg("失败!");
//
//                    }
//                }, e -> {
//                    mView.showMsg("错误!" + e);
////                    mView.hideDialog();
//                }
//
//        ));
//    }

    @Override
    public void poweroff(String id) {
//        mView.showDialog();
        mRxManage.add(mModel.poweroff(id).subscribe(res -> {
//                    mView.hideDialog();
                    if (res.success.equals("success")) {
                        mView.tip_suc();
//                        mView.showMsg("设备即将关闭!");
                    } else {
//                        mView.showMsg("失败!");
                        mView.tip_fail();
                    }
                }, e -> {
                    mView.tip_error();
//                    mView.showMsg("错误!" + e);
//                    mView.hideDialog();
                }

        ));
    }

    @Override
    public void sos(String id, String content) {
//        mView.showDialog();
        mRxManage.add(mModel.sos(id,content).subscribe(res -> {
//                    mView.hideDialog();
                    if (res.success.equals("success")) {
                        String[] msg=content.split(",");
                        List<String> sosList=new ArrayList<String>();
                        for (int i=0;i<msg.length;i++)
                            sosList.add(msg[i]);
                        SpUtil.changeWatchUserListSos(SpUtil.getChoise(),sosList);
                        mView.tip_suc();
                    } else {
//                        mView.showMsg("失败!");
                        mView.need_one();
                    }
                }, e -> {
                    mView.tip_error();
//                    mView.showMsg("错误!" + e);
//                    mView.hideDialog();
                }

        ));
    }

    @Override
    public void upload(String id, String content) {
//        mView.showDialog();
        mRxManage.add(mModel.upload(id,content).subscribe(res -> {
//                    mView.hideDialog();
                    if (res.success.equals("success")) {
                        SpUtil.changePattern(SpUtil.getChoise(),content);
                        mView.tip_suc();
                    } else {
//                        mView.showMsg("失败!");
                        mView.tip_fail();
                    }
                }, e -> {
            mView.tip_error();
//                    mView.hideDialog();
                }

        ));
    }


    @Override
    public void login(String phone, String pasword) {
//        mView.showDialog();
        mRxManage.add(mModel.login(phone,pasword).subscribe(res -> {
//                    mView.hideDialog();
                    if (res.success.equals("success")) {
                        SpUtil.setAppUser(res.appUser);

//                        mView.showMsg("suc");
                        mView.unbind_suc();
                    }else
                    {
//                        mView.showMsg("帐号/密码错误!");
                    }
                }, e -> {
//                    mView.fail();
//                    mView.showMsg("错误!" + e);
//                    mView.hideDialog();
                }

        ));
    }

    @Override
    public void cr(String id) {
//        mView.showDialog();
        mRxManage.add(mModel.cr(id).subscribe(res -> {
//                    mView.hideDialog();
                    if (res.success.equals("success")) {
                        mView.tip_suc();
                        getWatchPoi(id);
                    }else
                    {
//                        mView.showMsg("帐号/密码错误!");
                    }
                }, e -> {
//                    mView.fail();
//                    mView.showMsg("错误!" + e);
//                    mView.hideDialog();
                }

        ));
    }


}
