package app.conqueror.com.zhengzaipai.mainfragment.watch.device.AddDevice;

import app.conqueror.com.zhengzaipai.base.BaseModel;
import app.conqueror.com.zhengzaipai.base.BasePresenter;
import app.conqueror.com.zhengzaipai.base.BaseView;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.ActResult;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.AuthResult;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WatchResult;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WatchResult2;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/20.
 */

public interface AddDeviceContract {
    interface Model extends BaseModel {

          Observable<WatchResult2> auth(String phone, String id, String nickName, String name);
          Observable<ActResult> getOfflineMes(String phone);

    }

    interface View extends BaseView {
            void initList(String mes);
            void addSuc(int poi);
            void suc();
            void fail();
            void authed();
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void auth(int poi,String phone,String id,String nickName,String name);
        public abstract void getOfflineMes(String phone);
    }
}
