package app.conqueror.com.zhengzaipai.mainfragment.watch.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2017/9/6/006.
 */

public class Wlak {
//    public String wlakTime;
    public @SerializedName("wlakTime")
    List<String> wlakTimeList;
    public String pace;
    public String weight;
    public String onOff;

    public Wlak(List<String> wlakTimeList, String pace, String weight, String onOff) {
        this.wlakTimeList = wlakTimeList;
        this.pace = pace;
        this.weight = weight;
        this.onOff = onOff;
    }

    public List<String> getWlakTimeList() {
        return wlakTimeList;
    }

    public void setWlakTimeList(List<String> wlakTimeList) {
        this.wlakTimeList = wlakTimeList;
    }

    public String getPace() {
        return pace;
    }

    public void setPace(String pace) {
        this.pace = pace;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getOnOff() {
        return onOff;
    }

    public void setOnOff(String onOff) {
        this.onOff = onOff;
    }
}
