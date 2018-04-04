package app.conqueror.com.zhengzaipai.mainfragment.watch.map.trail;

import java.util.List;

import app.conqueror.com.zhengzaipai.base.BaseModel;
import app.conqueror.com.zhengzaipai.base.BasePresenter;
import app.conqueror.com.zhengzaipai.base.BaseView;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.Gps;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.GpsMsg;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WatchPoiEntity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WatchPoiResult;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/20.
 */

public interface TrailContract {
    interface Model extends BaseModel {
          Observable<WatchPoiResult> getWatchPoi(String id);
          Observable<GpsMsg> getTrail(String id, String stime, String etime);
    }

    interface View extends BaseView {

        void setWatchPoi(WatchPoiEntity poi);
        void move(List<Gps> gpsList,Boolean isplay);

        void suc();
        void fail();
        void trailnull();
    }

    abstract class Presenter extends BasePresenter<Model, View> {
          public abstract void getWatchPoi(String id);
          public abstract void getTrail(String id,String stime,String etime,Boolean isplay);
//        Observable<WatchPoiResult> getTrail(String id,String stime,String etime);
    }
}
