package app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActHealth;


import android.content.Intent;
import android.os.Parcelable;

import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.Health;
import app.conqueror.com.zhengzaipai.util.Actions;

/**
 * Created by Administrator on 2017/7/20.
 */

public class ActHealthPresenter extends ActHealthContract.Presenter {
    @Override
    public void onStart() {
//        mView.initDialog();

    }

    @Override
    public void getWlakByIdAndTime(String id, String time) {
        mView.showDialog();
        mRxManage.add(mModel.getWlakByIdAndTime(id,time).subscribe(res -> {
                    mView.hideDialog();
                    if (res.success.equals("success")) {
//                        JSONObject object = null;//一个user对象，使用一个JSONObject对象来装
//                        try {
//                            object = new JSONObject(res.);
//                            mView.getTvdata().setText(object.getString("num"));


//                        mView.getTvdata().setText(res.health.get(0).getNum());
                        Intent intent = new Intent();
                        intent.setAction(Actions.ACTION_CHANGE_STIP);
                        List<Health> healthList= res.health;
                        intent.putExtra("step", (Serializable) healthList);
                        mView.sendAction(intent);


//                        } catch (JSONException e) {
//                            mView.getTvdata().setText("0");
//                            e.printStackTrace();
//                        }

//                        mView.showMsg("suc!");
                    } else {

                        Intent intent = new Intent();
                        intent.setAction(Actions.ACTION_CHANGE_STIP);
                        List<Health> healthList= new ArrayList<>();
                        intent.putExtra("step", (Serializable) healthList);
                        mView.sendAction(intent);

//                        mView.getTvdata().setText("0");
//                        mView.showMsg("失败!");

                    }
                }, e -> {
//                    mView.showMsg("错误!" + e);
                    mView.hideDialog();
                }

        ));
    }

    @Override
    public void getSleepByIdAndTime(String id, String time) {
        mView.showDialog();
        mRxManage.add(mModel.getSleepByIdAndTime(id,time).subscribe(res -> {
                    mView.hideDialog();
                    if (res.success.equals("success")) {
//                        JSONObject object = null;//一个user对象，使用一个JSONObject对象来装
//                        try {
//                            object = new JSONObject(res.mes);
//                            mView.getTvdata().setText(res.health.get(0).getNum());
                        Intent intent = new Intent();
                        intent.setAction(Actions.ACTION_CHANGE_SLEEP);
                        List<Health> healthList= res.health;
                        intent.putExtra("sleep", (Serializable) healthList);
                        mView.sendAction(intent);


//                        } catch (JSONException e) {
//                            mView.getTvdata().setText("0");
//                            e.printStackTrace();
//                        }
//                        mView.showMsg("suc!");
                    } else {

                        Intent intent = new Intent();
                        intent.setAction(Actions.ACTION_CHANGE_SLEEP);
                        List<Health> healthList= new ArrayList<>();
                        intent.putExtra("sleep", (Serializable) healthList);
                        mView.sendAction(intent);

//                        mView.showMsg("失败!");
//                        mView.getTvdata().setText("0");
                    }
                }, e -> {
//                    mView.showMsg("错误!" + e);
                    mView.hideDialog();
                }

        ));
    }


}