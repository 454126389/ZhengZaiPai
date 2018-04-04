package app.conqueror.com.zhengzaipai.mainfragment.watch.device.AddDevice;

import app.conqueror.com.zhengzaipai.base.RxSchedulers;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActWifi.WifiContract;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.ActResult;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.AuthResult;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WatchResult;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WatchResult2;
import app.conqueror.com.zhengzaipai.retrofit.Api;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/20.
 */

public class AddDeviceModel implements AddDeviceContract.Model {




    @Override
    public Observable<WatchResult2> auth(String phone, String id, String nickName, String name) {
        return Api.getInstance().movieService_watch
                .auth(phone,id,nickName,name)
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<ActResult> getOfflineMes(String phone) {
        return Api.getInstance().movieService_watch
                .getOfflineMes(phone)
                .compose(RxSchedulers.io_main());
    }
}
