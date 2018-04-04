package app.conqueror.com.zhengzaipai.mainfragment.watch.login.signup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import app.conqueror.com.zhengzaipai.App;
import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.base.BaseActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.AddDevice.addlist.binddevice.BindDeviceActivity;
import app.conqueror.com.zhengzaipai.util.SpUtil;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/19/019.
 */

public class SignUpActivity extends BaseActivity<SignUpPresenter, SignUpModel> implements SignUpContract.View, View.OnClickListener {


    @Bind(R.id.lib_btn_back)
    ImageButton libBtnBack;

    @Bind(R.id.btn_vcode)
    Button btn_vcode;

    @Bind(R.id.btn_ok)
    Button btn_ok;

    @Bind(R.id.et_phone)
    EditText et_phone;

    @Bind(R.id.et_vcode)
    EditText et_vcode;

    @Bind(R.id.et_password)
    EditText et_password;
    @Bind(R.id.rl_vcode)
    RelativeLayout rlVcode;


    @Override
    public int getLayoutId() {
        return R.layout.login_act_sign_up;
    }

    @Override
    public void initView() {
        libBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_vcode.setOnClickListener(this);
        btn_ok.setOnClickListener(this);


        //tw版本
        if (SpUtil.getLanguage().equals("zh-TW"))
        {
            et_vcode.setText("1111");
            rlVcode.setVisibility(View.GONE);
        }


    }


    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel, this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_vcode:
                if (et_phone.getText().toString().length() == 0) {
                    App.showText(getString(R.string.tx_number_null));
                } else
                    mPresenter.sendVcode(et_phone.getText().toString());
                break;
            case R.id.btn_ok:
                if (et_phone.getText().toString().length() == 0) {
                    App.showText(getString(R.string.tx_number_null));
//                    App.showText("号码不能为空");
                } else if (et_vcode.getText().toString().length() == 0) {
                    App.showText(getString(R.string.tx_vcode_null));
                } else if (et_password.getText().toString().length() == 0) {
                    App.showText(getString(R.string.tx_psw_null));
                } else {
                    mPresenter.register(et_phone.getText().toString(), et_password.getText().toString(), et_vcode.getText().toString());
                }
                break;
        }
    }

    @Override
    public void showMsg(String msg) {
        App.showText(msg);
    }

    @Override
    public void initDialog() {
        initBaseDialog();
    }

    @Override
    public void showDialog() {
        showBaseDialog();
    }

    @Override
    public void hideDialog() {
        hideBaseDialog();
    }

    @Override
    public void goMain() {
        finish();
        startActivity(new Intent(SignUpActivity.this, BindDeviceActivity.class).putExtra("phone", et_phone.getText().toString()));
    }

    @Override
    public void suc() {
        App.showText(getString(R.string.tip_suc));
    }

    @Override
    public void fail() {
        App.showText(getString(R.string.tip_fail));
    }

    @Override
    public void vcode_error() {
        App.showText(getString(R.string.tx_vcode_error));
    }


}
