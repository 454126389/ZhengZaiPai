package app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActDnd;

import app.conqueror.com.zhengzaipai.base.RxSchedulers;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.LoveBons.LoveBonsContract;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.ActResult;
import app.conqueror.com.zhengzaipai.retrofit.Api;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/20.
 */

public class ActDndModel implements ActDndContract.Model {


    @Override
    public Observable<ActResult> silenceTime(String id, String content) {
        return Api.getInstance().movieService_watch
                .silenceTime(id,content)
                .compose(RxSchedulers.io_main());
    }
}
