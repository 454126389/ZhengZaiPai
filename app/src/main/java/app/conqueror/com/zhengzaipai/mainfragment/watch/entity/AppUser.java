package app.conqueror.com.zhengzaipai.mainfragment.watch.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2017/9/7/007.
 */

public class AppUser {
    public String phone;
    public String nickName;
    public Boolean sex;
    public String address;
    public String synopsis;
    public @SerializedName("devices")
    List<WatchDevice> deviceList;

}
