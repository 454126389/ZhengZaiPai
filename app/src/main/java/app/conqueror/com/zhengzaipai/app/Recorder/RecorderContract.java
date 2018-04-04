package app.conqueror.com.zhengzaipai.app.Recorder;

import java.util.List;

import app.conqueror.com.zhengzaipai.base.BaseModel;
import app.conqueror.com.zhengzaipai.base.BasePresenter;
import app.conqueror.com.zhengzaipai.base.BaseView;
import app.conqueror.com.zhengzaipai.entity.DateResult;
import app.conqueror.com.zhengzaipai.entity.Device;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/18.
 */

public interface RecorderContract {
    interface Model extends BaseModel {
        Observable<DateResult> getDeviceList(String appid);
        Observable<DateResult> getCardList(String appid);
        Observable<DateResult> LockDevice(String appid, String did, String phone, String name);
        Observable<DateResult> LockCard(String appid, String did, String phone, String name);
    }

    interface View extends BaseView {
        void initDeviceList(List<Device> devicelist);
        void initLock();
        String getType();

//        void initDialog();

    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void getDeviceList(String appid);
        public abstract void LockDevice(String appid, String did,String phone,String name);
    }


}
