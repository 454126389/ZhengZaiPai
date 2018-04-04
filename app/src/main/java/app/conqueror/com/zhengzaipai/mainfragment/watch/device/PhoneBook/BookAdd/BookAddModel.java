package app.conqueror.com.zhengzaipai.mainfragment.watch.device.PhoneBook.BookAdd;

import app.conqueror.com.zhengzaipai.base.RxSchedulers;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.ActResult;
import app.conqueror.com.zhengzaipai.retrofit.Api;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/20.
 */

public class BookAddModel implements BookAddContract.Model {


    @Override
    public Observable<ActResult> phb(String id, String content) {
        return Api.getInstance().movieService_watch
                .phb(id,content)
                .compose(RxSchedulers.io_main());
    }
}
