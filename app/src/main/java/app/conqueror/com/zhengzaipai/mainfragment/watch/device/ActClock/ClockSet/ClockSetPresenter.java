package app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActClock.ClockSet;

import java.util.List;

import app.conqueror.com.zhengzaipai.util.SpUtil;

/**
 * Created by Administrator on 2017/7/20.
 */

public class ClockSetPresenter extends ClockSetContract.Presenter {
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
                        mView.fail();
//                        mView.showMsg("失败!");

                    }
                }, e -> {
                      mView.fail();
                    mView.hideDialog();
                }

        ));
    }
}