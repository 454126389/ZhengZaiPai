package app.conqueror.com.zhengzaipai.app.CardService;

import app.conqueror.com.zhengzaipai.util.SpUtil;

/**
 * Created by Administrator on 2017/7/18.
 */

public class CardServicePresenter extends CardServiceContract.Presenter {


    @Override
    public void onStart() {
        mView.initDialog();
        getServiceList(SpUtil.getCard().cardid);
    }

    @Override
    public void getServiceList(String iccid) {
        mView.showDialog();
        mRxManage.add(mModel.getServiceList(iccid).subscribe(res -> {
                        mView.hideDialog();
                        mView.initServiceList(res.baseset,res.changeset,res.content_flowall);
                }, e -> {
                    mView.showMsg("失败!"+e);
                    mView.hideDialog();
                }
        ));
    }

    @Override
    public void buyService(String iccid) {

    }
}
