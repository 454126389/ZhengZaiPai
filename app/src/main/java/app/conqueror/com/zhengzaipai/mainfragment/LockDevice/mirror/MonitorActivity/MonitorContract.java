package app.conqueror.com.zhengzaipai.mainfragment.LockDevice.mirror.MonitorActivity;

import app.conqueror.com.zhengzaipai.base.BaseModel;
import app.conqueror.com.zhengzaipai.base.BasePresenter;
import app.conqueror.com.zhengzaipai.base.BaseView;
import app.conqueror.com.zhengzaipai.entity.XgResult;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/18.
 */

public interface MonitorContract {
    interface Model extends BaseModel {
        Observable<XgResult> takePhoto(String apptoken, String did);
        Observable<XgResult> takeVideo(String apptoken,String did);
        Observable<XgResult> speekWords(String apptoken,String did,String words);
    }

    interface View extends BaseView {


    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void takePhoto(String apptoken,String did);
        public abstract void takeVideo(String apptoken,String did);
        public abstract void speekWords(String apptoken,String did,String words);
    }


}
