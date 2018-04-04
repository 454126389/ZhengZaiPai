package app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActCallBack;

import app.conqueror.com.zhengzaipai.base.RxSchedulers;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.ActResult;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.GpsMsg;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WatchPoiResult;
import app.conqueror.com.zhengzaipai.mainfragment.watch.map.trail.TrailContract;
import app.conqueror.com.zhengzaipai.retrofit.Api;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/20.
 */

public class CallBackModel implements CallBackContract.Model {

    @Override
    public Observable<ActResult> monitor(String id,String content) {
        return Api.getInstance().movieService_watch
                .monitor(id,content)
                .compose(RxSchedulers.io_main());
    }
}
