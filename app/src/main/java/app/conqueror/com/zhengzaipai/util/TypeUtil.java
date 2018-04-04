package app.conqueror.com.zhengzaipai.util;

/**
 * Created by Administrator on 2017/7/20.
 */

public class TypeUtil {
    public static int getType(String num)
    {
        int devicenum=Integer.parseInt(num);
        int type = 0;
        if((devicenum>=100&&devicenum<=127)||(devicenum>=140&&devicenum<=142)||(devicenum==159)||(devicenum>=161&&devicenum<=169)||(devicenum>=171&&devicenum<=172))
            type=0;
//        else if((devicenum>=128&&devicenum<=132)||(devicenum==137)||(devicenum==143))
        return type; 
    }
}
