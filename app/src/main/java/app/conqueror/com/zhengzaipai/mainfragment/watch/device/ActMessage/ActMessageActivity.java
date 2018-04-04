package app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActMessage;

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
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WatchMsg;
import app.conqueror.com.zhengzaipai.mainfragment.watch.me.MultipleItem;
import app.conqueror.com.zhengzaipai.mainfragment.watch.me.MultipleItemQuickAdapter;
import app.conqueror.com.zhengzaipai.util.SpUtil;
import butterknife.Bind;
import butterknife.ButterKnife;

public class ActMessageActivity extends BaseActivity {


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
        return R.layout.account_act_message_center;
    }

    @Override
    public void initView() {


        libBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        List<MultipleItem> list = new ArrayList<>();
        List<WatchMsg> watchMsgList=SpUtil.getWatchMsgList(SpUtil.getUsername());
        if (watchMsgList!=null)
        {
            for (int i=0;i<watchMsgList.size();i++)
             list.add(new MultipleItem(MultipleItem.ITEM_MESSAGE, MultipleItem.IMG_SPAN_SIZE, 0,watchMsgList.get(i).getID(), 0,  watchMsgList.get(i).getMSG(), true,watchMsgList.get(i).getTIME()));

            final MultipleItemQuickAdapter multipleItemAdapter = new MultipleItemQuickAdapter(this, list);
    //        final GridLayoutManager manager = new GridLayoutManager(myContext, 4);
    //        recycler_view_extra.setLayoutManager(manager);
            multipleItemAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
                @Override
                public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                    return list.get(position).getSpanSize();
                }
            });
            recyclerView.setAdapter(multipleItemAdapter);
        }


    }

    @Override
    public void initPresenter() {

    }



}
