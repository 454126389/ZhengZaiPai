package app.conqueror.com.zhengzaipai.app.Function;

import app.conqueror.com.zhengzaipai.app.Function.FunctionContract;
import app.conqueror.com.zhengzaipai.base.RxSchedulers;
import app.conqueror.com.zhengzaipai.entity.XgResult;
import app.conqueror.com.zhengzaipai.retrofit.Api;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/20.
 */

public class FunctionModel implements FunctionContract.Model {
    @Override
    public Observable<XgResult> shutdown(String apptoken, String did) {
        return Api.getInstance().movieService_watch
                .closeSystem(apptoken,did)
                .compose(RxSchedulers.io_main());
    }
}
