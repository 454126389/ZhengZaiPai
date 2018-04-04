package app.conqueror.com.zhengzaipai.mainfragment.LockDevice;

import java.util.List;

import app.conqueror.com.zhengzaipai.base.BaseModel;
import app.conqueror.com.zhengzaipai.base.BasePresenter;
import app.conqueror.com.zhengzaipai.base.BaseView;
import app.conqueror.com.zhengzaipai.entity.CodeResult;
import app.conqueror.com.zhengzaipai.entity.DateResult;
import app.conqueror.com.zhengzaipai.entity.Device;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/18.
 */

public interface LockContract {
    interface Model extends BaseModel {
        Observable<DateResult> getDeviceList(String appid);
        Observable<DateResult> getCardList(String appid);
        Observable<DateResult> LockDevice(String appid,String did,String phone,String name);
        Observable<DateResult> LockCard(String appid,String did,String phone,String name);
        Observable<CodeResult> getDeviceCode(String phone);
    }

    interface View extends BaseView {
        void initDeviceList(List<Device> devicelist);
        void initLock();
        String getType();


    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void getDeviceList(String appid);
        public abstract void LockDevice(String appid, String did,String phone,String name);
        public abstract void getDeviceCode(String phone);
    }


}
