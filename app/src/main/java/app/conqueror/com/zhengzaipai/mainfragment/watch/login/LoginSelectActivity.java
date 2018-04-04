package app.conqueror.com.zhengzaipai.mainfragment.watch.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.mainfragment.watch.login.signup.SignUpActivity;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/19/019.
 */

public class LoginSelectActivity extends Activity implements View.OnClickListener {
    @Bind(R.id.center_point)
    View centerPoint;
    @Bind(R.id.btn_account)
    Button btnAccount;
    @Bind(R.id.btn_wechat)
    Button btnWechat;
    @Bind(R.id.btn_sign_up)
    TextView btnSignUp;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0){
            finish();
            return;
        }

        setContentView(R.layout.login_act_login_2);
        ButterKnife.bind(this);

        btnAccount.setOnClickListener(this);
        btnWechat.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_account:
                startActivity(new Intent(LoginSelectActivity.this, LoginActivity.class));
                break;
            case R.id.btn_wechat:
                startActivity(new Intent(LoginSelectActivity.this, LoginActivity.class));
                break;
            case R.id.btn_sign_up:
                startActivity(new Intent(LoginSelectActivity.this, SignUpActivity.class));
                break;
        }
    }
}
