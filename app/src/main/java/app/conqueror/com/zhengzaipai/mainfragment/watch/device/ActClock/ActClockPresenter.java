package app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActClock;

import java.util.List;

import app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActClock.ClockSet.ClockSetContract;
import app.conqueror.com.zhengzaipai.util.SpUtil;

/**
 * Created by Administrator on 2017/7/20.
 */

public class ActClockPresenter extends ActClockContract.Presenter {
    @Override
    public void onStart() {
        mView.initDialog();

    }

    @Override
    public void remind(String id,List<String> list) {
        mView.showDialog();
        String content=list.toString().substring(1, list.toString().length() - 1).replaceAll(" ", "");

        mRxManage.add(mModel.remind(id,content).subscribe(res -> {
                    mView.hideDialog();
                    if (res.success.equals("success")) {
                        SpUtil.changeWatchUserListClockList(SpUtil.getChoise(),list);
//                        mView.showMsg("修改成功");
                        mView.suc();
                    } else {
//                        mView.showMsg("失败!");
                        mView.fail();

                    }
                }, e -> {
//                    mView.showMsg("错误!" + e);
                    mView.fail();
                    mView.hideDialog();
                }

        ));
    }
}