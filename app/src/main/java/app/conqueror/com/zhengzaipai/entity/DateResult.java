// (c)2016 Flipboard Inc, All Rights Reserved.

package app.conqueror.com.zhengzaipai.entity;

import android.bluetooth.BluetoothClass;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DateResult {
    public boolean result;
    public String msg;
    public @SerializedName("devicelist")
    List<Device> devicelist;

}
