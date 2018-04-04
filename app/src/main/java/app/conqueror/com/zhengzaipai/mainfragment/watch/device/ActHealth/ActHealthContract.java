package app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActHealth;

import android.content.Intent;
import android.widget.TextView;

import org.w3c.dom.Text;

import app.conqueror.com.zhengzaipai.base.BaseModel;
import app.conqueror.com.zhengzaipai.base.BasePresenter;
import app.conqueror.com.zhengzaipai.base.BaseView;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.ActHealthResult;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.ActResult;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/20.
 */

public interface ActHealthContract {
    interface Model extends BaseModel {
          Observable<ActHealthResult> getWlakByIdAndTime(String id, String time);
          Observable<ActHealthResult> getSleepByIdAndTime(String id, String time);
    }

    interface View extends BaseView {
        TextView getTvdata();
        void sendAction(Intent intent);
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void getWlakByIdAndTime(String id,String time);
        public abstract void getSleepByIdAndTime(String id,String time);
    }
}
