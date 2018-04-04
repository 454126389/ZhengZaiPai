package app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActRemoteCamera;

import app.conqueror.com.zhengzaipai.base.RxSchedulers;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.LoveBons.LoveBonsContract;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.ActResult;
import app.conqueror.com.zhengzaipai.retrofit.Api;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/20.
 */

public class ActCameraModel implements ActCameraContract.Model {



    @Override
    public Observable<ActResult> rcapture(String id) {
        return Api.getInstance().movieService_watch
                .rcapture(id)
                .compose(RxSchedulers.io_main());
    }
}
