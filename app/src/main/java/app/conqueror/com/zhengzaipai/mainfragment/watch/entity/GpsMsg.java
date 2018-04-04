// (c)2016 Flipboard Inc, All Rights Reserved.

package app.conqueror.com.zhengzaipai.mainfragment.watch.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import app.conqueror.com.zhengzaipai.entity.CardStatus;

public class GpsMsg {
    public String success;
    public @SerializedName("mes")
    List<Gps> list;
}
