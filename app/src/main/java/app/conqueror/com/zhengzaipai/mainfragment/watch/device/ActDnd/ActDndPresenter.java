package app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActDnd;

import java.util.List;

import app.conqueror.com.zhengzaipai.mainfragment.watch.device.LoveBons.LoveBonsContract;
import app.conqueror.com.zhengzaipai.util.SpUtil;

/**
 * Created by Administrator on 2017/7/20.
 */

public class ActDndPresenter extends ActDndContract.Presenter {
    @Override
    public void onStart() {
        mView.initDialog();

    }

    @Override
    public void silenceTime(String id, String content,List<String> disableList) {
        mView.showDialog();
        mRxManage.add(mModel.silenceTime(id,content).subscribe(res -> {
                    mView.hideDialog();
                    if (res.success.equals("success")) {
//                        SpUtil.changeWatchUserListDisable(SpUtil.getChoise(),disableList);
//                        mView.showMsg("修改成功");
                        mView.suc();
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