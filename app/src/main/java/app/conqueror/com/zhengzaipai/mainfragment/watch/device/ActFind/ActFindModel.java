package app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActFind;

import app.conqueror.com.zhengzaipai.base.RxSchedulers;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.ActResult;
import app.conqueror.com.zhengzaipai.mainfragment.watch.home.chatlist.chat.ChatContract;
import app.conqueror.com.zhengzaipai.retrofit.Api;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/20.
 */

public class ActFindModel implements ActFindContract.Model {



    @Override
    public Observable<ActResult> find(String id) {
        return Api.getInstance().movieService_watch
                .find(id)
                .compose(RxSchedulers.io_main());
    }
}
