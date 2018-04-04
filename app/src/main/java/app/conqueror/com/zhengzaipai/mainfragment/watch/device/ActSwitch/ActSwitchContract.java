
package app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActSwitch;

import app.conqueror.com.zhengzaipai.base.BaseModel;
import app.conqueror.com.zhengzaipai.base.BasePresenter;
import app.conqueror.com.zhengzaipai.base.BaseView;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.ActResult;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.ChatMsg;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/20.
 */

public interface ActSwitchContract {
    interface Model extends BaseModel {
          Observable<ActResult> remove(String id, String content);
    }




    interface View extends BaseView {
        void suc();
        void fail();
//        from  0 left，1 right
//        type 0  msg,1  sound
        void addMsgList(ChatMsg chatMsg, Boolean isAdd);
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void remove(String id,String content);
    }
}
