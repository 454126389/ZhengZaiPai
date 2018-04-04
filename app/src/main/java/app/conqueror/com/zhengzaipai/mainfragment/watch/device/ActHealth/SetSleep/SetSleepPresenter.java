package app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActHealth.SetSleep;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActHealth.ActHealthContract;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.Wlak;
import app.conqueror.com.zhengzaipai.util.SpUtil;

/**
 * Created by Administrator on 2017/7/20.
 */

public class SetSleepPresenter extends SetSleepContract.Presenter {
    @Override
    public void onStart() {

    }

    @Override
    public void sleepTime(String id, String content) {
        mView.showDialog();
        mRxManage.add(mModel.sleepTime(id,content).subscribe(res -> {
                    mView.hideDialog();
                    if (res.success.equals("success")) {

//                        Wlak wlak= SpUtil.getWatchUserList().get(SpUtil.getChoise()).wlak;


                        SpUtil.changeSleep(SpUtil.getChoise(),content);

                        mView.suc();
//                        mView.showMsg("设置成功");
                    } else {
                        mView.fail();
//                        mView.showMsg("设置失败");
                    }
                }, e -> {
            mView.fail();
//                    mView.showMsg("错误!" + e);
                    mView.hideDialog();
                }

        ));
    }
}