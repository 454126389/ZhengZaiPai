package app.conqueror.com.zhengzaipai.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import app.conqueror.com.zhengzaipai.base.BaseActivity;
import app.conqueror.com.zhengzaipai.entity.Device;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.ChatMsg;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.AppUser;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WatchMsg;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WatchUser;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WatchPoiEntity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.Weather;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WifiMsg;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.Wlak;

/**
 * Created by Administrator on 2016/4/5.
 */
public class SpUtil {
    static SharedPreferences prefs;

    public static void init(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }



    public static void setLanguage(String language) {
        prefs.edit().putString("language", language).commit();
    }

    public static String getLanguage() {
        return prefs.getString("language","zh-CN");
    }

    public static boolean isNight() {
        return prefs.getBoolean("isNight", false);
    }

    public void clearData() {
        prefs.edit().clear().commit();
    }




    public static void setNight(Context context, boolean isNight) {
        prefs.edit().putBoolean("isNight", isNight).commit();
        if (context instanceof BaseActivity)
            ((BaseActivity) context).reload();
    }

    public static void setPhone(Context context, String phone) {
        prefs.edit().putString("phone", phone).commit();
    }

    public static void setEmail(Context context, String email) {
        prefs.edit().putString("email", email).commit();
    }

    public static void setUsername(String username) {
        prefs.edit().putString("username", username).commit();
    }


    public static String getUsername() {
        return prefs.getString("username", "");
    }

    public static void setPassword(String password) {
        prefs.edit().putString("password", password).commit();
    }

    public static String getPassword() {
        return prefs.getString("password", "");
    }



    public static void setXGToken( String xgtoken) {
        prefs.edit().putString("xgtoken", xgtoken).commit();
    }

    public static String getXGToken() {
        return prefs.getString("xgtoken", "");
    }

    public static Device getDevice() {
        return new Gson().fromJson(prefs.getString("device", ""), Device.class);
    }

    public static void setDevice(Device device) {
        prefs.edit().putString("device", new Gson().toJson(device)).commit();
    }


    public static List<Device> getDeviceList() {

        return new Gson().fromJson(prefs.getString("devicelist", ""), new TypeToken<List<Device>>() {
        }.getType());

    }

    public static void setDeviceList(List<Device> list) {
        prefs.edit().putString("devicelist", new Gson().toJson(list)).commit();
    }




    public static Device getCard() {
        return new Gson().fromJson(prefs.getString("card", ""), Device.class);
    }

    public static void setCard(Device card) {

   prefs.edit().putString("card", new Gson().toJson(card)).commit();
}




    public static List<Device> getCardList() {

        return new Gson().fromJson(prefs.getString("cardlist", ""), new TypeToken<List<Device>>() {
        }.getType());

    }

    public static void setCardList(List<Device> list) {
        prefs.edit().putString("cardlist", new Gson().toJson(list)).commit();
    }

    //验证码
    public static void setCode(String code) {
        prefs.edit().putString("code", code).commit();
    }

    public static String getCode() {
        return prefs.getString("code", "");
    }

    public static void setAppid(String appid) {
        prefs.edit().putString("appid", appid).commit();
    }

    public static String getAppid() {
        return prefs.getString("appid", "");
    }


    public static boolean isNoFirst() {
        return prefs.getBoolean("isNoFirst", false);
    }
    public static void setFirst(boolean isFirst) {
        prefs.edit().putBoolean("isNoFirst", isFirst).commit();
    }


    public static WatchPoiEntity getWatchPoiEntity(String id ) {
        return new Gson().fromJson(prefs.getString("watchPoi"+id, ""), WatchPoiEntity.class);
    }

    public static void setWatchPoiEntity(WatchPoiEntity watchPoi,String id) {

        prefs.edit().putString("watchPoi"+id, new Gson().toJson(watchPoi)).commit();
    }



/*    public static WatchResult getUserInfo() {
        return new Gson().fromJson(prefs.getString("userinfo", ""), WatchResult.class);
    }

    public static void setUserInfo(WatchResult userinfo) {

        prefs.edit().putString("userinfo", new Gson().toJson(userinfo)).commit();
    }*/


