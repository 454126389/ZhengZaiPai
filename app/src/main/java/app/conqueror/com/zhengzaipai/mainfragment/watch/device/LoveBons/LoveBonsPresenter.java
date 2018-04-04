package app.conqueror.com.zhengzaipai.mainfragment.watch.device.LoveBons;

import java.util.ArrayList;
import java.util.List;

import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WatchPoiEntity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WatchUser;
import app.conqueror.com.zhengzaipai.mainfragment.watch.map.zonelist.ZoneListContract;
import app.conqueror.com.zhengzaipai.util.SpUtil;

/**
 * Created by Administrator on 2017/7/20.
 */

public class LoveBonsPresenter extends LoveBonsContract.Presenter {
    @Override
    public void onStart() {
        mView.initDialog();

    }

    @Override
    public void flower(String id, String content) {
        mView.showDialog();
        mRxManage.add(mModel.flower(id,content).subscribe(res -> {
                    mView.hideDialog();
                    if (res.success.equals("success")) {
                        SpUtil.changeWatchUserListFlower(SpUtil.getChoise(),content);
                        mView.suc();
//                        mView.showMsg("爱心修改完成");
                    } else {
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
}