package app.conqueror.com.zhengzaipai.mainfragment.watch.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2017/9/5/005.
 */

public class GoogleResult {
//    public String results;
    public @SerializedName("results")
    List<AddressResult> addressResult;
    public String status;
}