    public static List<WatchUser> getWatchUserList() {

        return new Gson().fromJson(prefs.getString("watchuserlist", ""), new TypeToken<List<WatchUser>>() {
        }.getType());

    }

    public static void setWatchUserList(List<WatchUser> watchuserlist) {
        prefs.edit().putString("watchuserlist", new Gson().toJson(watchuserlist)).commit();
    }


    public static void setDissw(String  id,List<String> clockList) {
        prefs.edit().putString("sw"+id, new Gson().toJson(clockList)).commit();
    }

    public static List<String> getDissw(String  id) {
        return new Gson().fromJson(prefs.getString("sw"+id, ""), new TypeToken<List<String>>() {
        }.getType());
    }


    public static void changeWatchUserListFlower(int i,String flower) {
        List<WatchUser> watchUserList=getWatchUserList();
        watchUserList.get(i).flower=flower;
        setWatchUserList(watchUserList);
    }


    public static void changeWatchUserListDisable(int i,List<String> disableList) {
        List<WatchUser> watchUserList=getWatchUserList();
        watchUserList.get(i).disableList=disableList;
        setWatchUserList(watchUserList);
    }

    public static void changeWatchUserListClockList(int i,List<String> clockList) {
        List<WatchUser> watchUserList=getWatchUserList();
        watchUserList.get(i).clockList=clockList;
        setWatchUserList(watchUserList);
    }


//    public static void changeWatchUserListalArmSwitch(int i,String armSwitch) {
//        List<WatchUser> watchUserList=getWatchUserList();
//        watchUserList.get(i).alarmSwitch=armSwitch;
//        setWatchUserList(watchUserList);
//    }

    public static void changeWatchUserListSos(int i,List<String> sosList) {
        List<WatchUser> watchUserList=getWatchUserList();
        watchUserList.get(i).sosList=sosList;
        setWatchUserList(watchUserList);
    }

    public static void changeWatchUserListRailsList(int i,List<String> railsList) {
        List<WatchUser> watchUserList=getWatchUserList();
        watchUserList.get(i).railsList=railsList;
        setWatchUserList(watchUserList);
    }

    public static void changeWatchUserListController(int i,String controller) {
        List<WatchUser> watchUserList=getWatchUserList();
        watchUserList.get(i).controller=controller;
        setWatchUserList(watchUserList);
    }

    public static void changeWatchUserListAlarmSwitch(int i,String alarmSwitch) {
        List<WatchUser> watchUserList=getWatchUserList();
        watchUserList.get(i).alarmSwitch=alarmSwitch;
        setWatchUserList(watchUserList);
    }

    public static void changeWatchUserListBookList(int i,List<String> bookList) {
        List<WatchUser> watchUserList=getWatchUserList();
        watchUserList.get(i).bookList=bookList;
        setWatchUserList(watchUserList);
    }

    public static void changePattern(int i,String pattern) {
        List<WatchUser> watchUserList=getWatchUserList();
        watchUserList.get(i).pattern=pattern;
        setWatchUserList(watchUserList);
    }


    public static AppUser getAppUser() {
        return new Gson().fromJson(prefs.getString("appUser", ""), AppUser.class);
    }

    public static void setAppUser(AppUser appUser) {

        prefs.edit().putString("appUser", new Gson().toJson(appUser)).commit();
    }



    public static List<ChatMsg> getChatMsgList(String id) {

        return new Gson().fromJson(prefs.getString("chatmsg"+id, ""), new TypeToken<List<ChatMsg>>() {
        }.getType());

    }

    public static void setChatMsgList(String id,List<ChatMsg> chatmsglist) {
        prefs.edit().putString("chatmsg"+id, new Gson().toJson(chatmsglist)).commit();
    }

    public static void addChatMsgList(String id,ChatMsg chatMsg) {
        List<ChatMsg> chatmsglist=getChatMsgList(id);
        if(chatmsglist==null)
            chatmsglist=new ArrayList<>();
        chatmsglist.add(chatMsg);
        setChatMsgList(id,chatmsglist);
    }




