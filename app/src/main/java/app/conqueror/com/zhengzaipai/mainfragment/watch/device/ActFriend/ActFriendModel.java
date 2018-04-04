package app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActFriend;

import app.conqueror.com.zhengzaipai.base.RxSchedulers;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.PhoneBook.BookDetailContract;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.ActResult;
import app.conqueror.com.zhengzaipai.retrofit.Api;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/20.
 */

public class ActFriendModel implements ActFriendContract.Model {





    @Override
    public Observable<ActResult> ppr(String id, String fid) {
        return Api.getInstance().movieService_watch
                .phb(id,fid)
                .compose(RxSchedulers.io_main());
    }
}
