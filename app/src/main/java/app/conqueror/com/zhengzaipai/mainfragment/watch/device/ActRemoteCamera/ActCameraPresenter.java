package app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActRemoteCamera;

import app.conqueror.com.zhengzaipai.mainfragment.watch.device.LoveBons.LoveBonsContract;

/**
 * Created by Administrator on 2017/7/20.
 */

public class ActCameraPresenter extends ActCameraContract.Presenter {
    @Override
    public void onStart() {
        mView.initDialog();

    }



    @Override
    public void rcapture(String id) {
        mView.showDialog();
        mRxManage.add(mModel.rcapture(id).subscribe(res -> {
                    mView.hideDialog();
                    if (res.success.equals("success")) {

//                        mView.showMsg("请求已发出!");
                        mView.suc();
                    } else {
//                        mView.showMsg("失败!");
                        mView.fail();

                    }
                }, e -> {
                    mView.fail();
//                    mView.showMsg("错误!" + e);
                    mView.hideDialog();
                }

        ));
    }
}