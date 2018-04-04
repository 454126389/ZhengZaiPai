package app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActWifi;

import app.conqueror.com.zhengzaipai.mainfragment.watch.device.AddDevice.addlist.binddevice.BindDeviceContract;

/**
 * Created by Administrator on 2017/7/20.
 */

public class WifiPresenter extends WifiContract.Presenter {
    @Override
    public void onStart() {
        mView.initDialog();

    }




    @Override
    public void wifisearch(String id) {
        mView.showDialog();

        mRxManage.add(mModel.wifisearch(id).subscribe(res -> {
                    mView.hideDialog();
                    if (res.success.equals("success")) {
                        mView.suc();
//                        mView.showMsg("请求已发出");
                    }
                }, e -> {
            mView.fail();
//                    mView.showMsg("错误!" + e);
                    mView.hideDialog();
                }

        ));
    }

    @Override
    public void wifiset(String id, String content) {
        mView.showDialog();

        mRxManage.add(mModel.wifiset(id,content).subscribe(res -> {
                    mView.hideDialog();
                    if (res.success.equals("success")) {
//                        mView.showMsg("成功");
                        mView.suc();
                    }
                }, e -> {
                    mView.fail();
//                    mView.showMsg("错误!" + e);
                    mView.hideDialog();
                }

        ));
    }
}