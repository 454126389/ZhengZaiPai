package app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActHealth;

import app.conqueror.com.zhengzaipai.base.RxSchedulers;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.LoveBons.LoveBonsContract;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.ActHealthResult;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.ActResult;
import app.conqueror.com.zhengzaipai.retrofit.Api;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/20.
 */

public class ActHealthModel implements ActHealthContract.Model {
    @Override
    public Observable<ActHealthResult> getWlakByIdAndTime(String id, String time) {
        return Api.getInstance().movieService_watch
                .getWlakByIdAndTime(id, time)
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<ActHealthResult> getSleepByIdAndTime(String id, String time) {
        return Api.getInstance().movieService_watch
                .getSleepByIdAndTime(id, time)
                .compose(RxSchedulers.io_main());
    }

//
//    @Override
//    public Observable<ActResult> silenceTime(String id, String content) {
//        return Api.getInstance().movieService_watch
//                .silenceTime(id,content)
//                .compose(RxSchedulers.io_main());
//    }
}
