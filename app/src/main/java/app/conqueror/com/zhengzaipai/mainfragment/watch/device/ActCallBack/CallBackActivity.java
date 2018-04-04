package app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActCallBack;

import android.content.DialogInterface;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import app.conqueror.com.zhengzaipai.App;
import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.base.BaseActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WatchDevice;
import app.conqueror.com.zhengzaipai.mainfragment.watch.home.phone.AlertDialogFragment;
import app.conqueror.com.zhengzaipai.mainfragment.watch.me.MultipleItem;
import app.conqueror.com.zhengzaipai.mainfragment.watch.me.MultipleItemQuickAdapter;
import app.conqueror.com.zhengzaipai.util.SpUtil;
import butterknife.Bind;

public class CallBackActivity extends BaseActivity<CallBackPresenter, CallBackModel> implements CallBackContract.View,View.OnClickListener {


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

    @Bind(R.id.rv_devices)
    RecyclerView rvDevices;
    @Bind(R.id.root_phone_card)
    FrameLayout rootPhoneCard;
    @Bind(R.id.container)
    FrameLayout container;

    List<WatchDevice> deviceList;

    @Override
    public int getLayoutId() {
        return R.layout.account_act_phone;
    }

    @Override
    public void initView() {

        libBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        rvDevices.setLayoutManager(new GridLayoutManager(this, 1));
        deviceList= SpUtil.getAppUser().deviceList;
        List<MultipleItem> list = new ArrayList<>();

//        for (int i=0;i<bookList.size();i++)
//        {
//            list.add(new MultipleItem(MultipleItem.ITEM_PHONE_BOOK, MultipleItem.IMG_SPAN_SIZE,bookList.get(i).split("-")[0], 0, bookList.get(i).split("-")[1], true));
//        }
        if (null!=deviceList&&deviceList.size()>0)
        {
            for (int i=0;i<deviceList.size();i++)
            {
//                list.add(new MultipleItem(MultipleItem.ITEM_PHONE_BOOK, MultipleItem.IMG_SPAN_SIZE, bookList.get(i), 0, bookList.get(i+1), true));
//                list.add(new MultipleItem(MultipleItem.ITEM_PHONE_BOOK, MultipleItem.IMG_SPAN_SIZE, bookList.get(i).split("-")[0], 0, bookList.get(i).split("-")[1], true));
                list.add(new MultipleItem(MultipleItem.ITEM_PHONE_BOOK, MultipleItem.IMG_SPAN_SIZE, deviceList.get(i).nickName, 0, deviceList.get(i).id, true));
            }
        }


        final MultipleItemQuickAdapter multipleItemAdapter = new MultipleItemQuickAdapter(this, list);

        multipleItemAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                return list.get(position).getSpanSize();
            }
        });

        rvDevices.setAdapter(multipleItemAdapter);

        multipleItemAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {


                AlertDialogFragment dialogFragment =AlertDialogFragment.newInstance(deviceList.get(position).nickName,deviceList.get(position).id,1);
                dialogFragment.setOnButtonClick(new AlertDialogFragment.OnButtonClick() {
                    @Override
                    public void onClick() {
                        mPresenter.monitor(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id,SpUtil.getAppUser().phone);
                    }
                });
                dialogFragment.show(getFragmentManager(), "AlertDialog");

//                new android.app.AlertDialog.Builder(CallBackActivity.this)
//                        .setTitle("回拨")
//                        .setMessage("请求设备该回拨")
//                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
////                                        Toast.makeText(getActivity(), "已请求", Toast.LENGTH_SHORT).show();
////                                        mPresenter.getWatchPoi();
//                                mPresenter.monitor(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id,bookList.get(position).split("-")[0]);
//                            }
//                        }).setNegativeButton("取消", null)
//                        .show();



            }
        });
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel, this);
    }


    @Override
    public void onClick(View view) {

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
        showMsg(getString(R.string.tx_call_suc));
    }

    @Override
    public void fail() {
        showMsg(getString(R.string.tip_fail));
    }
}
