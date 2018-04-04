package app.conqueror.com.zhengzaipai.mainfragment.watch.home.chatlist;

import android.content.Intent;
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
import app.conqueror.com.zhengzaipai.mainfragment.watch.home.chatlist.chat.ChatActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.me.MultipleItem;
import app.conqueror.com.zhengzaipai.mainfragment.watch.me.MultipleItemQuickAdapter;
import app.conqueror.com.zhengzaipai.util.SpUtil;
import butterknife.Bind;

public class ChatListActivity extends BaseActivity {


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
    @Bind(R.id.status_layout)
    FrameLayout statusLayout;



    @Override
    public int getLayoutId() {
        return R.layout.frag_chat_contacts;
    }

    @Override
    public void initView() {


        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        List<MultipleItem> list = new ArrayList<>();
        List<WatchDevice> deviceList = SpUtil.getAppUser().deviceList;
        for (int i=0;i<deviceList.size();i++)
        {
            list.add(new MultipleItem(MultipleItem.ITEM_CHAT_CONTACT, MultipleItem.IMG_SPAN_SIZE, deviceList.get(i).nickName, R.mipmap.device_ic_phone_headimg_default_s, null, true));
        }
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
                Intent intent=new Intent(ChatListActivity.this, ChatActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });


        libBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    public void initPresenter() {

    }


}
