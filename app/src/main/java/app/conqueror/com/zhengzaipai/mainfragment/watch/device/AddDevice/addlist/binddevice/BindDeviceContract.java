package app.conqueror.com.zhengzaipai.mainfragment.watch.device.AddDevice.addlist.binddevice;

import java.util.List;

import app.conqueror.com.zhengzaipai.base.BaseModel;
import app.conqueror.com.zhengzaipai.base.BasePresenter;
import app.conqueror.com.zhengzaipai.base.BaseView;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.ActResult;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.AppResult;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.Result;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WatchResult2;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/20.
 */

public interface BindDeviceContract {
    interface Model extends BaseModel {
          Observable<WatchResult2> bunding(String phone, String code, String nickName, String name);
        Observable<AppResult> login(String phone, String pasword);
    }

    interface View extends BaseView {
        void back();
        void suc();
        void fail();
        void wait_auth();
        void wait_did();
        void nofind();
        void goMain();

    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void bunding(String phone, String code, String nickName, String name);
        public abstract void login(String phone, String pasword);
    }
}
