package app.conqueror.com.zhengzaipai.mainfragment.LockDevice.mirror;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.base.BaseTabActivity;
import app.conqueror.com.zhengzaipai.mainfragment.LockDevice.mirror.MonitorActivity.MonitorActivity;
import app.conqueror.com.zhengzaipai.mainfragment.LockDevice.mirror.PoiRec.PoiRecActivity;
import app.conqueror.com.zhengzaipai.mainfragment.LockDevice.mirror.Search.SearchWebActivity;


//在路上
public class MirrorTab extends BaseTabActivity
{
    private List<Class<?>> ClassList=new ArrayList<Class<?>>();
    private List<Integer> resIdNormaldList=new ArrayList<Integer>();
    private List<Integer> resIdClickdList=new ArrayList<Integer>();
    private Integer[] tabTitle= new Integer[] { R.string.tab_mirror_1,R.string.tab_mirror_2,R.string.tab_mirror_3,R.string.tab_mirror_4 };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ClassList.add(SearchWebActivity.class);
        ClassList.add(MonitorActivity.class);
        ClassList.add(PoiRecActivity.class);
        ClassList.add(null);

        resIdNormaldList.add(R.mipmap.tab_search);
        resIdNormaldList.add(R.mipmap.tab_mirror);
        resIdNormaldList.add(R.mipmap.tab_data);
        resIdNormaldList.add(R.mipmap.tab_shutdown);

        resIdClickdList.add(R.mipmap.tab_search_click);
        resIdClickdList.add(R.mipmap.tab_mirror_click);
        resIdClickdList.add(R.mipmap.tab_data_click);
        resIdClickdList.add(R.mipmap.tab_shutdown_click);


        SetData(ClassList,resIdNormaldList,resIdClickdList,tabTitle);
    }
}
