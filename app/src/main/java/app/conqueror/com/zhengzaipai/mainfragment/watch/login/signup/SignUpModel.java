package app.conqueror.com.zhengzaipai.mainfragment.watch.login.signup;

import app.conqueror.com.zhengzaipai.base.RxSchedulers;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.ActResult;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.AppResult;
import app.conqueror.com.zhengzaipai.mainfragment.watch.login.LoginContract;
import app.conqueror.com.zhengzaipai.retrofit.Api;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/20.
 */

public class SignUpModel implements SignUpContract.Model {



    @Override
    public Observable<ActResult> sendVcode(String phone) {
        return Api.getInstance().movieService_watch
                .sendVcode(phone)
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<ActResult> register(String phone, String password, String vcode) {
        return Api.getInstance().movieService_watch
                .register(phone,password,vcode)
                .compose(RxSchedulers.io_main());
    }
}
