package app.conqueror.com.zhengzaipai.mainfragment.watch.device.PhoneBook;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import app.conqueror.com.zhengzaipai.App;
import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.base.BaseActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.PhoneBook.BookAdd.BookAddActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.PhoneBook.BookAdd.BookAddContract;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.PhoneBook.BookAdd.BookAddModel;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.PhoneBook.BookAdd.BookAddPresenter;
import app.conqueror.com.zhengzaipai.util.SpUtil;
import butterknife.Bind;

public class BookDetailActivity extends BaseActivity<BookDetailPresenter, BookDetailModel> implements BookDetailContract.View {


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
    @Bind(R.id.tv_phone)
    TextView tvPhone;
    @Bind(R.id.tv_name)
    TextView tvName;

    private final static int REQUESTCODE = 1; // 返回的结果码

    @Override
    public int getLayoutId() {
        return R.layout.device_frag_phone_book_detail;
    }

    @Override
    public void initView() {

        libBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        Intent intent = getIntent();
        String phone = intent.getStringExtra("phone");
        String name = intent.getStringExtra("name");
        int id = intent.getIntExtra("id",0);

        titleRightIv.setVisibility(View.VISIBLE);
        titleRightIv.setImageResource(R.mipmap.device_ic_phone_more);


        titleRightIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new android.app.AlertDialog.Builder(BookDetailActivity.this)
                        .setTitle(getString(R.string.menu_phone_list))
                        .setMessage(getString(R.string.tx_choise))
                        .setPositiveButton(getString(R.string.btn_edit), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // 意图实现activity的跳转
                                Intent intent = new Intent(BookDetailActivity.this,BookAddActivity.class);

                                intent.putExtra("name", name);
                                intent.putExtra("phone", phone);
                                intent.putExtra("id", id);


                                // 这种启动方式：startActivity(intent);并不能返回结果
                                startActivityForResult(intent, REQUESTCODE); //REQUESTCODE--->1
                            }
                        }).setNegativeButton(getString(R.string.delete), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        List<String> bookList = SpUtil.getWatchUserList().get(SpUtil.getChoise()).bookList;
                        bookList.remove(id);
                        mPresenter.phb(SpUtil.getWatchUserList().get(SpUtil.getChoise()).id, bookList);
                    }
                })
                        .show();
            }
        });



        tvPhone.setText(phone);
        tvName.setText(name);
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel, this);
    }

    // 为了获取结果
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // RESULT_OK，判断另外一个activity已经结束数据输入功能，Standard activity result:
        // operation succeeded. 默认值是-1
        if (resultCode == 2) {
            if (requestCode == REQUESTCODE) {
                String phone = data.getStringExtra("phone");
                String name = data.getStringExtra("name");
                tvPhone.setText(phone);
                tvName.setText(name);
                //设置结果显示框的显示数值
//                result.setText(String.valueOf(three));
            }
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
        finish();
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
