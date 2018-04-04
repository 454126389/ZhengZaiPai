package app.conqueror.com.zhengzaipai.mainfragment.watch.device.AddDevice.addlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.base.BaseActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.AddDevice.addlist.binddevice.BindDeviceActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.me.MultipleItem;
import app.conqueror.com.zhengzaipai.mainfragment.watch.me.MultipleItemQuickAdapter;
import app.conqueror.com.zhengzaipai.util.SpUtil;
import butterknife.Bind;
import butterknife.ButterKnife;

public class AddListActivity extends BaseActivity {


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
        return R.layout.login_frag_bind_device_selete;
    }

    @Override
    public void initView() {

        libBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        List<MultipleItem> list = new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        list.add(new MultipleItem(MultipleItem.ITEM_DEVICE_SELECT, MultipleItem.IMG_SPAN_SIZE, getString(R.string.special_function), 0, getString(R.string.tx_introduce), true));
        final MultipleItemQuickAdapter multipleItemAdapter = new MultipleItemQuickAdapter(this, list);
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
                startActivity(new Intent(AddListActivity.this,BindDeviceActivity.class).putExtra("phone", SpUtil.getAppUser().phone));
            }
        });



    }

    @Override
    public void initPresenter() {

    }

}
