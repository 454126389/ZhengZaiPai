package app.conqueror.com.zhengzaipai.mainfragment.watch.device.LoveBons;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Space;
import android.widget.TextView;

import app.conqueror.com.zhengzaipai.App;
import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.base.BaseActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.map.zonelist.ZoneListContract;
import app.conqueror.com.zhengzaipai.mainfragment.watch.map.zonelist.ZoneListModel;
import app.conqueror.com.zhengzaipai.mainfragment.watch.map.zonelist.ZoneListPresenter;
import app.conqueror.com.zhengzaipai.util.SpUtil;
import butterknife.Bind;
import butterknife.ButterKnife;

public class LoveBonsActivity extends BaseActivity<LoveBonsPresenter, LoveBonsModel> implements LoveBonsContract.View, View.OnClickListener {


    @Bind(R.id.title_bar_status)
    View titleBarStatus;
    @Bind(R.id.lib_btn_back)
    ImageButton libBtnBack;
    @Bind(R.id.lib_tv_title)
    TextView libTvTitle;
    @Bind(R.id.title_right_btn)
    Button titleRightBtn;
    @Bind(R.id.title_right_iv)
    ImageView titleRightIv;
    @Bind(R.id.lib_tv_right)
    TextView libTvRight;
    @Bind(R.id.lib_bottom_line)
    View libBottomLine;
    @Bind(R.id.tip)
    TextView tip;
    @Bind(R.id.deviceImageView)
    ImageView deviceImageView;
    @Bind(R.id.head_img)
    ImageView headImg;
    @Bind(R.id.value)
    EditText value;
    @Bind(R.id.btn_min)
    ImageView btnMin;
    @Bind(R.id.btn_add)
    ImageView btnAdd;
    @Bind(R.id.btn_ok)
    Button btnOk;

    @Override
    public int getLayoutId() {
        return R.layout.device_act_love_bonus;
    }

    int flower;
    int addflower=0;
    @Override
    public void initView() {
        libBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        btnMin.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnOk.setOnClickListener(this);
    }


    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel, this);
    }


    @Override
    public void showMsg(String msg) {
        App.showText(msg);
    }

    @Override
    public void initDialog() {

    }

    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_min:
//                if (flower > 0) {
                    addflower--;
                    value.setText("" + addflower);
//                }
                break;
            case R.id.btn_add:
//                if (flower < 10) {
                    addflower++;
                    value.setText("" + addflower);
//                }
                break;
            case R.id.btn_ok:
                mPresenter.flower(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id,""+(flower+addflower));
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        flower = Integer.parseInt(SpUtil.getWatchUserList().get(SpUtil.getChoise()).flower);
//        value.setText("" + flower);
        value.setText("" + addflower);
    }

    @Override
    public void suc() {
        App.showText(getString(R.string.tip_suc));
    }

    @Override
    public void fail() {
        App.showText(getString(R.string.tip_fail));
    }
}
