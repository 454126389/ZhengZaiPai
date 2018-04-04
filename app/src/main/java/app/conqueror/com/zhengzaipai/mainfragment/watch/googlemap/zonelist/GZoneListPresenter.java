package app.conqueror.com.zhengzaipai.mainfragment.watch.googlemap.zonelist;

import java.util.List;

import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WatchPoiEntity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.map.zonelist.ZoneListContract;
import app.conqueror.com.zhengzaipai.util.SpUtil;

/**
 * Created by Administrator on 2017/7/20.
 */

public class GZoneListPresenter extends GZoneListContract.Presenter {
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
//                        mView.showMsg("失败!");
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
    public void addZone(String id, List<String> list) {
//        mView.showDialog();

        String content=list.toString().substring(1, list.toString().length() - 1).replaceAll(" ", "");
        mRxManage.add(mModel.addZone(id,content.toString()).subscribe(res -> {

                    if (res.success.equals("success")) {
//                        mView.showMsg("围栏设置成功!");
                        SpUtil.changeWatchUserListRailsList(SpUtil.getChoise(),list);
                        mView.addZoneSuc();
//                        mView.addZoneSuc(list);
                    } else {
                        mView.fail();
//                        mView.showMsg("失败!");
                    }
//                    mView.hideDialog();
                }, e -> {
                    mView.fail();
//                    mView.showMsg("错误!" + e);
//                    mView.hideDialog();
                }

        ));
    }


}