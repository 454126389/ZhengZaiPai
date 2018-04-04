package app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActFriend;

import java.util.List;

import app.conqueror.com.zhengzaipai.base.BaseModel;
import app.conqueror.com.zhengzaipai.base.BasePresenter;
import app.conqueror.com.zhengzaipai.base.BaseView;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.ActResult;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/20.
 */

public interface ActFriendContract {
    interface Model extends BaseModel {
          Observable<ActResult> ppr(String id, String fid);
    }

    interface View extends BaseView {
//        void addSuc();
void suc();
        void fail();
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void ppr(String id, String fid);
    }
}
