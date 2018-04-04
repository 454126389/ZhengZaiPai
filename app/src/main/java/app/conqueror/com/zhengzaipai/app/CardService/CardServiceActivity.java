package app.conqueror.com.zhengzaipai.app.CardService;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;

import java.util.ArrayList;
import java.util.List;

import app.conqueror.com.zhengzaipai.App;
import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.base.BaseActivity;
import app.conqueror.com.zhengzaipai.entity.Plan;
import butterknife.Bind;
import butterknife.ButterKnife;

public class CardServiceActivity extends BaseActivity<CardServicePresenter, CardSrerviceModel> implements CardServiceContract.View {


    @Bind(R.id.base_list)
    ListView baseList;
    @Bind(R.id.add_list)
    ListView addList;
    @Bind(R.id.update_list)
    ListView updateList;
    @Bind(R.id.tabhost)
    TabHost tabhost;

    @Override
    public int getLayoutId() {
        return R.layout.activity_cardservice;
    }

    @Override
    public void initView() {
        tabhost.setup();
        tabhost.addTab(tabhost.newTabSpec("tab1").setIndicator("基本套餐")
                .setContent(R.id.tab1));

        tabhost.addTab(tabhost.newTabSpec("tab2").setIndicator("当月套餐")
                .setContent(R.id.tab2));

        tabhost.addTab(tabhost.newTabSpec("tab3").setIndicator("套餐升级")
                .setContent(R.id.tab3));




    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel,this);
    }


//    @Override
//    public void initDialog() {
//        initBaseDialog();
//    }

    @Override
    public void showDialog() {
        showBaseDialog();
    }


    @Override
    public void hideDialog() {
        hideBaseDialog();
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
    public void initServiceList(List<Plan> baseset, List<Plan> changeset, List<Plan> content_flowall) {
        baseList.setAdapter(new ServiceAdapter(this, baseset));
        baseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        addList.setAdapter(new ServiceAdapter(this, changeset));
        updateList.setAdapter(new ServiceAdapter(this, content_flowall));
    }






}
