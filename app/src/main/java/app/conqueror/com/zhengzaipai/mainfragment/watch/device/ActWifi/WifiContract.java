package app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActWifi;

import app.conqueror.com.zhengzaipai.base.BaseModel;
import app.conqueror.com.zhengzaipai.base.BasePresenter;
import app.conqueror.com.zhengzaipai.base.BaseView;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.ActResult;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/20.
 */

public interface WifiContract {
    interface Model extends BaseModel {
          Observable<ActResult> wifisearch(String id);
          Observable<ActResult> wifiset(String id,String content);
    }

    interface View extends BaseView {
        void suc();
        void fail();
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void wifisearch(String id);
        public abstract void wifiset(String id,String content);
    }
}
