// (c)2016 Flipboard Inc, All Rights Reserved.

package app.conqueror.com.zhengzaipai.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CardMsg {
    public @SerializedName("list")
    List<CardStatus> list;
}
