package app.conqueror.com.zhengzaipai.mainfragment.watch.device.AddDevice.addlist.binddevice;

import app.conqueror.com.zhengzaipai.base.RxSchedulers;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.PhoneBook.BookAdd.BookAddContract;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.ActResult;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.AppResult;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.Result;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WatchResult2;
import app.conqueror.com.zhengzaipai.retrofit.Api;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/20.
 */

public class BindDeviceModel implements BindDeviceContract.Model {
    @Override
    public Observable<WatchResult2> bunding(String phone, String code, String nickName, String name) {
        return Api.getInstance().movieService_watch
                .bunding(phone,code,nickName,name)
                .compose(RxSchedulers.io_main());
    }


    @Override
    public Observable<AppResult> login(String phone, String pasword) {
        return Api.getInstance().movieService_watch
                .login(phone,pasword)
                .compose(RxSchedulers.io_main());
    }


//    @Override
//    public Observable<Result> bunding(String phone, String code, String nickName, String name) {
//        return Api.getInstance().movieService_watch
//                .bunding(phone,code,nickName,name)
//                .compose(RxSchedulers.io_main());
//    }
}
