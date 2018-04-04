package app.conqueror.com.zhengzaipai.mainfragment.watch.login;

import app.conqueror.com.zhengzaipai.base.RxSchedulers;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.ActResult;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.AppResult;
import app.conqueror.com.zhengzaipai.mainfragment.watch.home.chatlist.chat.ChatContract;
import app.conqueror.com.zhengzaipai.retrofit.Api;
import app.conqueror.com.zhengzaipai.util.SpUtil;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/20.
 */

public class LoginModel implements LoginContract.Model {

    @Override
    public Observable<AppResult> login(String phone, String pasword) {
        return Api.getInstance().movieService_watch
                .login(phone,pasword)
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<ActResult> saveToken(String phone, String token, String dtype) {
        return Api.getInstance().movieService_watch
                .saveToken(phone, token,dtype)
                .compose(RxSchedulers.io_main());
    }
}
