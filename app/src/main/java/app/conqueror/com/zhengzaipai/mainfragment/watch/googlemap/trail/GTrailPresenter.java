package app.conqueror.com.zhengzaipai.mainfragment.watch.googlemap.trail;

import java.util.Collections;

import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WatchPoiEntity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.map.trail.TrailContract;

/**
 * Created by Administrator on 2017/7/20.
 */

public class GTrailPresenter extends GTrailContract.Presenter {
    @Override
    public void onStart() {
        mView.initDialog();

//        getWatchPoi("2016000891");

    }

    @Override
    public void getWatchPoi(String id) {
        mView.showDialog();
        mRxManage.add(mModel.getWatchPoi(id).subscribe(res -> {
                    mView.hideDialog();
                    if (res.success.equals("success")) {
                        WatchPoiEntity watchPoi = new WatchPoiEntity();
                        String[] subres = (res.mes).split(",");
                        watchPoi.setTime(""+(Long.parseLong(subres[0])+28800));
                        watchPoi.setType(Integer.parseInt(subres[1]));
                        watchPoi.setLat(Double.parseDouble(subres[2]));
                        watchPoi.setLng(Double.parseDouble(subres[4]));

                        watchPoi.setSpeed(Float.parseFloat(subres[6]));
                        watchPoi.setDirec(Float.parseFloat(subres[7]));
                        watchPoi.setAlt(Float.parseFloat(subres[8]));
                        watchPoi.setGSM(Integer.parseInt(subres[9]));
                        watchPoi.setGSM_SENCE(Integer.parseInt(subres[10]));
                        watchPoi.setElec(Integer.parseInt(subres[11]));
                        watchPoi.setStep(Integer.parseInt(subres[12]));
                        watchPoi.setTrun(Integer.parseInt(subres[13]));


                        mView.setWatchPoi(watchPoi);
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

    @Override
    public void getTrail(String id, String stime, String etime,Boolean isplay) {
//        mView.showDialog();
        mRxManage.add(mModel.getTrail(id,stime,etime).subscribe(res -> {
                    if (res.success.equals("success")) {
                        if(res.list.size()>0)
                        {
                            Collections.reverse(res.list);
                            mView.move(res.list,isplay);
                        }
                        else
                            mView.trailnull();
//                            mView.showMsg("没有轨迹!");
                    } else {
                        mView.fail();
//                        mView.showMsg("失败!");
                    }
                }, e -> {
                      mView.fail();
//                    mView.showMsg("错误!" + e);
                }

        ));
    }


}