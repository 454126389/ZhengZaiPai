package app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActHealth.SetStep;

import app.conqueror.com.zhengzaipai.base.RxSchedulers;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActHealth.SetSleep.SetSleepContract;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.ActResult;
import app.conqueror.com.zhengzaipai.retrofit.Api;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/20.
 */

public class SetStepModel implements SetStepContract.Model {

    @Override
    public Observable<ActResult> pedo(String id, String content) {
        return Api.getInstance().movieService_watch
                .pedo(id,content)
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<ActResult> setp(String id, String content) {
        return Api.getInstance().movieService_watch
                .setp(id,content)
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<ActResult> weight(String id, String content) {
        return Api.getInstance().movieService_watch
                .weight(id,content)
                .compose(RxSchedulers.io_main());
    }



    @Override
    public Observable<ActResult> walktime(String id, String content) {
        return Api.getInstance().movieService_watch
                .walktime(id,content)
                .compose(RxSchedulers.io_main());
    }
}
