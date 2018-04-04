package app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActSwitch;

import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import app.conqueror.com.zhengzaipai.App;
import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.base.BaseActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.ChatMsg;
import app.conqueror.com.zhengzaipai.util.SpUtil;
import app.conqueror.com.zhengzaipai.util.SwitchUtils;
import butterknife.Bind;
import butterknife.ButterKnife;

public class ActSwitchActivity extends BaseActivity<ActSwitchPresenter, ActSwitchModel> implements ActSwitchContract.View,CompoundButton.OnCheckedChangeListener {


    @Bind(R.id.title_bar_status)
    View titleBarStatus;
    @Bind(R.id.lib_btn_back)
    ImageButton libBtnBack;
    @Bind(R.id.lib_tv_title)
    TextView libTvTitle;
    @Bind(R.id.title_right_btn)
    Button titleRightBtn;
    @Bind(R.id.title_right_iv)
    ImageView titleRightIv;
    @Bind(R.id.lib_tv_right)
    TextView libTvRight;
    @Bind(R.id.lib_bottom_line)
    View libBottomLine;
    @Bind(R.id.switch_compat)
    SwitchCompat switchCompat;

    @Override
    public int getLayoutId() {
        return R.layout.device_act_push_switch;
    }

    @Override
    public void initView() {



        libBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        switchCompat.setChecked(SwitchUtils.changeCode2Switch(Integer.parseInt(SpUtil.getWatchUserList().get(SpUtil.getChoise()).alarmSwitch),1048576));


//        if(SpUtil.getWatchUserList().get(SpUtil.getChoise()).alarmSwitch.equals("1"))
//            switchCompat.setChecked(true);
//        else
//            switchCompat.setChecked(false);
        switchCompat.setOnCheckedChangeListener(this);
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel, this);
    }



    @Override
    public void showMsg(String msg) {
        App.showText(msg);
    }

    @Override
    public void initDialog() {

    }

    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }

    @Override
    public void addMsgList(ChatMsg chatMsg, Boolean isAdd) {

    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b)
            mPresenter.remove(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id,"1");
        else
            mPresenter.remove(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id,"0");
    }


    @Override
    public void suc() {
        App.showText(getString(R.string.tip_suc));
    }

    @Override
    public void fail() {
        App.showText(getString(R.string.tip_fail));
    }

}
