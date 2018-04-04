package app.conqueror.com.zhengzaipai.mainfragment.LockDevice;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.bugly.crashreport.CrashReport;

import java.util.ArrayList;
import java.util.List;

import app.conqueror.com.zhengzaipai.App;
import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.app.Function.FunctionActivity;
import app.conqueror.com.zhengzaipai.base.BaseActivity;
import app.conqueror.com.zhengzaipai.entity.Device;
import app.conqueror.com.zhengzaipai.mainfragment.LockDevice.mirror.MirrorTab;
import app.conqueror.com.zhengzaipai.util.CheckUtil;
import app.conqueror.com.zhengzaipai.util.SpUtil;
import app.conqueror.com.zhengzaipai.view.pop.PopupMenuUtil;
import butterknife.Bind;

public class LockDeviceActivity extends BaseActivity<LockPresenter, LockModel> implements LockContract.View {


    @Bind(R.id.lv_device)
    ListView lvDevice;
    @Bind(R.id.btn_lock)
    Button btnLock;
    @Bind(R.id.pand_lock)
    LinearLayout pandLock;
    @Bind(R.id.pand_list)
    LinearLayout pandList;
    @Bind(R.id.device_id)
    EditText deviceId;
    @Bind(R.id.device_phone)
    EditText devicePhone;
    @Bind(R.id.device_name)
    EditText deviceName;
    @Bind(R.id.device_code)
    EditText deviceCode;
    @Bind(R.id.code_tr)
    TableRow codeTr;
    @Bind(R.id.device_id_tv)
    TextView deviceIdTv;
    @Bind(R.id.device_name_tv)
    TextView deviceNameTv;
    @Bind(R.id.sacn_btn)
    ImageView sacnBtn;


    private String type;
    private final int SCANNIN_GREQUEST_CODE=0x001;
    private long exitTime = 0;
    @Override
    public void initDeviceList(List<Device> devicelist) {
        if (devicelist.size() > 0) {

            pandLock.setVisibility(View.GONE);
            pandList.setVisibility(View.VISIBLE);
            List<String> mListStr = new ArrayList<String>();
            for (int i = 0; i < devicelist.size(); i++)
                if (type.equals("lockcard"))
                    mListStr.add(devicelist.get(i).cardid);
                else
                    mListStr.add(devicelist.get(i).parameterid);
            lvDevice.setAdapter(new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, mListStr));
            lvDevice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    switch (devicelist.get(i).cut) {
                        case 0:
                        case 3:
                            SpUtil.setDevice(devicelist.get(i));

                            Intent function_intent = new Intent(LockDeviceActivity.this, MirrorTab.class);
                            //用Bundle携带数据
                            Bundle bundle = new Bundle();
                            //传递name参数为tinyphp
                            bundle.putInt("cut", devicelist.get(i).cut);
                            bundle.putString("did", devicelist.get(i).parameterid);
                            function_intent.putExtras(bundle);
                            startActivity(function_intent);
                            break;
                        case 6:
//                            startActivity(new Intent(LockDeviceActivity.this, FunctionActivity.class));
                            Intent recorder_intent = new Intent(LockDeviceActivity.this, FunctionActivity.class);
                            //用Bundle携带数据
                            Bundle recorderbundle = new Bundle();
                            //传递name参数为tinyphp
                            recorderbundle.putInt("cut", devicelist.get(i).cut);
                            recorderbundle.putString("did", devicelist.get(i).parameterid);
                            recorder_intent.putExtras(recorderbundle);
                            startActivity(recorder_intent);
                            break;
                        case -1:

                            SpUtil.setCard(devicelist.get(i));

                            Intent card_intent = new Intent(LockDeviceActivity.this, FunctionActivity.class);
                            //用Bundle携带数据
                            Bundle card_bundle = new Bundle();
                            //传递name参数为tinyphp
                            card_bundle.putInt("cut", devicelist.get(i).cut);
                            card_bundle.putString("did", devicelist.get(i).cardid);
                            card_intent.putExtras(card_bundle);

                            startActivity(card_intent);
                            break;
                    }


                }
            });

            //只有一台设备进行跳转
