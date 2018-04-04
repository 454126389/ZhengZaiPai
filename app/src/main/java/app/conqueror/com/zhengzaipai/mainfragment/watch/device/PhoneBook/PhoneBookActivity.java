package app.conqueror.com.zhengzaipai.mainfragment.watch.device.PhoneBook;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.base.BaseActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.PhoneBook.BookAdd.BookAddActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.me.MultipleItem;
import app.conqueror.com.zhengzaipai.mainfragment.watch.me.MultipleItemQuickAdapter;
import app.conqueror.com.zhengzaipai.util.SpUtil;
import butterknife.Bind;

public class PhoneBookActivity extends  BaseActivity implements View.OnClickListener{


    @Bind(R.id.title_bar_status)
    View titleBarStatus;
    @Bind(R.id.lib_btn_back)
    ImageButton libBtnBack;
    @Bind(R.id.lib_tv_title)
    TextView libTvTitle;
    @Bind(R.id.title_right_btn)
    ImageButton titleRightBtn;
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
        return R.layout.account_frag_phone;
    }

    @Override
    public void initView() {

        titleRightBtn.setVisibility(View.VISIBLE);
        titleRightBtn.setOnClickListener(this);


        libBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));


    }

    @Override
    protected void onResume() {
        super.onResume();
        List<String> bookList=SpUtil.getWatchUserList().get(SpUtil.getChoise()).bookList;
        List<MultipleItem> list = new ArrayList<>();

        if (null!=bookList&&bookList.size()>0)
        {
            for (int i=0;i<bookList.size();i++)
            {
                list.add(new MultipleItem(MultipleItem.ITEM_PHONE_BOOK, MultipleItem.IMG_SPAN_SIZE, bookList.get(i).split("-")[0], 0, bookList.get(i).split("-")[1], true));
            }
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

                startActivity(new Intent(PhoneBookActivity.this,BookDetailActivity.class).putExtra("phone",bookList.get(position).split("-")[0]).putExtra("name",bookList.get(position).split("-")[1]).putExtra("id",position));
            }
        });
    }

    @Override
    public void initPresenter() {
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(PhoneBookActivity.this,BookAddActivity.class));
    }
}
