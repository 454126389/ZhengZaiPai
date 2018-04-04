package app.conqueror.com.zhengzaipai.mainfragment.watch.map.zonelist.addzone;

import java.util.List;

import app.conqueror.com.zhengzaipai.base.BaseModel;
import app.conqueror.com.zhengzaipai.base.BasePresenter;
import app.conqueror.com.zhengzaipai.base.BaseView;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WatchPoiEntity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WatchPoiResult;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/20.
 */

public interface AddZoneContract {
    interface Model extends BaseModel {
          Observable<WatchPoiResult> getWatchPoi(String id);
          Observable<WatchPoiResult> addZone(String id, String paparm);
    }

    interface View extends BaseView {

        void setWatchPoi(WatchPoiEntity poi);
        void addZoneSuc();
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void getWatchPoi(String id);
        public abstract void addZone(String id,List<String> zoneList);
    }
}
