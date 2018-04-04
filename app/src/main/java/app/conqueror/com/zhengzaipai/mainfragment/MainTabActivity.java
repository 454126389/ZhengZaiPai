package app.conqueror.com.zhengzaipai.mainfragment;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.mainfragment.LockDevice.LockDeviceActivity;
import app.conqueror.com.zhengzaipai.base.BaseTabActivity;
import app.conqueror.com.zhengzaipai.mainfragment.onway.OnWayActivity;
import app.conqueror.com.zhengzaipai.mainfragment.me.AppSettingActivity;
import app.conqueror.com.zhengzaipai.receiver.common.NotificationService;
import app.conqueror.com.zhengzaipai.view.pop.PopupMenuUtil;

import static com.tencent.android.tpush.horse.DefaultServer.b;

public class MainTabActivity extends BaseTabActivity {
    private NotificationService notificationService;
    private long exitTime = 0;
    private List<Class<?>> ClassList=new ArrayList<Class<?>>();
    private List<Integer> resIdNormaldList=new ArrayList<Integer>();
    private List<Integer> resIdClickdList=new ArrayList<Integer>();
    private Integer[] tabTitle= new Integer[] { R.string.tab_onway,R.string.tab_mydevice,R.string.tab_zzwarn,R.string.tab_usercenter };
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ClassList.add(OnWayActivity.class);
        ClassList.add(LockDeviceActivity.class);
        ClassList.add(LockDeviceActivity.class);
        ClassList.add(AppSettingActivity.class);

        resIdNormaldList.add(R.mipmap.tab_onway);
        resIdNormaldList.add(R.mipmap.tab_device);
        resIdNormaldList.add(R.mipmap.tab_warn);
        resIdNormaldList.add(R.mipmap.tab_me);

        resIdClickdList.add(R.mipmap.tab_onway_click);
        resIdClickdList.add(R.mipmap.tab_device_click);
        resIdClickdList.add(R.mipmap.tab_warn_click);
        resIdClickdList.add(R.mipmap.tab_me_click);


        SetData(ClassList,resIdNormaldList,resIdClickdList,tabTitle);

        notificationService = NotificationService.getInstance(this);



    }



}