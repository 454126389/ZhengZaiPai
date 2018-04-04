package app.conqueror.com.zhengzaipai.mainfragment.watch.me;

import android.os.Bundle;
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

import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.base.BaseActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WatchDevice;
import app.conqueror.com.zhengzaipai.util.SpUtil;
import butterknife.Bind;
import butterknife.ButterKnife;

public class FamilyActivity extends BaseActivity {


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

    @Override
    public int getLayoutId() {
        return R.layout.family;
    }

    @Override
    public void initView() {
        libBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        List<WatchDevice> deviceList= SpUtil.getAppUser().deviceList;
        rvDevices.setLayoutManager(new GridLayoutManager(this, 1));
        List<MultipleItem> list = new ArrayList<>();
        if (null!=deviceList&&deviceList.size()>0)
        {
            for (int i=0;i<deviceList.size();i++)
            {
//                list.add(new MultipleItem(MultipleItem.ITEM_PHONE_BOOK, MultipleItem.IMG_SPAN_SIZE, bookList.get(i), 0, bookList.get(i+1), true));
                list.add(new MultipleItem(MultipleItem.ITEM_PHONE_BOOK, MultipleItem.IMG_SPAN_SIZE, deviceList.get(i).nexus, 0, deviceList.get(i).id, true));
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

    }

    @Override
    public void initPresenter() {

    }

}
