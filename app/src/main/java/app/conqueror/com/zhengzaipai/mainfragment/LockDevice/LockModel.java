package app.conqueror.com.zhengzaipai.mainfragment.LockDevice;

import app.conqueror.com.zhengzaipai.base.RxSchedulers;
import app.conqueror.com.zhengzaipai.entity.CodeResult;
import app.conqueror.com.zhengzaipai.entity.DateResult;
import app.conqueror.com.zhengzaipai.retrofit.Api;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/18.
 */

public class LockModel implements LockContract.Model {

    @Override
    public Observable<DateResult> getDeviceList(String appid) {
        return Api.getInstance().movieService
                .getDeviceList(appid)
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<DateResult> getCardList(String appid) {
        return Api.getInstance().movieService
                .getCardList(appid)
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<DateResult> LockDevice(String appid, String did, String phone, String name) {
        return Api.getInstance().movieService
                .lockDevice(appid,did,phone,name)
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<DateResult> LockCard(String appid, String iccid, String phone, String alias) {
        return Api.getInstance().movieService
                .lockCard(appid,iccid,phone,alias)
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<CodeResult> getDeviceCode(String phone) {
        return Api.getInstance().movieService_card
                .getDeviceCode(phone)
                .compose(RxSchedulers.io_main());
    }


}
