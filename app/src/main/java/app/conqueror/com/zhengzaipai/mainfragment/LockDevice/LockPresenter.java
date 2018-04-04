package app.conqueror.com.zhengzaipai.mainfragment.LockDevice;

import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.util.SpUtil;

/**
 * Created by Administrator on 2017/7/18.
 */

public class LockPresenter extends LockContract.Presenter {


    @Override
    public void onStart() {
        mView.initDialog();
        if(mView.getType().equals("lockcard")){
            if(SpUtil.getCardList()==null||SpUtil.getCardList().equals(""))
                getDeviceList(SpUtil.getAppid());
            else
                mView.initDeviceList(SpUtil.getCardList());
        }else
        {
            if(SpUtil.getDeviceList()==null||SpUtil.getDeviceList().equals(""))
                getDeviceList(SpUtil.getAppid());
            else
                mView.initDeviceList(SpUtil.getDeviceList());
        }
    }

    @Override
    public void getDeviceList(String appid) {
        mView.showDialog();
        if(mView.getType().equals("lockcard"))
        {
            mRxManage.add(mModel.getCardList(appid).subscribe(res -> {
                        mView.hideDialog();
                        if(!res.result)
                            mView.initLock();
                        else
                        {
                            SpUtil.setCardList(res.devicelist);
                            mView.initDeviceList(res.devicelist);
                        }
                    }, e -> {
                        mView.showMsg("失败!"+e);
                        mView.hideDialog();
                        mView.initLock();}
            ));

        }else
        {
            mRxManage.add(mModel.getDeviceList(appid).subscribe(res -> {
                        mView.hideDialog();
                        if(!res.result)
                            mView.initLock();
                        else
                        {
                            SpUtil.setDeviceList(res.devicelist);
                            mView.initDeviceList(res.devicelist);

                        }
                    }, e -> {
                        mView.showMsg("失败!"+e);
                        mView.hideDialog();
                        mView.initLock();}
            ));
        }
    }

    @Override
    public void LockDevice(String appid, String did,String phone,String name) {
        mView.showDialog();
        if(mView.getType().equals("lockcard")){
            mRxManage.add(mModel.LockCard(appid,did,phone,name).subscribe(res -> {
                        mView.hideDialog();
                        if(!res.result)
                        {
                            mView.showMsg(res.msg);
                            mView.initLock();
                        }
                        else
                        {
                            SpUtil.setCardList(res.devicelist);
                            mView.initDeviceList(res.devicelist);
                        }
                    }, e -> {mView.showMsg("登录失败!"+e);mView.hideDialog();mView.initLock();}

            ));

        }else{
            mRxManage.add(mModel.LockDevice(appid,did,phone,name).subscribe(res -> {
                        mView.hideDialog();
                        if(!res.result)
                        {
                            mView.showMsg(res.msg);
                            mView.initLock();
                        }
                        else
                        {
                            SpUtil.setDeviceList(res.devicelist);
                            mView.initDeviceList(res.devicelist);
                        }
                    }, e -> {mView.showMsg("登录失败!"+e);mView.hideDialog();mView.initLock();}

            ));

        }
    }

    @Override
    public void getDeviceCode(String phone) {
        mRxManage.add(mModel.getDeviceCode(phone).subscribe(res -> {
                    mView.hideDialog();
                    if(res.success)
                    {
                        mView.showMsg(context.getResources().getString(R.string.net_suc));
                        SpUtil.setCode(res.code);
                    }
                    else
                    {
                        mView.showMsg(context.getResources().getString(R.string.net_error));
//                        SpUtil.setDeviceList(res.devicelist);
//                        mView.initDeviceList(res.devicelist);
                    }
                }, e -> {
                    mView.showMsg(context.getResources().getString(R.string.net_error)+e);
                    mView.hideDialog();mView.initLock();
                }

        ));
    }
}
