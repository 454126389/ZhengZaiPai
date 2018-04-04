package app.conqueror.com.zhengzaipai.app.SetFragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.app.Function.FunctionModel;
import app.conqueror.com.zhengzaipai.app.Function.FunctionPresenter;
import app.conqueror.com.zhengzaipai.app.SetFragment.AboutFragment;
import app.conqueror.com.zhengzaipai.app.SetFragment.DeviceSetFragment;
import app.conqueror.com.zhengzaipai.app.SetFragment.PoiSetFragment;
import app.conqueror.com.zhengzaipai.base.BaseActivity;


/**
 * Created by kumaha on 16/7/11.
 */
public class SettingActivity extends BaseActivity<FunctionPresenter, FunctionModel> {
    private Toolbar mToolbar;

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public void initView() {
        Bundle bundle = this.getIntent().getExtras();
        //接收name值
        String settype = bundle.getString("settype");
        if(settype.equals("deviceset"))
            getFragmentManager().beginTransaction().replace(R.id.content_settings, new DeviceSetFragment()).commit();
        else if(settype.equals("poirecset"))
            getFragmentManager().beginTransaction().replace(R.id.content_settings, new PoiSetFragment()).commit();
        else if(settype.equals("about"))
            getFragmentManager().beginTransaction().replace(R.id.content_settings, new AboutFragment()).commit();
        else if(settype.equals("recorderset"))
            getFragmentManager().beginTransaction().replace(R.id.content_settings, new RecorderSetFragment()).commit();

        setTitle("设置");
    }

    @Override
    public void initPresenter() {

    }


}
