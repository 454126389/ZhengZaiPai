package app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActSms;

import app.conqueror.com.zhengzaipai.base.RxSchedulers;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.ActResult;
import app.conqueror.com.zhengzaipai.mainfragment.watch.home.chatlist.chat.ChatContract;
import app.conqueror.com.zhengzaipai.retrofit.Api;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/20.
 */

public class ActSmsModel implements ActSmsContract.Model {


//    @Override
//    public Observable<ActResult> message(String id, String content) {
//        return Api.getInstance().movieService_watch
//                .message(id,content)
//                .compose(RxSchedulers.io_main());
//    }


    @Override
    public Observable<ActResult> center(String id, String content) {
        return Api.getInstance().movieService_watch
                .center(id,content)
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<ActResult> remove(String id, String content) {
        return Api.getInstance().movieService_watch
                .remove(id,content)
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<ActResult> sossms(String id, String content) {
        return Api.getInstance().movieService_watch
                .sossms(id,content)
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<ActResult> lowbat(String id, String content) {
        return Api.getInstance().movieService_watch
                .lowbat(id,content)
                .compose(RxSchedulers.io_main());
    }
}
