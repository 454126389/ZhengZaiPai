package app.conqueror.com.zhengzaipai.mainfragment.watch.entity;

/**
 * Created by Administrator on 2017/9/7/007.
 */

public class ChatMsg {
    public int from;
    public int type;
    public String name;
    public String msg;
    public Boolean isSuc;
    public String time;
    //        from  0 left，1 right
//        type 0  msg,1  sound
    public ChatMsg(int from, int type, String name, String msg, Boolean isSuc, String time) {
        this.from = from;
        this.type = type;
        this.name = name;
        this.msg = msg;
        this.isSuc = isSuc;
        this.time = time;
    }
}
