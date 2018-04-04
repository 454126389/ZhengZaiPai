package app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActWifi;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import app.conqueror.com.zhengzaipai.App;
import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.base.BaseActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.AddDevice.addlist.AddListActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WifiMsg;
import app.conqueror.com.zhengzaipai.mainfragment.watch.me.MultipleItem;
import app.conqueror.com.zhengzaipai.mainfragment.watch.me.MultipleItemQuickAdapter;
import app.conqueror.com.zhengzaipai.util.SpUtil;
import butterknife.Bind;

public class WifiActivity extends  BaseActivity<WifiPresenter, WifiModel> implements WifiContract.View, View.OnClickListener {


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
    @Bind(R.id.tv_wifi)
    TextView tvWifi;
    @Bind(R.id.tv_wifi_label)
    TextView tvWifiLabel;
    @Bind(R.id.btn_scan)
    TextView btnScan;
    @Bind(R.id.iv_loading)
    ContentLoadingProgressBar ivLoading;
    @Bind(R.id.tv_checking)
    TextView tvChecking;
    @Bind(R.id.layout_loading)
    LinearLayout layoutLoading;
    @Bind(R.id.googleProgress)
    ProgressBar googleProgress;
    @Bind(R.id.swipe_target)
    RecyclerView swipeTarget;
    @Bind(R.id.refresh_layout)
    SwipeToLoadLayout refreshLayout;
    @Bind(R.id.status_layout)
    FrameLayout statusLayout;
    @Bind(R.id.btn_ok)
    Button btnOk;

    @Override
    public int getLayoutId() {
        return R.layout.device_frag_wifi_list;
    }


//    private final Handler handler = new Handler();


    @Override
    public void initView() {
        libBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnScan.setOnClickListener(this);
        btnOk.setOnClickListener(this);

        swipeTarget.setLayoutManager(new GridLayoutManager(this, 1));

        handler.sendEmptyMessage(0);

    }


    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel, this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_ok:
                startActivity(new Intent(WifiActivity.this, AddListActivity.class));
                break;
            case R.id.btn_scan:
              mPresenter.wifisearch(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id);
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


    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0:
                    // 移除所有的msg.what为0等消息，保证只有一个循环消息队列再跑
                    handler.removeMessages(0);
                    // app的功能逻辑处理
                    List<WifiMsg> wifiMsgList=SpUtil.getWifiMsgList(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id);
                    if(wifiMsgList==null)
                        // 再次发出msg，循环更新
                        handler.sendEmptyMessageDelayed(0, 2000);
                    else
                    {
                        App.showText(wifiMsgList.get(0).name);

                        List<MultipleItem> list = new ArrayList<>();
                        if (null!=wifiMsgList&&wifiMsgList.size()>0)
                        {
                            for (int i=0;i<wifiMsgList.size();i++)
                            {
                                list.add(new MultipleItem(MultipleItem.ITEM_PHONE_BOOK, MultipleItem.IMG_SPAN_SIZE, wifiMsgList.get(i).name, 0, wifiMsgList.get(i).address, true));
                            }
                        }
                        final MultipleItemQuickAdapter multipleItemAdapter = new MultipleItemQuickAdapter(WifiActivity.this, list);
                        multipleItemAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
                            @Override
                            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                                return list.get(position).getSpanSize();
                            }
                        });

                        swipeTarget.setAdapter(multipleItemAdapter);
                        multipleItemAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                            @Override
                            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                                LayoutInflater factory = LayoutInflater.from(WifiActivity.this);//提示框
                                final View dialog_view = factory.inflate(R.layout.editbox_layout, null);//这里必须是final的
                                final EditText edit = (EditText) dialog_view.findViewById(R.id.editText);//获得输入框对象
                                new AlertDialog.Builder(WifiActivity.this)
                                        .setTitle(getString(R.string.tx_name_wifi))//提示框标题
                                        .setView(dialog_view)
                                        .setPositiveButton(getString(R.string.btn_sure),//提示框的两个按钮
                                                new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog,
                                                                        int which) {
//
//                                                        App.showText(edit.getText().toString());
                                                        mPresenter.wifiset(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id,list.get(position).getLabel()+","+edit.getText().toString()+","+list.get(position).getData());
                                                    }
                                                }).setNegativeButton(getString(R.string.btn_cancel), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                }).create().show();
                            }
                        });

                        handler.removeMessages(0);
                        SpUtil.setWifiMsgList(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id,null);
                    }
                    break;

                case 1:
                    // 直接移除，定时器停止
                    handler.removeMessages(0);
                    break;

                default:
                    break;
            }
        };
    };


    @Override
    public void suc() {
        App.showText(getString(R.string.tip_suc));
    }

    @Override
    public void fail() {
        App.showText(getString(R.string.tip_fail));
    }

}
