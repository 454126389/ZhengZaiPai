package app.conqueror.com.zhengzaipai.mainfragment.watch.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2017/9/7/007.
 */

public class AuthResult {
    public String success;
    public @SerializedName("mes")
    WatchUser appUser;

}
