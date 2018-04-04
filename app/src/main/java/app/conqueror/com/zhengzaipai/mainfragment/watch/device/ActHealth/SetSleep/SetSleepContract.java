package app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActHealth.SetSleep;

import android.widget.TextView;

import app.conqueror.com.zhengzaipai.base.BaseModel;
import app.conqueror.com.zhengzaipai.base.BasePresenter;
import app.conqueror.com.zhengzaipai.base.BaseView;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.ActResult;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/20.
 */

public interface SetSleepContract {
    interface Model extends BaseModel {
        Observable<ActResult> sleepTime(String id, String content);
    }

    interface View extends BaseView {
        void suc();
        void fail();
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void sleepTime(String id,String content);
    }
}