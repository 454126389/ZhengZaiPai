
package app.conqueror.com.zhengzaipai.mainfragment.watch.login;

import app.conqueror.com.zhengzaipai.base.BaseModel;
import app.conqueror.com.zhengzaipai.base.BasePresenter;
import app.conqueror.com.zhengzaipai.base.BaseView;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.ActResult;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.AppResult;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.ChatMsg;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/20.
 */

public interface LoginContract {
    interface Model extends BaseModel {
          Observable<AppResult> login(String phone, String pasword);
          Observable<ActResult> saveToken(String phone, String token, String dtype);
    }




    interface View extends BaseView {
//        from  0 left，1 right
//        type 0  msg,1  sound
        void goMain();
        void suc();
        void fail();
        void inputError();
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void login(String phone, String pasword);
        public abstract void saveToken(String phone, String token, String dtype);
    }
}