    public static void setChoise( int choise) {
        prefs.edit().putInt("choise", choise).commit();
    }

    public static int getChoise() {
        return prefs.getInt("choise",0);
    }



    public static Weather getWeather() {
        return new Gson().fromJson(prefs.getString("weather", ""), Weather.class);
    }

    public static void setWeather(Weather weather) {

        prefs.edit().putString("weather", new Gson().toJson(weather)).commit();
    }





    public static List<String> getImgList(String id) {

        return new Gson().fromJson(prefs.getString("img"+id, ""), new TypeToken<List<String>>() {
        }.getType());

    }

    public static void setImgList(String id,List<String> imglist) {
        prefs.edit().putString("img"+id, new Gson().toJson(imglist)).commit();
    }

    public static void addImgList(String id,String img) {
        List<String> imalist=getImgList(id);
            if(imalist==null)
                imalist=new ArrayList<>();
        imalist.add(img);
        setImgList(id,imalist);
    }

//    public static void setTempImage(String id,String image) {
//        prefs.edit().putString("tempimage"+id, image).commit();
//    }
//
//    public static String getTempImage(String id) {
//        return prefs.getString("tempimage"+id, "");
//    }

//
//    public static List<Zone> getZoneList(String id) {
//
//        return new Gson().fromJson(prefs.getString("zone"+id, ""), new TypeToken<List<Zone>>() {
//        }.getType());
//
//    }
//
//    public static void setZoneList(String id,List<Zone> zoneList) {
//        prefs.edit().putString("zone"+id, new Gson().toJson(zoneList)).commit();
//    }
//
//    public static void addZoneList(String id,Zone zone) {
//        List<Zone> zoneList=getZoneList(id);
//        if(zoneList==null)
//            zoneList=new ArrayList<>();
//        zoneList.add(zone);
//        setZoneList(id,zoneList);
//    }


    public static List<WifiMsg> getWifiMsgList(String id) {

        return new Gson().fromJson(prefs.getString("wifimsg"+id, ""), new TypeToken<List<WifiMsg>>() {
        }.getType());

    }

    public static void setWifiMsgList(String id,List<WifiMsg> wifimsglist) {
        prefs.edit().putString("wifimsg"+id, new Gson().toJson(wifimsglist)).commit();
    }

    public static void setPhone(String id,String phone) {
        prefs.edit().putString("phone"+id, phone).commit();
    }

    public static String getPhone(String id) {
        return prefs.getString("phone"+id, "");
    }


//    public static Wlak getWeather() {
//        return new Gson().fromJson(prefs.getString("walk", ""), Weather.class);
//    }

    public static void changeWlak(int i,Wlak walk) {
        List<WatchUser> watchUserList=getWatchUserList();
        watchUserList.get(i).wlak=walk;
        setWatchUserList(watchUserList);
    }

    public static void changeSleep(int i,String sleep) {
        List<WatchUser> watchUserList=getWatchUserList();
        watchUserList.get(i).sleep=sleep;
        setWatchUserList(watchUserList);
    }

    public static void setMess(String mess,String username) {
        prefs.edit().putString("mess"+username, mess).commit();
    }

    public static String getMess(String username) {
        return prefs.getString("mess"+username, "");
    }



    public static boolean getIsAutoLogin() {
        return prefs.getBoolean("isAutoLogin", false);
    }
    public static void setIsAutoLogin(boolean isAutoLogin) {
        prefs.edit().putBoolean("isAutoLogin", isAutoLogin).commit();
    }


    public static List<WatchMsg> getWatchMsgList(String username) {

        return new Gson().fromJson(prefs.getString("watchmsg"+username, ""), new TypeToken<List<WatchMsg>>() {
        }.getType());

    }

    public static void setWatchMsgList(String username,List<WatchMsg> watchmsglist) {
        prefs.edit().putString("watchmsg"+username, new Gson().toJson(watchmsglist)).commit();
    }


}
