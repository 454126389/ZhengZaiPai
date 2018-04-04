package app.conqueror.com.zhengzaipai.mainfragment.LockDevice.mirror.MonitorActivity;

import app.conqueror.com.zhengzaipai.base.RxSchedulers;
import app.conqueror.com.zhengzaipai.entity.XgResult;
import app.conqueror.com.zhengzaipai.retrofit.Api;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/18.
 */

public class MonitorModel implements MonitorContract.Model {


    @Override
    public Observable<XgResult> takePhoto(String apptoken, String did) {
        return Api.getInstance().movieService
                .takePhoto(apptoken,did)
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<XgResult> takeVideo(String apptoken, String did) {
        return Api.getInstance().movieService
                .takeVideo(apptoken,did)
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<XgResult> speekWords(String apptoken, String did, String words) {
        return Api.getInstance().movieService
                .speekWords(apptoken,did,words)
                .compose(RxSchedulers.io_main());
    }
}
