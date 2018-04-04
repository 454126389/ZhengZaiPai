package app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActCallBack;

import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WatchPoiEntity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.map.trail.TrailContract;

/**
 * Created by Administrator on 2017/7/20.
 */

public class CallBackPresenter extends CallBackContract.Presenter {
    @Override
    public void onStart() {
        mView.initDialog();

    }



    @Override
    public void monitor(String id,String content) {
        mRxManage.add(mModel.monitor(id,content).subscribe(res -> {

                    if (res.success.equals("success")) {
                        mView.suc();
                    } else {
                        mView.fail();

                    }
                }, e -> {
//                    mView.showMsg("错误!" + e);
                    mView.fail();
//                    mView.hideDialog();
                }

        ));
    }
}