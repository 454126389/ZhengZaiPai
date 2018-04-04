package app.conqueror.com.zhengzaipai.mainfragment.watch.device.AddDevice;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import app.conqueror.com.zhengzaipai.App;
import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.base.BaseActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.AddDevice.addlist.AddListActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.me.MultipleItem;
import app.conqueror.com.zhengzaipai.mainfragment.watch.me.MultipleItemQuickAdapter;
import app.conqueror.com.zhengzaipai.util.SpUtil;
import butterknife.Bind;
import butterknife.ButterKnife;

public class AddDeviceActivity extends BaseActivity<AddDevicePresenter, AddDeviceModel> implements AddDeviceContract.View, View.OnClickListener {


    List<MultipleItem> list;
    MultipleItemQuickAdapter multipleItemAdapter;
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
    @Bind(R.id.tv_tip)
    TextView tvTip;
    @Bind(R.id.googleProgress)
    ProgressBar googleProgress;
    @Bind(R.id.swipe_target)
    RecyclerView recyclerView;
    @Bind(R.id.refresh_layout)
    SwipeToLoadLayout refreshLayout;
    @Bind(R.id.content)
    LinearLayout content;
    @Bind(R.id.iv_case)
    ImageView ivCase;
    @Bind(R.id.btn_ok)
    Button btnOk;


    @Override
    public int getLayoutId() {
        return R.layout.login_frag_bind_device_state;
    }

    @Override
    public void initView() {
        libBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnOk.setOnClickListener(this);

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
//                for (int i = 0; i < 20; i++) {
//                    list.add(0,"刷新获得的数据");
//                }
//                myAdapter.notifyDataSetChanged();
                //设置下拉刷新结束
                refreshLayout.setRefreshing(false);
            }
        });


    }


    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel, this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_ok:
                startActivity(new Intent(AddDeviceActivity.this, AddListActivity.class));
                break;
        }
    }

    @Override
    public void showMsg(String msg) {
        App.showText(msg);
    }

    @Override
    public void initDialog() {
        initBaseDialog();
    }

    @Override
    public void showDialog() {
        showBaseDialog();
    }

    @Override
    public void hideDialog() {
        hideBaseDialog();
    }

    @Override
    public void initList(String mes) {


//        if(null==mes&&!SpUtil.getMess().equals(""))
//            mes=SpUtil.getMess();
//        if(null!=mes&&SpUtil.getMess().equals(""))
//            SpUtil.setMess(mes);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        String[] mesmap = mes.split(";");

        list = new ArrayList<>();

        if (mesmap.length > 0)
            tvTip.setVisibility(View.INVISIBLE);
        for (int i = 0; i < mesmap.length; i++) {
            list.add(new MultipleItem(MultipleItem.ITEM_DEVICE_AUTH, MultipleItem.IMG_SPAN_SIZE, (mesmap[i].toString()).split(",")[2], 0, getString(R.string.tx_auth_wait), true));
        }

        multipleItemAdapter = new MultipleItemQuickAdapter(AddDeviceActivity.this, list);
        multipleItemAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                return list.get(position).getSpanSize();
            }
        });

        recyclerView.setAdapter(multipleItemAdapter);
        multipleItemAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                new AlertDialog.Builder(AddDeviceActivity.this)
                        .setTitle(getString(R.string.tx_auth))
                        .setMessage(getString(R.string.tx_auth_tip))
                        .setPositiveButton(getString(R.string.btn_sure), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                for (int j = 0; j < SpUtil.getAppUser().deviceList.size(); j++)
//                                    if (SpUtil.getAppUser().deviceList.get(j).id.equals((mesmap[i + 1].toString()).split(",")[2]))
                                        if (SpUtil.getAppUser().deviceList.get(j).rank.equals("1"))
                                            mPresenter.auth(position, (mesmap[i + 1].toString()).split(",")[2], (mesmap[i + 1].toString()).split(",")[0], (mesmap[i + 1].toString()).split(",")[3], (mesmap[i + 1].toString()).split(",")[4]);
//                                        else
//                                            App.showText(getString(R.string.tx_no_power));


//                                addSuc(position);

                            }
                        }).setNegativeButton(getString(R.string.btn_cancel), null)
                        .show();
            }
        });
    }

    @Override
    public void addSuc(int poi) {
        list.remove(poi);
        multipleItemAdapter.notifyDataSetChanged();
        SpUtil.setMess("",SpUtil.getUsername());
    }


    @Override
    public void suc() {
        App.showText(getString(R.string.tip_suc));
    }

    @Override
    public void fail() {
        App.showText(getString(R.string.tip_fail));
    }

    @Override
    public void authed() {
        App.showText(getString(R.string.tx_authed));
    }



}
