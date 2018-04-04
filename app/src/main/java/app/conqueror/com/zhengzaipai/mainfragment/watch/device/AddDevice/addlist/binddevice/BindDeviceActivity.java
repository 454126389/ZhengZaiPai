package app.conqueror.com.zhengzaipai.mainfragment.watch.device.AddDevice.addlist.binddevice;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import app.conqueror.com.zhengzaipai.App;
import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.base.BaseActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.WatchTabActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.login.LoginActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.me.MultipleItem;
import app.conqueror.com.zhengzaipai.mainfragment.watch.me.MultipleItemQuickAdapter;
import app.conqueror.com.zhengzaipai.util.SpUtil;
import app.conqueror.com.zhengzaipai.util.zxing.ScannerActivity;
import butterknife.Bind;
import butterknife.ButterKnife;

public class BindDeviceActivity extends BaseActivity<BindDevicePresenter, BindDeviceModel> implements BindDeviceContract.View, View.OnClickListener{


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
    @Bind(R.id.iv_device_type)
    ImageView ivDeviceType;
    @Bind(R.id.tv_tip)
    TextView tvTip;
    @Bind(R.id.btn_detail)
    TextView btnDetail;
    @Bind(R.id.btn_ok)
    Button btnOk;
    @Bind(R.id.et_reg_code)
    EditText etRegCode;
    @Bind(R.id.btn_scan)
    ImageButton btnScan;
    @Bind(R.id.et_nickname)
    EditText etNickname;
    @Bind(R.id.relation)
    EditText relation;
    @Bind(R.id.rv_roles_choose)
    RecyclerView rvRolesChoose;
    @Bind(R.id.contentPanel)
    LinearLayout contentPanel;

    private GalleryAdapter mAdapter;
    private List<Integer> mDatas;
    private List<String> nameList;

    private final int SCANNIN_GREQUEST_CODE=0x001;
    String phone;

    static final int VOICE_REQUEST_CODE = 66;

    @Override
    public int getLayoutId() {
        return R.layout.login_frag_bind_device_add;
    }

    @Override
    public void initView() {


        Intent msg=getIntent();
        phone=msg.getStringExtra("phone");

        libBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnScan.setOnClickListener(this);
        btnOk.setOnClickListener(this);




        initDatas();

        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvRolesChoose.setLayoutManager(linearLayoutManager);
        //设置适配器
        mAdapter = new GalleryAdapter(this, mDatas,nameList);
        rvRolesChoose.setAdapter(mAdapter);

        mAdapter.setOnItemClickLitener(new GalleryAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                relation.setText(nameList.get(position));
            }
        });

    }


    private void initDatas()
    {
        mDatas = new ArrayList<Integer>(Arrays.asList(R.mipmap.icon_father,R.mipmap.icon_mother,R.mipmap.icon_other_boy,R.mipmap.icon_other_girl));
        nameList = new ArrayList<String>(Arrays.asList(getString(R.string.apply_father),getString(R.string.apply_mother),getString(R.string.apply_boy),getString(R.string.apply_girl)));

    }




    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel, this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.btn_scan:

                //判断是否开启摄像头权限
                if ((ContextCompat.checkSelfPermission(BindDeviceActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED))
                {
//                    Intent intent = new Intent(BindDeviceActivity.this, CaptureActivity.class);
//                    startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
                    startActivityForResult(new Intent(this, ScannerActivity.class), 1);
                } else{       //请求获取摄像头权限
                ActivityCompat.requestPermissions((Activity) BindDeviceActivity.this,
                        new String[]{Manifest.permission.CAMERA}, VOICE_REQUEST_CODE);
                }
                break;
            case R.id.btn_ok:
                if(etRegCode.getText().length()==0)
                    App.showText(getString(R.string.reg_code_null));
                else if(etNickname.getText().length()==0)
                    App.showText(getString(R.string.nickname_null));
                else if(relation.getText().length()==0)
                    App.showText(getString(R.string.relice_null));
                else
                    mPresenter.bunding(phone,etRegCode.getText().toString(),etNickname.getText().toString(),relation.getText().toString());
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        App.showText(requestCode+"-"+resultCode+"-"+data.getExtras());

        switch (requestCode) {
            case SCANNIN_GREQUEST_CODE:
                if(resultCode == RESULT_OK){
//                    App.showText("s");
                    Bundle bundle = data.getExtras();
                    String scanResult = bundle.getString("text");
//                    App.showText("s="+scanResult);
                    etRegCode.setText(scanResult);
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
        initBaseDialog();
    }

    @Override
    public void showDialog() {
        showBaseDialog();
    }

    @Override
    public void hideDialog() {
        hideBaseDialog();
    }

    @Override
    public void back() {
        finish();
    }

    @Override
    public void goMain() {
//        Intent.setFlag(FLAG_ACTIVITY_NO_HISTORY）
//        startActivity(new Intent(LoginActivity.this, ScannerActivity.class).setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP));
//        finish();




        Intent intent = new Intent(this,WatchTabActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    /**
     * 请求权限回调
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == VOICE_REQUEST_CODE) {
            if ((grantResults[0] == PackageManager.PERMISSION_GRANTED) ) {
//                Intent intent = new Intent(BindDeviceActivity.this, CaptureActivity.class);
//                startActivityForResult(intent, SCANNIN_GREQUEST_CODE);

                startActivityForResult(new Intent(this, ScannerActivity.class), 1);
            } else {
                App.showText(getString(R.string.auth_fail));
            }
        }
    }


    @Override
    public void suc() {
        App.showText(getString(R.string.tx_auth_suc));
    }

    @Override
    public void fail() {
        App.showText(getString(R.string.tip_fail));
    }

    public void wait_auth() {
        App.showText(getString(R.string.tx_auth_wait));
    }

    public void wait_did() {
        App.showText(getString(R.string.tx_auth_did));
    }
    public void nofind() {
        App.showText(getString(R.string.tip_no_find_watch));
    }
}
