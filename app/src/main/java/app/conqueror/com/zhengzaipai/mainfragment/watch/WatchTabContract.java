package app.conqueror.com.zhengzaipai.mainfragment.watch;

import android.content.Intent;

import app.conqueror.com.zhengzaipai.base.BaseModel;
import app.conqueror.com.zhengzaipai.base.BasePresenter;
import app.conqueror.com.zhengzaipai.base.BaseView;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.ActResult;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.AppResult;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.GoogleResult;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WatchResult;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WatchPoiResult;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/20.
 */

public interface WatchTabContract {
    interface Model extends BaseModel {
        Observable<WatchPoiResult> getWatchPoi(String id);
        Observable<WatchResult> findDevicesByUserPhone(String phone);
//        Observable<AppResult> findUserByPhone(String phone);
        Observable<ActResult> poweroff(String id);
        Observable<ActResult> sos(String id,String content);
        Observable<ActResult> upload(String id,String content);
        Observable<ActResult> tw(String lat,String lon);
        Observable<ActResult> unbunding(String phone,String id);
        Observable<GoogleResult> geocode(String latlng, String key);
        Observable<AppResult> login(String phone, String pasword);
        Observable<AppResult> cr(String id);



    }

    interface View extends BaseView {
        void initTab();
        void tip_suc();
        void unbind_suc();
        void tip_fail();
        void need_one();
        void tip_error();
        void set_placse(String address);
        void sendAction(Intent intent);
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void getWatchPoi(String id);
        public abstract void findDevicesByUserPhone(String phone);
//        public abstract void findUserByPhone(String phone);
        public abstract void poweroff(String id);
        public abstract void sos(String id,String content);
        public abstract void upload(String id,String content);
        public abstract void tw(String lat,String lon);
        public abstract void unbunding(String phone,String id);
        public abstract void geocode(String latlng,String key);
        public abstract void login(String phone, String pasword);
        public abstract void cr(String id);
    }
}
