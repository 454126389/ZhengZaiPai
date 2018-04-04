package app.conqueror.com.zhengzaipai.mainfragment.watch.device.LoveBons;

import app.conqueror.com.zhengzaipai.base.RxSchedulers;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.ActResult;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WatchPoiResult;
import app.conqueror.com.zhengzaipai.mainfragment.watch.map.zonelist.ZoneListContract;
import app.conqueror.com.zhengzaipai.retrofit.Api;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/20.
 */

public class LoveBonsModel implements LoveBonsContract.Model {


    @Override
    public Observable<ActResult> flower(String id, String content) {
        return Api.getInstance().movieService_watch
                .flower(id,content)
                .compose(RxSchedulers.io_main());
    }
}
