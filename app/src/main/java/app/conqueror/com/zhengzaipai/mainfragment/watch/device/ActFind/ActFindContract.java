
package app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActFind;

import app.conqueror.com.zhengzaipai.base.BaseModel;
import app.conqueror.com.zhengzaipai.base.BasePresenter;
import app.conqueror.com.zhengzaipai.base.BaseView;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.ActResult;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.ChatMsg;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/20.
 */

public interface ActFindContract {
    interface Model extends BaseModel {
          Observable<ActResult> find(String id);
    }




    interface View extends BaseView {
//        from  0 left，1 right
//        type 0  msg,1  sound
       void suc();
        void fail();

    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void find(String id);
    }
}
