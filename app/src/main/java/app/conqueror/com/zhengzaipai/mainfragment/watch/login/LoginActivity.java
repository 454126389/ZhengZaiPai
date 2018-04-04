package app.conqueror.com.zhengzaipai.mainfragment.watch.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import app.conqueror.com.zhengzaipai.App;
import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.base.BaseActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.WatchTabActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.AddDevice.addlist.binddevice.BindDeviceActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.ChatMsg;
import app.conqueror.com.zhengzaipai.util.SpUtil;
import butterknife.Bind;
import butterknife.ButterKnife;

import static app.conqueror.com.zhengzaipai.R.id.et_phone;

public class LoginActivity extends BaseActivity<LoginPresenter, LoginModel> implements LoginContract.View, View.OnClickListener, CompoundButton.OnCheckedChangeListener {


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
    @Bind(R.id.account)
    EditText account;
    @Bind(R.id.password)
    EditText password;
    @Bind(R.id.btn_password_mode)
    ImageButton btnPasswordMode;
    @Bind(R.id.auto_login)
    CheckBox autoLogin;
    @Bind(R.id.forgot_pwd)
    TextView forgotPwd;
    @Bind(R.id.login)
    Button login;

    boolean isshow=false;

    @Override
    public int getLayoutId() {
        return R.layout.login_act_login_3;
    }

    @Override
    public void initView() {



            libBtnBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
            account.setText(SpUtil.getUsername());
            password.setText(SpUtil.getPassword());

            if(SpUtil.getIsAutoLogin())
                autoLogin.setChecked(true);
            else
                autoLogin.setChecked(false);
            login.setOnClickListener(this);
        btnPasswordMode.setOnClickListener(this);


    }


    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel, this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                mPresenter.login(account.getText().toString(),password.getText().toString());
                break;
            case R.id.btn_password_mode:
                if(isshow)
                {
                    isshow=false;
                    password.setTransformationMethod(new PasswordTransformationMethod());
                }
                else
                {
                    isshow=true;
                    password.setTransformationMethod(null);
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

    }

    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }

    @Override
    public void goMain() {
//        Intent.setFlag(FLAG_ACTIVITY_NO_HISTORY）
//        startActivity(new Intent(LoginActivity.this, ScannerActivity.class).setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP));
//        finish();


        if(autoLogin.isChecked())
            SpUtil.setIsAutoLogin(true);
        else
            SpUtil.setIsAutoLogin(false);

        SpUtil.setUsername(account.getText().toString());
        SpUtil.setPassword(password.getText().toString());

        //判断是否已经有已绑定设备列表
        if(SpUtil.getAppUser().deviceList==null||SpUtil.getAppUser().deviceList.size()==0)
        {
            startActivity(new Intent(LoginActivity.this, BindDeviceActivity.class).putExtra("phone",SpUtil.getAppUser().phone));
        }else
        {
            Intent intent = new Intent(LoginActivity.this,WatchTabActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b) {

        } else {

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

    @Override
    public void inputError() {
        App.showText(getString(R.string.tx_input_error));
    }

}
