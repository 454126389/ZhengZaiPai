package app.conqueror.com.zhengzaipai.mainfragment.watch;

import app.conqueror.com.zhengzaipai.base.RxSchedulers;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.ActResult;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.AppResult;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.GoogleResult;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WatchResult;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WatchPoiResult;
import app.conqueror.com.zhengzaipai.retrofit.Api;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/20.
 */

public class WatchTabModel implements WatchTabContract.Model {
    @Override
    public Observable<WatchPoiResult> getWatchPoi(String id) {
        return Api.getInstance().movieService_watch
                .getWatchPoi(id)
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<WatchResult> findDevicesByUserPhone(String phone) {
        return Api.getInstance().movieService_watch
                .findDevicesByUserPhone(phone)
                .compose(RxSchedulers.io_main());
    }

//    @Override
//    public Observable<AppResult> findUserByPhone(String phone) {
//        return Api.getInstance().movieService_watch
//                .findUserByPhone(phone)
//                .compose(RxSchedulers.io_main());
//    }

    @Override
    public Observable<ActResult> poweroff(String id) {
        return Api.getInstance().movieService_watch
                .poweroff(id)
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<ActResult> sos(String id,String content) {
        return Api.getInstance().movieService_watch
                .sos(id,content)
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<ActResult> upload(String id, String content) {
        return Api.getInstance().movieService_watch
                .upload(id,content)
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<ActResult> tw(String lat, String lon) {
        return Api.getInstance().movieService_watch
                .tw(lat,lon)
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<ActResult> unbunding(String phone, String id) {
        return Api.getInstance().movieService_watch
                .unbunding(phone,id)
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<GoogleResult> geocode(String latlng, String key) {
        return Api.getInstance().movieService_google
                .geocode(latlng,key)
                .compose(RxSchedulers.io_main());
    }


    @Override
    public Observable<AppResult> login(String phone, String pasword) {
        return Api.getInstance().movieService_watch
                .login(phone,pasword)
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<AppResult> cr(String id) {
        return Api.getInstance().movieService_watch
                .cr(id)
                .compose(RxSchedulers.io_main());
    }
}
