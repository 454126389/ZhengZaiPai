package app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActClock.ClockSet;

import app.conqueror.com.zhengzaipai.base.RxSchedulers;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.ActResult;
import app.conqueror.com.zhengzaipai.retrofit.Api;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/20.
 */

public class ClockSetModel implements ClockSetContract.Model {


    @Override
    public Observable<ActResult> remind(String id, String content) {
        return Api.getInstance().movieService_watch
                .remind(id,content)
                .compose(RxSchedulers.io_main());
    }
}