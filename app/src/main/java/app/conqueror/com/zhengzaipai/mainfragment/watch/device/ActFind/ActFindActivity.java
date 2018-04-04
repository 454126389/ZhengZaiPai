package app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActFind;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import app.conqueror.com.zhengzaipai.App;
import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.base.BaseActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.ChatMsg;
import app.conqueror.com.zhengzaipai.mainfragment.watch.home.chatlist.chat.ChatContract;
import app.conqueror.com.zhengzaipai.mainfragment.watch.home.chatlist.chat.ChatModel;
import app.conqueror.com.zhengzaipai.mainfragment.watch.home.chatlist.chat.ChatPresenter;
import app.conqueror.com.zhengzaipai.util.SpUtil;
import butterknife.Bind;
import butterknife.ButterKnife;

public class ActFindActivity extends BaseActivity<ActFindPresenter, ActFindModel> implements ActFindContract.View,View.OnClickListener {


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
    @Bind(R.id.icon)
    ImageView icon;
    @Bind(R.id.submit)
    Button submit;

    @Override
    public int getLayoutId() {
        return R.layout.device_act_find;
    }

    @Override
    public void initView() {
        libBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        submit.setOnClickListener(this);
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel, this);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.submit:
                mPresenter.find(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id);
                break;

        }
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
    public void suc() {
        App.showText(getString(R.string.tip_suc));
    }

    @Override
    public void fail() {
        App.showText(getString(R.string.tip_fail));
    }
}
