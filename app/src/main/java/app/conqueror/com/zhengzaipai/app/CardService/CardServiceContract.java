package app.conqueror.com.zhengzaipai.app.CardService;

import java.util.List;

import app.conqueror.com.zhengzaipai.base.BaseModel;
import app.conqueror.com.zhengzaipai.base.BasePresenter;
import app.conqueror.com.zhengzaipai.base.BaseView;
import app.conqueror.com.zhengzaipai.entity.Device;
import app.conqueror.com.zhengzaipai.entity.Plan;
import app.conqueror.com.zhengzaipai.entity.ServiceResult;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/18.
 */

public interface CardServiceContract {
    interface Model extends BaseModel {
        Observable<ServiceResult> getServiceList(String iccid);
        Observable<ServiceResult> buyService(String iccid);
    }

    interface View extends BaseView {
        void initServiceList(List<Plan> baseset,List<Plan> changeset,List<Plan> content_flowall);

    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void getServiceList(String iccid);
        public abstract void buyService(String iccid);
    }


}
