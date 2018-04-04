package app.conqueror.com.zhengzaipai.app.Function;

import app.conqueror.com.zhengzaipai.base.BaseModel;
import app.conqueror.com.zhengzaipai.base.BasePresenter;
import app.conqueror.com.zhengzaipai.base.BaseView;
import app.conqueror.com.zhengzaipai.entity.DateResult;
import app.conqueror.com.zhengzaipai.entity.XgResult;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/20.
 */

public interface FunctionContract {
    interface Model extends BaseModel {
        Observable<XgResult> shutdown(String apptoken, String did);
    }

    interface View extends BaseView {
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void shutdown(String apptoken,String did);
    }
}
