package app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActWifi;

import app.conqueror.com.zhengzaipai.base.RxSchedulers;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.AddDevice.addlist.binddevice.BindDeviceContract;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.ActResult;
import app.conqueror.com.zhengzaipai.retrofit.Api;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/20.
 */

public class WifiModel implements WifiContract.Model {



    @Override
    public Observable<ActResult> wifisearch(String id) {
        return Api.getInstance().movieService_watch
                .wifisearch(id)
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<ActResult> wifiset(String id, String content) {
        return Api.getInstance().movieService_watch
                .wifiset(id,content)
                .compose(RxSchedulers.io_main());
    }
}
