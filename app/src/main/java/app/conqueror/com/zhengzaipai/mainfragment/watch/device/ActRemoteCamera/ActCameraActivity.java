package app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActRemoteCamera;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import app.conqueror.com.zhengzaipai.App;
import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.base.BaseActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.me.MultipleItem;
import app.conqueror.com.zhengzaipai.mainfragment.watch.me.MultipleItemQuickAdapter;
import app.conqueror.com.zhengzaipai.util.SpUtil;
import app.conqueror.com.zhengzaipai.view.layout.SwipeBackLayout;
import butterknife.Bind;
import butterknife.ButterKnife;

public class ActCameraActivity extends BaseActivity<ActCameraPresenter, ActCameraModel> implements ActCameraContract.View, View.OnClickListener {


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
    @Bind(R.id.tv_data)
    TextView tvData;
    @Bind(R.id.swipe_target)
    RecyclerView swipeTarget;
    @Bind(R.id.refresh_layout)
    SwipeBackLayout refreshLayout;
    @Bind(R.id.btn_ok)
    Button btnOk;

    List<String> imglist;
    MultipleItemQuickAdapter multipleItemAdapter;
    List<MultipleItem> list;

    @Override
    public int getLayoutId() {
        return R.layout.device_act_remote_camera;
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
        swipeTarget.setLayoutManager(new GridLayoutManager(this, 4));

        imglist = SpUtil.getImgList(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id);
        if (imglist == null)
            imglist = new ArrayList<>();


        list = new ArrayList<>();
        for (int i = 0; i < imglist.size(); i++)
            list.add(new MultipleItem(MultipleItem.IMG, MultipleItem.IMG_SPAN_SIZE, imglist.get(i), 0, getString(R.string.tx_introduce), true));
        multipleItemAdapter = new MultipleItemQuickAdapter(this, list);
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

                String imageUrl = imglist.get(position);
                Intent intent = new Intent(ActCameraActivity.this, ImageScaleActivity.class);
                intent.putExtra("filePath", imageUrl);
                startActivity(intent);

            }
        });

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
            case R.id.btn_ok:
                mPresenter.rcapture(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        List<String> tempimglist = SpUtil.getImgList(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id);

        if (null != tempimglist) {
            for (int i = list.size(); i < tempimglist.size(); i++) {
                list.add(new MultipleItem(MultipleItem.IMG, MultipleItem.IMG_SPAN_SIZE, tempimglist.get(i), 0, getString(R.string.tx_introduce), true));
                imglist.add(tempimglist.get(i));
            }
        }
        multipleItemAdapter.notifyDataSetChanged();
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
