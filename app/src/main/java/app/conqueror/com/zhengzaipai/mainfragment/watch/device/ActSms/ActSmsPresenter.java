package app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActSms;

import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.ChatMsg;
import app.conqueror.com.zhengzaipai.util.DateUtils;
import app.conqueror.com.zhengzaipai.util.SpUtil;
import app.conqueror.com.zhengzaipai.util.SwitchUtils;

/**
 * Created by Administrator on 2017/7/20.
 */

public class ActSmsPresenter extends ActSmsContract.Presenter {
    @Override
    public void onStart() {
        mView.initDialog();
    }

    @Override
    public void center(String id, String content) {
        mView.showDialog();
        mRxManage.add(mModel.center(id, content).subscribe(res -> {
                    mView.hideDialog();
                    if (res.success.equals("success")) {

                        SpUtil.changeWatchUserListController(SpUtil.getChoise(),content);
                        mView.suc();

//                        mView.showMsg("设置成功");
                    } else {
                        mView.fail();
                    }
                }, e -> {
                    mView.fail();
//                    mView.showMsg("错误!" + e);
                    mView.hideDialog();
                }

        ));
    }

    @Override
    public void remove(String id, String content) {
        mView.showDialog();
        mRxManage.add(mModel.remove(id, content).subscribe(res -> {
                    mView.hideDialog();
                    if (res.success.equals("success")) {

                        SpUtil.changeWatchUserListAlarmSwitch(SpUtil.getChoise(),SwitchUtils.changeSwitch2Code(content,Integer.parseInt(SpUtil.getWatchUserList().get(SpUtil.getChoise()).alarmSwitch),1048576));
                        mView.suc();
//                        mView.showMsg("设置成功");
                    } else {
                        mView.fail();
                    }
                }, e -> {
                    mView.fail();
//                    mView.showMsg("错误!" + e);
                    mView.hideDialog();
                }

        ));
    }

    @Override
    public void sossms(String id, String content) {
        mView.showDialog();
        mRxManage.add(mModel.sossms(id, content).subscribe(res -> {
                    mView.hideDialog();
                    if (res.success.equals("success")) {

                        SpUtil.changeWatchUserListAlarmSwitch(SpUtil.getChoise(),SwitchUtils.changeSwitch2Code(content,Integer.parseInt(SpUtil.getWatchUserList().get(SpUtil.getChoise()).alarmSwitch),65535));
                        mView.suc();
//                        mView.showMsg("设置成功");
                    } else {
                        mView.fail();
                    }
                }, e -> {
            mView.fail();
//                    mView.showMsg("错误!" + e);
                    mView.hideDialog();
                }

        ));
    }

    @Override
    public void lowbat(String id, String content) {
        mView.showDialog();
        mRxManage.add(mModel.lowbat(id, content).subscribe(res -> {
                    mView.hideDialog();
                    if (res.success.equals("success")) {
                        SpUtil.changeWatchUserListAlarmSwitch(SpUtil.getChoise(),SwitchUtils.changeSwitch2Code(content,Integer.parseInt(SpUtil.getWatchUserList().get(SpUtil.getChoise()).alarmSwitch),131072));
//                        mView.showMsg("设置成功");
                        mView.suc();
                    } else {
                    }
                }, e -> {
                    mView.fail();
//                    mView.showMsg("错误!" + e);
                    mView.hideDialog();
                }

        ));
    }
}