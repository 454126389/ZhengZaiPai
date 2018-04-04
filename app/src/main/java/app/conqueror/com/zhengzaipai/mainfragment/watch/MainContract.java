package app.conqueror.com.zhengzaipai.mainfragment.watch;



import app.conqueror.com.zhengzaipai.mainfragment.watch.base.BasePresenter;
import app.conqueror.com.zhengzaipai.mainfragment.watch.base.BaseView;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WatchPoiEntity;

/**
 * Created by Administrator on 2016/7/18 0018.
 */
public interface MainContract {



    interface Presenter extends BasePresenter {

        void getWatchPoi(String id);
    }


    interface View extends BaseView<Presenter> {

        void setWatchPoi(WatchPoiEntity poi);

    }



}
