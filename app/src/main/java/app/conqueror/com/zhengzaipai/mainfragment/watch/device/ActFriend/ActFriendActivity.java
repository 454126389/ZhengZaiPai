package app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActFriend;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import app.conqueror.com.zhengzaipai.App;
import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.base.BaseActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActWifi.WifiActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.PhoneBook.BookAdd.BookAddActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.PhoneBook.BookDetailActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.PhoneBook.BookDetailContract;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.PhoneBook.BookDetailModel;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.PhoneBook.BookDetailPresenter;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.PhoneBook.PhoneBookActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.Friend;
import app.conqueror.com.zhengzaipai.mainfragment.watch.me.MultipleItem;
import app.conqueror.com.zhengzaipai.mainfragment.watch.me.MultipleItemQuickAdapter;
import app.conqueror.com.zhengzaipai.util.SpUtil;
import butterknife.Bind;
import butterknife.ButterKnife;

public class ActFriendActivity extends BaseActivity<ActFriendPresenter, ActFriendModel> implements ActFriendContract.View  {


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
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    public int getLayoutId() {

        return R.layout.device_act_friend;
    }

    @Override
    public void initView() {
        libBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        List<Friend> friendList= SpUtil.getWatchUserList().get(SpUtil.getChoise()).friends;
        List<MultipleItem> list = new ArrayList<>();

        if (null!=friendList&&friendList.size()>0)
        {
            for (int i=0;i<friendList.size();i++)
            {
                list.add(new MultipleItem(MultipleItem.ITEM_PHONE_BOOK, MultipleItem.IMG_SPAN_SIZE, friendList.get(i).getId(), 0, friendList.get(i).getNickName(), true));
            }
        }
        final MultipleItemQuickAdapter multipleItemAdapter = new MultipleItemQuickAdapter(this, list);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
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
                new android.app.AlertDialog.Builder(ActFriendActivity.this)
                        .setTitle(getString(R.string.tx_tip))
                        .setMessage(getString(R.string.tx_choise))
                        .setPositiveButton(getString(R.string.tx_rename), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                LayoutInflater factory = LayoutInflater.from(ActFriendActivity.this);//提示框
                                final View dialog_view = factory.inflate(R.layout.editbox_layout, null);//这里必须是final的
                                final EditText edit = (EditText) dialog_view.findViewById(R.id.editText);//获得输入框对象
                                new AlertDialog.Builder(ActFriendActivity.this)
                                        .setTitle(getString(R.string.tx_name_friend))//提示框标题
                                        .setView(dialog_view)
                                        .setPositiveButton(getString(R.string.btn_sure),//提示框的两个按钮
                                                new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog,
                                                                        int which) {
//
//                                                        App.showText(edit.getText().toString());
//                                                        mPresenter.wifiset(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id,list.get(position).getLabel()+","+edit.getText().toString()+","+list.get(position).getData());
                                                    }
                                                }).setNegativeButton(getString(R.string.btn_cancel), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                }).create().show();

                            }
                        }).setNegativeButton(getString(R.string.tx_del_friend), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

//                        mPresenter.ppr(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id, friendList.get(position).getId());
                    }
                })
                        .show();
//                startActivity(new Intent(ActFriendActivity.this,BookDetailActivity.class).putExtra("phone",bookList.get(position).split("-")[0]).putExtra("name",bookList.get(position).split("-")[1]).putExtra("id",position));
            }
        });

    }

    @Override
    public void initPresenter() {
//        mPresenter.setVM(this, mModel, this);
    }

    @Override
    public void showMsg(String msg) {

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


//    @Override
//    public void showMsg(String msg) {
//
//    }
//
//    @Override
//    public void initDialog() {
//
//    }
//
//    @Override
//    public void showDialog() {
//
//    }
//
//    @Override
//    public void hideDialog() {
//
//    }

    @Override
    public void suc() {
        App.showText(getString(R.string.tip_suc));
    }

    @Override
    public void fail() {
        App.showText(getString(R.string.tip_fail));
    }

}
