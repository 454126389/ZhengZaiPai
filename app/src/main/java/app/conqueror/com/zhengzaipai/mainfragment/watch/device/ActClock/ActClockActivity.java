package app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActClock;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
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
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActClock.ClockSet.ClockSetActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.me.MultipleItem;
import app.conqueror.com.zhengzaipai.mainfragment.watch.me.MultipleItemQuickAdapter;
import app.conqueror.com.zhengzaipai.util.SpUtil;
import butterknife.Bind;

public class ActClockActivity extends BaseActivity<ActClockPresenter, ActClockModel> implements ActClockContract.View {
    List<MultipleItem> list = new ArrayList<>();

//    int choise;
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

     MultipleItemQuickAdapter multipleItemAdapter;
    List<String> clockList;
    @Override
    public int getLayoutId() {
        return R.layout.device_frag_alarm_info;
    }

    @Override
    public void initView() {

        libBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });





        multipleItemAdapter = new MultipleItemQuickAdapter(this, list);
        final GridLayoutManager manager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(manager);
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
                switch (view.getId())
                {
                    case R.id.item:
                        startActivity(new Intent(ActClockActivity.this, ClockSetActivity.class).putExtra("clock",clockList.get(position)).putExtra("index",""+position));
                        break;
                    case R.id.switch_compat:
                        List<String> clockList = SpUtil.getWatchUserList().get(SpUtil.getChoise()).clockList;
                        StringBuffer clockmsg=new StringBuffer();

                        String[] temp=clockList.get(position).split("-");

                        if(((SwitchCompat) view).isChecked())
//                             clockmsg=clockList.get(position).replace(clockList.get(position).split("-")[1],"1");
                            temp[1]="1";
                        else
//                             clockmsg=clockList.get(position).re
                            temp[1]="0";

                        for (int i=0;i<temp.length;i++)
                        {
                            clockmsg.append(temp[i]);
                            if (i<temp.length-1)
                                clockmsg.append("-");

                        }

                        clockList.set(position, clockmsg.toString());
                        mPresenter.remind(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id, clockList);
                        break;
                }


            }
        });



    }


    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel, this);
    }


    @Override
    protected void onResume() {
        super.onResume();

        list.clear();
        clockList = SpUtil.getWatchUserList().get(SpUtil.getChoise()).clockList;
        if(clockList==null)
        {
            clockList=new ArrayList<>();
            clockList.add("00:00-0-1");
            clockList.add("00:00-0-1");
            clockList.add("00:00-0-1");
            SpUtil.changeWatchUserListClockList(SpUtil.getChoise(),clockList);
        }
        for (int i = 0; i < clockList.size(); i++) {
            String tinme = clockList.get(i).split("-")[0];

            Boolean sw = false;
            if (clockList.get(i).split("-")[1].equals("0"))
                sw = false;
            else if (clockList.get(i).split("-")[1].equals("1"))
                sw = true;

            String type = null;
            if (clockList.get(i).split("-")[2].equals("1")) {
                type = getString(R.string.tx_clock_type1);
            } else if (clockList.get(i).split("-")[2].equals("2")) {
                type = getString(R.string.tx_clock_type2);
            } else if (clockList.get(i).split("-")[2].equals("3")) {
                type = getString(R.string.tx_clock_type3);
            }



            list.add(new MultipleItem(MultipleItem.ITEM_CLOCK, MultipleItem.IMG_SPAN_SIZE, tinme, 0, type, sw));
        }


        multipleItemAdapter.notifyDataSetChanged();
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
        App.showText(getString(R.string.tip_suc));
    }

    @Override
    public void fail() {
        App.showText(getString(R.string.tip_fail));
    }
}
