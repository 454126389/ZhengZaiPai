package app.conqueror.com.zhengzaipai.mainfragment.watch.login;

import app.conqueror.com.zhengzaipai.util.SpUtil;

/**
 * Created by Administrator on 2017/7/20.
 */

public class LoginPresenter extends LoginContract.Presenter {
    @Override
    public void onStart() {
        mView.initDialog();

        if(SpUtil.getIsAutoLogin())
        {
            login(SpUtil.getUsername(),SpUtil.getPassword());
        }

    }


//登录
    @Override
    public void login(String phone, String pasword) {
        mView.showDialog();
        mRxManage.add(mModel.login(phone,pasword).subscribe(res -> {
                    mView.hideDialog();
                    if (res.success.equals("success")) {
                        SpUtil.setAppUser(res.appUser);
                        saveToken(SpUtil.getAppUser().phone,SpUtil.getXGToken(),"1");
                    }else
                    {
                        mView.inputError();
//                        mView.showMsg("帐号/密码错误!");
                    }
                }, e -> {
                    mView.fail();
//                    mView.showMsg("错误!" + e);
                    mView.hideDialog();
                }

        ));
    }

    //保存信鸽TOKEN
    @Override
    public void saveToken(String phone, String token, String dtype) {
        mView.showDialog();
        mRxManage.add(mModel.saveToken(phone,token,dtype).subscribe(res -> {
                    mView.hideDialog();
                    if (res.success.equals("success")) {
//                        mView.showMsg("登录成功!");
                        mView.suc();
                        mView.goMain();
                    }else
                    {
                        mView.fail();
//                        mView.showMsg("后台接入异常!");
                    }
                }, e -> {
                    mView.fail();
//                    mView.showMsg("错误!" + e);
                    mView.hideDialog();
                }

        ));
    }

}