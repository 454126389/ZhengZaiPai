package app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActFind;

import app.conqueror.com.zhengzaipai.util.DateUtils;

/**
 * Created by Administrator on 2017/7/20.
 */

public class ActFindPresenter extends ActFindContract.Presenter {
    @Override
    public void onStart() {
        mView.initDialog();

    }




    @Override
    public void find(String id) {
        mView.showDialog();
        mRxManage.add(mModel.find(id).subscribe(res -> {
                    mView.hideDialog();
                    String time= DateUtils.getCurrentTime_Today();
                    if (res.success.equals("success"))
                    {
                        mView.suc();
//                        mView.showMsg("请求已经发出");
//                        mView.addMsgList(new ChatMsg(1,1,content,true,time),true);

                    } else {
                        mView.fail();
//                        mView.addMsgList(new ChatMsg(1,1,content,false,time),true);
                    }
                }, e -> {
                    mView.fail();
//                    mView.showMsg("错误!" + e);
                    mView.hideDialog();
                }

        ));
    }
}