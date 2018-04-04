package app.conqueror.com.zhengzaipai.app.CardService;

import app.conqueror.com.zhengzaipai.base.RxSchedulers;
import app.conqueror.com.zhengzaipai.entity.DateResult;
import app.conqueror.com.zhengzaipai.entity.ServiceResult;
import app.conqueror.com.zhengzaipai.retrofit.Api;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/18.
 */

public class CardSrerviceModel implements CardServiceContract.Model {


    @Override
    public Observable<ServiceResult> getServiceList(String iccid) {
        return Api.getInstance().movieService_sim
                .getServiceList(iccid)
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<ServiceResult> buyService(String iccid) {
        return null;
    }
}
