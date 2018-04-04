package app.conqueror.com.zhengzaipai.mainfragment.watch.device.PhoneBook;

import java.util.List;

import app.conqueror.com.zhengzaipai.base.BaseModel;
import app.conqueror.com.zhengzaipai.base.BasePresenter;
import app.conqueror.com.zhengzaipai.base.BaseView;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.ActResult;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/20.
 */

public interface BookDetailContract {
    interface Model extends BaseModel {
          Observable<ActResult> phb(String id, String content);
    }

    interface View extends BaseView {
        void addSuc();
        void suc();
        void fail();
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void phb(String id, List<String> bookList);
    }
}
