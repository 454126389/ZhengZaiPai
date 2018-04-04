package app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActHealth.SetStep;

import app.conqueror.com.zhengzaipai.base.BaseModel;
import app.conqueror.com.zhengzaipai.base.BasePresenter;
import app.conqueror.com.zhengzaipai.base.BaseView;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.ActResult;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/20.
 */

public interface SetStepContract {
    interface Model extends BaseModel {
        Observable<ActResult> pedo(String id, String content);
        Observable<ActResult> setp(String id, String content);
        Observable<ActResult> weight(String id, String content);
        Observable<ActResult> walktime(String id, String content);
    }

    interface View extends BaseView {
        void suc();
        void fail();
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void pedo(String id,String content);
        public abstract void setp(String id,String content);
        public abstract void weight(String id,String content);
        public abstract void walktime(String id,String content);

    }
}
