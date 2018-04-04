package app.conqueror.com.zhengzaipai.mainfragment.watch.device.LoveBons;

import app.conqueror.com.zhengzaipai.base.BaseModel;
import app.conqueror.com.zhengzaipai.base.BasePresenter;
import app.conqueror.com.zhengzaipai.base.BaseView;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.ActResult;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WatchPoiEntity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WatchPoiResult;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/20.
 */

public interface LoveBonsContract {
    interface Model extends BaseModel {
          Observable<ActResult> flower(String id, String content);
    }

    interface View extends BaseView {
        void suc();
        void fail();
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void flower(String id,String content);
    }
}
