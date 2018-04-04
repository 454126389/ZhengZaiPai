package app.conqueror.com.zhengzaipai.mainfragment.watch.device.AddDevice.addlist.binddevice;

import java.util.List;

import app.conqueror.com.zhengzaipai.mainfragment.watch.device.PhoneBook.BookAdd.BookAddContract;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WatchDevice;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WatchUser;
import app.conqueror.com.zhengzaipai.util.SpUtil;

/**
 * Created by Administrator on 2017/7/20.
 */

public class BindDevicePresenter extends BindDeviceContract.Presenter {
    @Override
    public void onStart() {
        mView.initDialog();

    }


    @Override
    public void bunding(String phone, String code, String nickName, String name) {
        mView.showDialog();

//        {"success":"waitAuth","mes":null}

        mRxManage.add(mModel.bunding(phone,code,nickName,name).subscribe(res -> {
                    mView.hideDialog();
                    if (res.success.equals("success")) {
//                        mView.showMsg("绑定成功");
                       List<WatchUser> watchuserlist=SpUtil.getWatchUserList();
                        watchuserlist.add(res.watchUser);
                        SpUtil.setWatchUserList(watchuserlist);

//                        WatchDevice watchDevice=new WatchDevice()
//                        SpUtil.getAppUser().deviceList
                        login(SpUtil.getUsername(),SpUtil.getPassword());


                    } else if (res.success.equals("waitAuth")) {
                        mView.wait_auth();
//                        mView.showMsg("绑定成功,等待授权");
                        mView.back();
                    }else if (res.success.equals("bundinged")) {
//                        mView.showMsg("已绑定过");
                        mView.wait_did();
                        mView.back();
                    }else if (res.success.equals("error")) {
//                        mView.showMsg("设备没找到");
                        mView.nofind();
                    }else {
                        mView.fail();
//                        mView.showMsg("失败!");

                    }
                }, e -> {
            mView.fail();
//                    mView.showMsg("错误!" + e);
                    mView.hideDialog();
                }

        ));
    }


    @Override
    public void login(String phone, String pasword) {
        mView.showDialog();
        mRxManage.add(mModel.login(phone,pasword).subscribe(res -> {
                    mView.hideDialog();
                    if (res.success.equals("success")) {
                        SpUtil.setAppUser(res.appUser);

                        mView.suc();
//                        mView.back();
                        mView.goMain();

                    }else
                    {
//                        mView.showMsg("帐号/密码错误!");
                    }
                }, e -> {
                    mView.fail();
//                    mView.showMsg("错误!" + e);
                    mView.hideDialog();
                }

        ));
    }

}