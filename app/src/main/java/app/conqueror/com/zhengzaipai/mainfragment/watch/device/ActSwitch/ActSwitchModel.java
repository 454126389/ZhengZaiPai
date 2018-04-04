package app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActSwitch;

import app.conqueror.com.zhengzaipai.base.RxSchedulers;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.ActResult;
import app.conqueror.com.zhengzaipai.mainfragment.watch.home.chatlist.chat.ChatContract;
import app.conqueror.com.zhengzaipai.retrofit.Api;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/20.
 */

public class ActSwitchModel implements ActSwitchContract.Model {



    @Override
    public Observable<ActResult> remove(String id, String content) {
        return Api.getInstance().movieService_watch
                .remove(id,content)
                .compose(RxSchedulers.io_main());
    }
}