//            if (devicelist.size() == 1) {
//                Intent function_intent = new Intent(LockDeviceActivity.this, FunctionActivity.class);
//                //用Bundle携带数据
//                Bundle bundle = new Bundle();
//                //传递name参数为tinyphp
//                bundle.putInt("cut", devicelist.get(0).cut);
//                if (type.equals("lockcard"))
//                //传递name参数为tinyphp
//                {
//                    SpUtil.setCard(devicelist.get(0));
//                    bundle.putString("did", devicelist.get(0).cardid);
//                } else {
//                    SpUtil.setDevice(devicelist.get(0));
//                    bundle.putString("did", devicelist.get(0).parameterid);
//                }
//                function_intent.putExtras(bundle);
//                startActivity(function_intent);
//            }


        } else {
            initLock();
        }


    }

    @Override
    public void initLock() {
        pandLock.setVisibility(View.VISIBLE);
        pandList.setVisibility(View.GONE);
    }

    public void getDeviceCode(View view) {

        CrashReport.testJavaCrash();
//        if (!CheckUtil.isMobileNO(devicePhone.getText().toString()))
//            App.showText("手机号不正确");
//        else
//            mPresenter.getDeviceCode(devicePhone.getText().toString());
    }

    public void LockDevice(View view) {
        if (deviceId.getText().toString().equals("")) {
            App.showText("设备ID不能为空");
        } else if (!CheckUtil.isMobileNO(devicePhone.getText().toString())) {
            App.showText("手机号不正确");
        } else if (deviceName.getText().toString().equals("")) {
            App.showText("名字不能为空");
        } else if (type.equals("lockcard") && deviceCode.getText().toString().equals("")) {
            App.showText("验证码不能为空");
        } else if (type.equals("lockcard") && !deviceCode.getText().toString().equals(SpUtil.getCode())) {
            App.showText("验证码不正确");
        } else {
            mPresenter.LockDevice(SpUtil.getAppid(), deviceId.getText().toString(), devicePhone.getText().toString(), deviceName.getText().toString());
        }
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_lockdevice;
    }

    @Override
    public void initView() {


//        Bundle bundle = getIntent().getExtras();
        type = "type";
        //添加设备的按钮
//        bar_right.setVisibility(View.VISIBLE);
//        bar_left.setVisibility(View.VISIBLE);

    /*    bar_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LockDeviceActivity.this.finish();
            }
        });


        bar_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pandList.getVisibility() == View.VISIBLE) {
                    if (type.equals("lockcard")) {
                        codeTr.setVisibility(View.VISIBLE);
                        deviceIdTv.setText(getString(R.string.card_iccid));
                        deviceNameTv.setText(getString(R.string.card_name));

                    } else {
                        codeTr.setVisibility(View.INVISIBLE);
                        deviceIdTv.setText(getString(R.string.device_id));
                        deviceNameTv.setText(getString(R.string.device_name));
                    }
                    //开始绑定
                    pandLock.setVisibility(View.VISIBLE);
                    pandList.setVisibility(View.GONE);
                } else {
                    //开始列表
                    pandLock.setVisibility(View.GONE);
                    pandList.setVisibility(View.VISIBLE);
                }
            }
        });*/
        sacnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(LockDeviceActivity.this, CaptureActivity.class);
//                startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
            }
        });

    }


    @Override
    public String getType() {
        return type;
    }


    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel, this);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SCANNIN_GREQUEST_CODE:
                if(resultCode == RESULT_OK){
                    Bundle bundle = data.getExtras();
                    String scanResult = bundle.getString("result");
                    App.showText(scanResult);
                }
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            // 当popupWindow 正在展示的时候 按下返回键 关闭popupWindow 否则关闭activity
            if (PopupMenuUtil.getInstance()._isShowing()) {
                PopupMenuUtil.getInstance()._rlClickAction();
            }
            // 当popupWindow 正在展示的时候 按下返回键 关闭popupWindow 否则关闭activity
            else if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
