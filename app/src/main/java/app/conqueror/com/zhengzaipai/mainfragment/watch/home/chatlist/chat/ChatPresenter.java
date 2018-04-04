package app.conqueror.com.zhengzaipai.mainfragment.watch.home.chatlist.chat;

import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.ChatMsg;
import app.conqueror.com.zhengzaipai.util.DateUtils;
import app.conqueror.com.zhengzaipai.util.SpUtil;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2017/7/20.
 */

public class ChatPresenter extends ChatContract.Presenter {
    @Override
    public void onStart() {
        mView.initDialog();

    }

    @Override
    public void vipmessage(String id, String uid, String content) {
        mView.showDialog();
        mRxManage.add(mModel.vipmessage(id,uid,content).subscribe(res -> {
                    mView.hideDialog();
                    String time= DateUtils.getCurrentTime_Today();
                    if (res.success.equals("success")) {

                        mView.addMsgList(new ChatMsg(1,0,SpUtil.getAppUser().nickName,content,true,time),true);

                    } else {
                        mView.addMsgList(new ChatMsg(1,0,SpUtil.getAppUser().nickName,content,false,time),true);
                    }
                }, e -> {
                    mView.showMsg(""+e);
                    mView.hideDialog();
                }

        ));
    }

    @Override
    public void viptk(RequestBody description, MultipartBody.Part file, String id, String uid,String filePath) {
        mView.showDialog();
        String time= DateUtils.getCurrentTime_Today();
        mRxManage.add(mModel.viptk(description,file,id,uid).subscribe(res -> {
                    mView.hideDialog();

                    if (res.success.equals("success")) {

                        mView.addMsgList(new ChatMsg(1,1,SpUtil.getAppUser().nickName,filePath,true,time),true);

                    } else {
                        mView.addMsgList(new ChatMsg(1,1,SpUtil.getAppUser().nickName,filePath,false,time),true);
                    }
                }, e -> {
                    mView.addMsgList(new ChatMsg(1,1,SpUtil.getAppUser().nickName,filePath,false,time),true);
                    mView.showMsg(""+ e);
                    mView.hideDialog();
                }

        ));
    }




/*    @Override
    public void message(String id, String content) {
        mView.showDialog();
        mRxManage.add(mModel.message(id,content).subscribe(res -> {
                    mView.hideDialog();
                    String time= DateUtils.getCurrentTime_Today();
                    if (res.success.equals("success")) {

                        mView.addMsgList(new ChatMsg(1,0,SpUtil.getAppUser().nickName,content,true,time),true);

                    } else {
                        mView.addMsgList(new ChatMsg(1,0,SpUtil.getAppUser().nickName,content,false,time),true);
                    }
                }, e -> {
                    mView.showMsg(""+e);
                    mView.hideDialog();
                }

        ));
    }
*/
    @Override
    public void tk(RequestBody description, MultipartBody.Part file, String id,String filePath) {

        mView.showDialog();
        String time= DateUtils.getCurrentTime_Today();
        mRxManage.add(mModel.tk(description,file,id).subscribe(res -> {
                    mView.hideDialog();

                    if (res.success.equals("success")) {

                        mView.addMsgList(new ChatMsg(1,1,SpUtil.getAppUser().nickName,filePath,true,time),true);

                    } else {
                        mView.addMsgList(new ChatMsg(1,1,SpUtil.getAppUser().nickName,filePath,false,time),true);
                    }
                }, e -> {
            mView.addMsgList(new ChatMsg(1,1,SpUtil.getAppUser().nickName,filePath,false,time),true);
                    mView.showMsg(""+ e);
                    mView.hideDialog();
                }

        ));
    }

   /* @Override
    public void tk(String id, RequestBody file) {
        mView.showDialog();
        mRxManage.add(mModel.tk(id,file).subscribe(res -> {
                    mView.hideDialog();
                    String time= DateUtils.getCurrentTime_Today();
                    if (res.success.equals("success")) {

                        mView.addMsgList(new ChatMsg(1,1,SpUtil.getAppUser().nickName,"",true,time),true);

                    } else {
                        mView.addMsgList(new ChatMsg(1,1,SpUtil.getAppUser().nickName,"",false,time),true);
                    }
                }, e -> {
                    mView.showMsg("错误!" + e);
                    mView.hideDialog();
                }

        ));
    }*/
}