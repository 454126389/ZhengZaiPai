package app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActHealth.SetSleep;

import app.conqueror.com.zhengzaipai.base.RxSchedulers;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActHealth.ActHealthContract;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.ActResult;
import app.conqueror.com.zhengzaipai.retrofit.Api;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/20.
 */

public class SetSleepModel implements SetSleepContract.Model {

    @Override
    public Observable<ActResult> sleepTime(String id, String content) {
        return Api.getInstance().movieService_watch
                .sleepTime(id,content)
                .compose(RxSchedulers.io_main());
    }
}
