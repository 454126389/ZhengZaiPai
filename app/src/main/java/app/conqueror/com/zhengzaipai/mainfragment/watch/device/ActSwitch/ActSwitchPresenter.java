package app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActSwitch;

import app.conqueror.com.zhengzaipai.util.DateUtils;
import app.conqueror.com.zhengzaipai.util.SpUtil;
import app.conqueror.com.zhengzaipai.util.SwitchUtils;

/**
 * Created by Administrator on 2017/7/20.
 */

public class ActSwitchPresenter extends ActSwitchContract.Presenter {
    @Override
    public void onStart() {
        mView.initDialog();

    }


    @Override
    public void remove(String id, String content) {
        mView.showDialog();
        mRxManage.add(mModel.remove(id, content).subscribe(res -> {
                    mView.hideDialog();
                    if (res.success.equals("success")) {
//                        mView.showMsg("设置成功");
                        mView.suc();


                        SpUtil.changeWatchUserListAlarmSwitch(SpUtil.getChoise(),SwitchUtils.changeSwitch2Code(content,Integer.parseInt(SpUtil.getWatchUserList().get(SpUtil.getChoise()).alarmSwitch),1048576));
//                        mView.addMsgList(new ChatMsg(1,1,content,true,time),true);
                    } else {
                        mView.fail();
//                        mView.addMsgList(new ChatMsg(1,1,content,false,time),true);
                    }
                }, e -> {
            mView.fail();
//                    mView.showMsg("错误!" + e);
                    mView.fail();
                    mView.hideDialog();
                }

        ));
    }
}