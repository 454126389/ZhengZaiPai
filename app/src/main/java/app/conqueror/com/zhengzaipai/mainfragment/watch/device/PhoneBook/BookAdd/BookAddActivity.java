package app.conqueror.com.zhengzaipai.mainfragment.watch.device.PhoneBook.BookAdd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import app.conqueror.com.zhengzaipai.App;
import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.base.BaseActivity;
import app.conqueror.com.zhengzaipai.util.SpUtil;
import butterknife.Bind;
import butterknife.ButterKnife;

public class BookAddActivity extends BaseActivity<BookAddPresenter, BookAddModel> implements BookAddContract.View, View.OnClickListener {


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
    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.btn_pick_contact)
    LinearLayout btnPickContact;

    Intent intent;
    int id=-1;
    String name;
    String phone;
    @Override
    public int getLayoutId() {
        return R.layout.device_frag_phone_book_edit;
    }

    @Override
    public void initView() {


        // 去除传递过来的意图,并提取数据
        intent = getIntent();
        if(null!=intent)
        {
             name = intent.getStringExtra("name"); // 没有输入值默认为0
             phone = intent.getStringExtra("phone"); // 没有输入值默认为0
            id = intent.getIntExtra("id",0);
            etPhone.setText(phone);
            etName.setText(name);
        }


        libBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        titleRightBtn.setText(getString(R.string.btn_sure));
        titleRightBtn.setVisibility(View.VISIBLE);
        titleRightBtn.setOnClickListener(this);

    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel, this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_right_btn:
                if (etPhone.getText().toString().equals("") || etPhone.getText().toString().length() == 0 || etName.getText().toString().equals("") || etName.getText().toString().length() == 0) {
                    App.showText(getString(R.string.no_null));
                } else {
                    List<String> bookList = SpUtil.getWatchUserList().get(SpUtil.getChoise()).bookList;
                    if(null!=name)
                        bookList.set(id,etPhone.getText().toString()+"-"+etName.getText().toString());
                    else
                        bookList.add(etPhone.getText().toString()+"-"+etName.getText().toString());
                    mPresenter.phb(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id, bookList);
                }
                break;

        }
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
    public void addSuc() {
        if(null!=name)
        {
            Intent intent = new Intent();
        // 获取用户计算后的结果
        intent.putExtra("phone", etPhone.getText().toString()); //将计算的值回传回去
        intent.putExtra("name", etName.getText().toString()); //将计算的值回传回去
        //通过intent对象返回结果，必须要调用一个setResult方法，
        //setResult(resultCode, data);第一个参数表示结果返回码，一般只要大于1就可以，但是
        setResult(2, intent);
        }
        finish(); //结束当前的activity的生命周期
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
