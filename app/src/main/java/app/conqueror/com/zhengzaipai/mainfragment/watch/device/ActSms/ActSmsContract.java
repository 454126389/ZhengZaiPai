
package app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActSms;

import app.conqueror.com.zhengzaipai.base.BaseModel;
import app.conqueror.com.zhengzaipai.base.BasePresenter;
import app.conqueror.com.zhengzaipai.base.BaseView;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.ActResult;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.ChatMsg;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/20.
 */

public interface ActSmsContract {
    interface Model extends BaseModel {
          Observable<ActResult> center(String id, String content);
          Observable<ActResult> remove(String id, String content);
          Observable<ActResult> sossms(String id, String content);
          Observable<ActResult> lowbat(String id, String content);
    }




    interface View extends BaseView {
            void suc();
            void fail();
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void center(String id,String content);
        public abstract void remove(String id,String content);
        public abstract void sossms(String id,String content);
        public abstract void lowbat(String id,String content);
    }
}
