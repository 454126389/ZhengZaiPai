package app.conqueror.com.zhengzaipai.mainfragment.watch.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2017/9/5/005.
 */

public class ActHealthResult {
    public String success;
    public @SerializedName("mes")
    List<Health> health;

//    {"success":"success","mes":{"time":"2017-10-26","hour":11,"num":1}}

}
