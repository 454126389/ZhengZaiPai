package app.conqueror.com.zhengzaipai.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/15/015.
 */

public class SwitchUtils {


//    计步功能开关               4
//    取下手表短信报警开关       8
//    SOS短信报警开关            65535
//    低电短信报警开关           131072
//    手环拆除报警开关           1048576


    public static Boolean changeCode2Switch(int v,int code)
    {
        if ((v & code) == code)
                return true;
        else
            return false;
    }


    public static String changeSwitch2Code(String content,int v,int code)
    {
        if (content.equals("1")) {
            v |= code;
        } else {
            v &= ~code;
        }
        return ""+v;
    }

}
