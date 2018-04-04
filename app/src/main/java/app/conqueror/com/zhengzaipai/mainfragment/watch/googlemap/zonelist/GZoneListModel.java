package app.conqueror.com.zhengzaipai.mainfragment.watch.googlemap.zonelist;

import app.conqueror.com.zhengzaipai.base.RxSchedulers;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WatchPoiResult;
import app.conqueror.com.zhengzaipai.mainfragment.watch.map.zonelist.ZoneListContract;
import app.conqueror.com.zhengzaipai.retrofit.Api;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/20.
 */

public class GZoneListModel implements GZoneListContract.Model {
    @Override
    public Observable<WatchPoiResult> getWatchPoi(String id) {
        return Api.getInstance().movieService_watch
                .getWatchPoi(id)
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<WatchPoiResult> addZone(String id, String paparm) {
        return Api.getInstance().movieService_watch
                .AddZone(id,paparm)
                .compose(RxSchedulers.io_main());
    }

}
