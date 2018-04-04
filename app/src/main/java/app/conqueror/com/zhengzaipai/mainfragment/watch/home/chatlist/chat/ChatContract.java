
package app.conqueror.com.zhengzaipai.mainfragment.watch.home.chatlist.chat;

import java.io.File;

import app.conqueror.com.zhengzaipai.base.BaseModel;
import app.conqueror.com.zhengzaipai.base.BasePresenter;
import app.conqueror.com.zhengzaipai.base.BaseView;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.ActResult;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.ChatMsg;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Part;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/20.
 */

public interface ChatContract {
    interface Model extends BaseModel {
//          Observable<ActResult> message(String id,String content);
          Observable<ActResult> vipmessage(String id,String uid,String content);
          Observable<ActResult> tk(RequestBody description, MultipartBody.Part file, String id);
          Observable<ActResult> viptk(RequestBody description, MultipartBody.Part file, String id,String uid);
    }




    interface View extends BaseView {
//        from  0 left，1 right
//        type 0  msg,1  sound
        void addMsgList(ChatMsg chatMsg,Boolean isAdd);
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void vipmessage(String id,String uid,String content);
        public abstract void viptk(RequestBody description, MultipartBody.Part file, String id,String uid,String filePath);
//        public abstract void message(String id,String content);
        public abstract void tk(RequestBody description, MultipartBody.Part file, String id,String filePath);
    }
}
