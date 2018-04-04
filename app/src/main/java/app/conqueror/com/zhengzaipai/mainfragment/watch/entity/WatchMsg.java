package app.conqueror.com.zhengzaipai.mainfragment.watch.entity;

/**
 * Created by Administrator on 2017/9/7/007.
 */

public class WatchMsg {
    String ID;
    String TYPE;
    String TIME;
    public String getTIME() {
        return TIME;
    }

    public void setTIME(String TIME) {
        this.TIME = TIME;
    }



    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public String getMSG() {
        return MSG;
    }

    public void setMSG(String MSG) {
        this.MSG = MSG;
    }

    String MSG;

    public WatchMsg(String ID, String TYPE, String MSG) {
        this.ID = ID;
        this.TYPE = TYPE;
        this.MSG = MSG;
    }
    public WatchMsg(String ID, String TYPE, String MSG,String TIME) {
        this.ID = ID;
        this.TYPE = TYPE;
        this.MSG = MSG;
        this.TIME = TIME;
    }
}
