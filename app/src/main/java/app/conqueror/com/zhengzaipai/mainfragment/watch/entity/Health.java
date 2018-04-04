package app.conqueror.com.zhengzaipai.mainfragment.watch.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/10/26/026.
 */

public class Health implements Serializable {
    String time;
    String hour;
    String num;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
