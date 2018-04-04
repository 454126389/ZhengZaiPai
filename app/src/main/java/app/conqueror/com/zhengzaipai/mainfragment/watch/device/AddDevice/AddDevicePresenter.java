package app.conqueror.com.zhengzaipai.mainfragment.watch.device.AddDevice;

import app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActWifi.WifiContract;
import app.conqueror.com.zhengzaipai.util.SpUtil;

/**
 * Created by Administrator on 2017/7/20.
 */

public class AddDevicePresenter extends AddDeviceContract.Presenter {
    @Override
    public void onStart() {
        mView.initDialog();
        getOfflineMes(SpUtil.getAppUser().phone);
    }


    @Override
    public void auth(int poi,String phone, String id, String nickName, String name) {
        mView.showDialog();

//        mView.initList(null);
        mRxManage.add(mModel.auth(phone,id,nickName,name).subscribe(res -> {
                    mView.hideDialog();
                    if (res.success.equals("success")) {
//                        mView.showMsg("成功");
                        mView.suc();
                        mView.addSuc(poi);
//                        getOfflineMes(phone);
                    }else  if (res.success.equals("authed")) {
//                        mView.showMsg("设备已经授权过了");
                        mView.authed();
                    }else  {
                        mView.fail();
//                        mView.showMsg("错误");
                    }
                }, e -> {
            mView.fail();
//                    mView.showMsg("错误!" + e);
                    mView.hideDialog();
                }

        ));
    }

    @Override
    public void getOfflineMes(String phone) {
        mView.showDialog();

        mRxManage.add(mModel.getOfflineMes(phone).subscribe(res -> {
                    mView.hideDialog();
                    if (res.success.equals("success")) {
//                        mView.showMsg("成功");
                        if(res.mes!=null)
                        {
                            SpUtil.setMess(res.mes,SpUtil.getUsername());
                            mView.initList(res.mes);
                        }
                        else
                        {
                            if(!SpUtil.getMess(SpUtil.getUsername()).equals(""))
                                mView.initList(SpUtil.getMess(SpUtil.getUsername()));
                        }

                    }
                }, e -> {
//                    mView.showMsg("错误!" + e);
                    mView.fail();
                    mView.hideDialog();
                }

        ));
    }
}