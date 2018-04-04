
package app.conqueror.com.zhengzaipai.mainfragment.watch.login.signup;

import app.conqueror.com.zhengzaipai.base.BaseModel;
import app.conqueror.com.zhengzaipai.base.BasePresenter;
import app.conqueror.com.zhengzaipai.base.BaseView;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.ActResult;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.AppResult;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/20.
 */

public interface SignUpContract {
    interface Model extends BaseModel {
          Observable<ActResult> sendVcode(String phone);
          Observable<ActResult> register(String phone,String password,String vcode);
    }




    interface View extends BaseView {
//        from  0 left，1 right
//        type 0  msg,1  sound
        void goMain();
        void suc();
        void fail();
        void vcode_error();
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void sendVcode(String phone);
        public abstract void register(String phone,String password,String vcode);
    }
}
