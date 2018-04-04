package app.conqueror.com.zhengzaipai.app.Function;

import app.conqueror.com.zhengzaipai.app.Function.FunctionContract;

/**
 * Created by Administrator on 2017/7/20.
 */

public class FunctionPresenter extends FunctionContract.Presenter {
    @Override
    public void onStart() {

    }

    @Override
    public void shutdown(String apptoken, String did) {
        mView.showDialog();
        mRxManage.add(mModel.shutdown(apptoken,did).subscribe(res -> {
                    mView.hideDialog();
                    if(res.ret_code==0)
                        mView.showMsg("请稍等，指令已发出");
                    else
                    {
                        mView.showMsg("登录失败!"+res.ret_code);

                    }
                }, e -> {
                    mView.showMsg("登录失败!"+e);
                    mView.hideDialog();
                }

        ));
    }
}
