package app.conqueror.com.zhengzaipai.mainfragment.watch.map.trail;

import app.conqueror.com.zhengzaipai.base.RxSchedulers;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.GpsMsg;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WatchPoiResult;
import app.conqueror.com.zhengzaipai.retrofit.Api;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/20.
 */

public class TrailModel implements TrailContract.Model {
    @Override
    public Observable<WatchPoiResult> getWatchPoi(String id) {
        return Api.getInstance().movieService_watch
                .getWatchPoi(id)
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<GpsMsg> getTrail(String id, String stime, String etime) {
        return Api.getInstance().movieService_watch
                .getTrail(id,stime,etime)
                .compose(RxSchedulers.io_main());
    }

}
