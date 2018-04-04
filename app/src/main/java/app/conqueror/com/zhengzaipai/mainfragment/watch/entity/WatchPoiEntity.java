package app.conqueror.com.zhengzaipai.mainfragment.watch.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/5/27.
 */
public class WatchPoiEntity implements Serializable {
    private String time;
    private int type;
    private double lat;
    private double lng;
    private float speed;
//    方向
    private float direc;
//    海拔
    private float alt;
    //卫星数
    private int GSM;
//    信号强度
    private int GSM_SENCE;
    //电量
    private int elec;
//    步数
    private int step;
//    翻转
    private int trun;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getDirec() {
        return direc;
    }

    public void setDirec(float direc) {
        this.direc = direc;
    }

    public float getAlt() {
        return alt;
    }

    public void setAlt(float alt) {
        this.alt = alt;
    }

    public int getGSM() {
        return GSM;
    }

    public void setGSM(int GSM) {
        this.GSM = GSM;
    }

    public int getGSM_SENCE() {
        return GSM_SENCE;
    }

    public void setGSM_SENCE(int GSM_SENCE) {
        this.GSM_SENCE = GSM_SENCE;
    }

    public int getElec() {
        return elec;
    }

    public void setElec(int elec) {
        this.elec = elec;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int getTrun() {
        return trun;
    }

    public void setTrun(int trun) {
        this.trun = trun;
    }



/*   private ArrayList<StoriesEntity> stories ;
    public void setStories(ArrayList<StoriesEntity> stories){
        this.stories = stories;
    }
    public ArrayList<StoriesEntity> getStories(){
        return this.stories;
    }*/
}
