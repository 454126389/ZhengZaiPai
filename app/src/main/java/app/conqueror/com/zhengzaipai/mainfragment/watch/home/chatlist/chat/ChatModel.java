package app.conqueror.com.zhengzaipai.mainfragment.watch.home.chatlist.chat;

import java.io.File;

import app.conqueror.com.zhengzaipai.base.RxSchedulers;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.ActResult;
import app.conqueror.com.zhengzaipai.retrofit.Api;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/20.
 */

public class ChatModel implements ChatContract.Model {


/*    @Override
    public Observable<ActResult> message(String id, String content) {
        return Api.getInstance().movieService_watch
                .message(id,content)
                .compose(RxSchedulers.io_main());
    }
*/
    @Override
    public Observable<ActResult> tk(RequestBody description, MultipartBody.Part file, String id) {
        return Api.getInstance().movieService_watch
                .tk(description,file,id)
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<ActResult> vipmessage(String id, String uid, String content) {
        return Api.getInstance().movieService_watch
                .vipmessage(id,uid,content)
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<ActResult> viptk(RequestBody description, MultipartBody.Part file, String id, String uid) {
        return Api.getInstance().movieService_watch
                .viptk(description,file,id)
                .compose(RxSchedulers.io_main());
    }

}
