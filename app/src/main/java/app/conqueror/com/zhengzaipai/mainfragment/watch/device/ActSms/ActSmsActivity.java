package app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActSms;

import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import app.conqueror.com.zhengzaipai.App;
import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.base.BaseActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActSwitch.ActSwitchContract;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActSwitch.ActSwitchModel;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActSwitch.ActSwitchPresenter;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.ChatMsg;
import app.conqueror.com.zhengzaipai.util.SpUtil;
import app.conqueror.com.zhengzaipai.util.SwitchUtils;
import butterknife.Bind;
import butterknife.ButterKnife;

public class ActSmsActivity extends BaseActivity<ActSmsPresenter, ActSmsModel> implements ActSmsContract.View, CompoundButton.OnCheckedChangeListener,View.OnClickListener {


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
    @Bind(R.id.tv_phone)
    TextView tvPhone;
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.divider1)
    View divider1;
    @Bind(R.id.iv_lowbattery)
    ImageView ivLowbattery;
    @Bind(R.id.tv_battery)
    TextView tvBattery;
    @Bind(R.id.switch_battery)
    SwitchCompat switchBattery;
    @Bind(R.id.divider2)
    View divider2;
    @Bind(R.id.iv_sos)
    ImageView ivSos;
    @Bind(R.id.tv_sos)
    TextView tvSos;
    @Bind(R.id.switch_sos)
    SwitchCompat switchSos;
    @Bind(R.id.divider3)
    View divider3;
    @Bind(R.id.iv_remove)
    ImageView ivRemove;
    @Bind(R.id.tv_remove)
    TextView tvRemove;
    @Bind(R.id.switch_remove)
    SwitchCompat switchRemove;
    @Bind(R.id.divider4)
    View divider4;
    @Bind(R.id.layout_remove)
    LinearLayout layoutRemove;
    @Bind(R.id.contentPanel)
    LinearLayout contentPanel;
    @Bind(R.id.btn_ok)
    Button btnOk;

    @Override
    public int getLayoutId() {
        return R.layout.device_act_sms;
    }

    @Override
    public void initView() {
        libBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

//        if(SpUtil.getWatchUserList().get(SpUtil.getChoise()).alarmSwitch.equals("1"))
//            switchCompat.setChecked(true);
//        else
//            switchCompat.setChecked(false);


        switchBattery.setChecked(SwitchUtils.changeCode2Switch(Integer.parseInt(SpUtil.getWatchUserList().get(SpUtil.getChoise()).alarmSwitch),131072));
        switchSos.setChecked(SwitchUtils.changeCode2Switch(Integer.parseInt(SpUtil.getWatchUserList().get(SpUtil.getChoise()).alarmSwitch),65535));
        switchRemove.setChecked(SwitchUtils.changeCode2Switch(Integer.parseInt(SpUtil.getWatchUserList().get(SpUtil.getChoise()).alarmSwitch),1048576));

        etPhone.setText(SpUtil.getWatchUserList().get(SpUtil.getChoise()).controller);
        switchBattery.setOnCheckedChangeListener(this);
        switchSos.setOnCheckedChangeListener(this);
        switchRemove.setOnCheckedChangeListener(this);
        btnOk.setOnClickListener(this);

    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel, this);
    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.switch_battery:
                if (b)
                    mPresenter.lowbat(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id, "1");
                else
                    mPresenter.lowbat(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id, "0");
                break;
            case R.id.switch_sos:
                if (b)
                    mPresenter.sossms(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id, "1");
                else
                    mPresenter.sossms(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id, "0");
                break;
            case R.id.switch_remove:
                if (b)
                    mPresenter.remove(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id, "1");
                else
                    mPresenter.remove(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id, "0");
                break;
        }
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
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_ok:
                        if (etPhone.getText().toString().equals("")||etPhone.getText().toString().length()==0)
                            App.showText(getString(R.string.no_null));
                        else
                            mPresenter.center(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id,etPhone.getText().toString());
                break;
        }
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
