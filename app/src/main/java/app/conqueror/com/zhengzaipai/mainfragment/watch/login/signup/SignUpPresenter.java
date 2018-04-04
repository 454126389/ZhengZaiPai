package app.conqueror.com.zhengzaipai.mainfragment.watch.login.signup;

import app.conqueror.com.zhengzaipai.mainfragment.watch.login.LoginContract;
import app.conqueror.com.zhengzaipai.util.SpUtil;

/**
 * Created by Administrator on 2017/7/20.
 */

public class SignUpPresenter extends SignUpContract.Presenter {
    @Override
    public void onStart() {
        mView.initDialog();

    }





    @Override
    public void sendVcode(String phone) {
        mView.showDialog();
        mRxManage.add(mModel.sendVcode(phone).subscribe(res -> {
                    mView.hideDialog();
                    if (res.success.equals("success")) {
                        mView.suc();
//                        mView.showMsg("验证码发送成功!");
                    }else
                    {
                        mView.fail();
//                        mView.showMsg("验证码发送异常!");
                    }
                }, e -> {
                    mView.fail();
//                    mView.showMsg("错误!" + e);
                    mView.hideDialog();
                }

        ));
    }

    @Override
    public void register(String phone, String password, String vcode) {
        mView.showDialog();
        mRxManage.add(mModel.register(phone,password,vcode).subscribe(res -> {
                    mView.hideDialog();
                    if (res.success.equals("success")) {
//                        mView.showMsg("注册成功!");
                        mView.suc();
                        mView.goMain();
                    }else
                    {
                        mView.vcode_error();
//                        mView.showMsg("注册失败，检查验证码是否过期");
                    }
                }, e -> {
                    mView.fail();
//                    mView.showMsg("错误!" + e);
                    mView.hideDialog();
                }

        ));
    }
}