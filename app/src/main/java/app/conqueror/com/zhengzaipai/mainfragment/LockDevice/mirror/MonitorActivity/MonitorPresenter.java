package app.conqueror.com.zhengzaipai.mainfragment.LockDevice.mirror.MonitorActivity;

/**
 * Created by Administrator on 2017/7/18.
 */

public class MonitorPresenter extends MonitorContract.Presenter {


    @Override
    public void onStart() {
        mView.initDialog();
    }

    @Override
    public void takePhoto(String apptoken, String did) {
        mView.showDialog();
        mRxManage.add(mModel.takePhoto(apptoken,did).subscribe(res -> {
                    mView.hideDialog();
                    if(res.ret_code==0)
                        mView.showMsg("请求已经发出"+res.ret_code);
                    else
                    {
                        mView.showMsg("失败!"+res.ret_code);

                    }
                }, e -> {
                    mView.showMsg("失败!"+e);
                    mView.hideDialog();
                }
        ));
    }

    @Override
    public void takeVideo(String apptoken, String did) {
        mView.showDialog();
        mRxManage.add(mModel.takeVideo(apptoken,did).subscribe(res -> {
                    mView.hideDialog();
                    if(res.ret_code==0)
                        mView.showMsg("请求已经发出"+res.ret_code);
                    else
                    {
                        mView.showMsg("失败!"+res.ret_code);

                    }
                }, e -> {
                    mView.showMsg("失败!"+e);
                    mView.hideDialog();
                }
        ));
    }

    @Override
    public void speekWords(String apptoken, String did,String words) {
        mView.showDialog();
        mRxManage.add(mModel.speekWords(apptoken,did,words).subscribe(res -> {
                    mView.hideDialog();
                    if(res.ret_code==0)
                        mView.showMsg("请求已经发出"+res.ret_code);
                    else
                    {
                        mView.showMsg("失败!"+res.ret_code);

                    }
                }, e -> {
                    mView.showMsg("失败!"+e);
                    mView.hideDialog();
                }
        ));
    }
}
