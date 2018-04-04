package app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActHealth.SetStep;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActHealth.SetSleep.SetSleepContract;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.Wlak;
import app.conqueror.com.zhengzaipai.util.SpUtil;

/**
 * Created by Administrator on 2017/7/20.
 */

public class SetStepPresenter extends SetStepContract.Presenter {
    @Override
    public void onStart() {

    }

    @Override
    public void pedo(String id, String content) {
        mView.showDialog();
        mRxManage.add(mModel.pedo(id,content).subscribe(res -> {
                    mView.hideDialog();
                    if (res.success.equals("success")) {
                        Wlak wlak=SpUtil.getWatchUserList().get(SpUtil.getChoise()).wlak;
                        if(wlak==null)
                        {
                            List<String> wlakTimeList=new ArrayList<>();
                            wlakTimeList.add("00:00-00:00");
                            wlakTimeList.add("00:00-00:00");
                            wlakTimeList.add("00:00-00:00");
                            wlak=new Wlak(wlakTimeList,"35","20","false");
                        }
//                            Wlak(List<String> wlakTimeList, String pace, String weight, String onOff)
                        if(content.equals("1"))
                            wlak.setOnOff("true");
                        else  if(content.equals("0"))
                            wlak.setOnOff("false");
                        SpUtil.changeWlak(SpUtil.getChoise(),wlak);
//                        mView.showMsg("设置成功");
                        mView.suc();
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

    @Override
    public void setp(String id, String content) {
        mView.showDialog();
        mRxManage.add(mModel.setp(id,content).subscribe(res -> {
                    mView.hideDialog();
                    if (res.success.equals("success")) {
                        Wlak wlak=SpUtil.getWatchUserList().get(SpUtil.getChoise()).wlak;
                        wlak.setPace(content);
                        SpUtil.changeWlak(SpUtil.getChoise(),wlak);
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

    @Override
    public void weight(String id, String content) {
        mView.showDialog();
        mRxManage.add(mModel.weight(id,content).subscribe(res -> {
                    mView.hideDialog();
                    if (res.success.equals("success")) {

                        Wlak wlak=SpUtil.getWatchUserList().get(SpUtil.getChoise()).wlak;
                        wlak.setWeight(content);
                        SpUtil.changeWlak(SpUtil.getChoise(),wlak);
                        mView.suc();
//                        mView.showMsg("设置成功");
                    } else {
//                        mView.showMsg("设置失败");
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
    public void walktime(String id, String content) {
        mView.showDialog();
        mRxManage.add(mModel.walktime(id,content).subscribe(res -> {
                    mView.hideDialog();
                    if (res.success.equals("success")) {

                        Wlak wlak=SpUtil.getWatchUserList().get(SpUtil.getChoise()).wlak;

                        List<String> wlakTimeList=new ArrayList<String>();
                        wlakTimeList.add(content.split(",")[0]);
                        wlakTimeList.add(content.split(",")[1]);
                        wlakTimeList.add(content.split(",")[2]);
                        wlak.setWlakTimeList(wlakTimeList);
                        SpUtil.changeWlak(SpUtil.getChoise(),wlak);
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