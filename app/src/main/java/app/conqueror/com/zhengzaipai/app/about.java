package app.conqueror.com.zhengzaipai.app;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.tencent.android.tpush.XGPushConfig;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import app.conqueror.com.zhengzaipai.App;
import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.WebActivity;
import app.conqueror.com.zhengzaipai.app.Function.FunctionContract;
import app.conqueror.com.zhengzaipai.app.Function.FunctionModel;
import app.conqueror.com.zhengzaipai.app.Function.FunctionPresenter;
import app.conqueror.com.zhengzaipai.mainfragment.LockDevice.mirror.MonitorActivity.MonitorActivity;
import app.conqueror.com.zhengzaipai.mainfragment.LockDevice.mirror.Navi.NavActivity;
import app.conqueror.com.zhengzaipai.mainfragment.LockDevice.mirror.PoiRec.PoiRecActivity;
import app.conqueror.com.zhengzaipai.app.SetFragment.SettingActivity;
import app.conqueror.com.zhengzaipai.base.BaseActivity;
import app.conqueror.com.zhengzaipai.util.Util;
import app.conqueror.com.zhengzaipai.wechat.Constants;
import butterknife.Bind;

public class about extends BaseActivity<FunctionPresenter, FunctionModel> implements FunctionContract.View {

    @Bind(R.id.type0)
    LinearLayout type0;
    @Bind(R.id.type3)
    LinearLayout type3;
    @Bind(R.id.typecard)
    LinearLayout typecard;
    private static final int THUMB_SIZE = 150;
    private int mTargetScene = SendMessageToWX.Req.WXSceneSession;
    private IWXAPI api;

    String did;

    @Override
    public int getLayoutId() {
        return R.layout.activity_function;
    }

    @Override
    public void initView() {
        Bundle bundle = this.getIntent().getExtras();
        //接收name值
        did = bundle.getString("did");
        int type = bundle.getInt("cut");
        if (type == 3)
            type3.setVisibility(View.VISIBLE);
        else if (type==-1)
            typecard.setVisibility(View.VISIBLE);
        else if (type==0)
            type0.setVisibility(View.VISIBLE);



        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
    }


    public void btn_listen(View view)
    {
        switch (view.getTag().toString())
        {

            case "cardabout":
                Intent cardAboutIntent =new Intent(about.this,WebActivity.class);
                //用Bundle携带数据
                Bundle cardAboutBundle=new Bundle();
                //传递name参数为tinyphp
                cardAboutBundle.putString("url", "http://sim.conqueror.cn/CiLearn/public/images/about.png");
                cardAboutIntent.putExtras(cardAboutBundle);
                startActivity(cardAboutIntent);
                break;
            case "deviceset":
                Intent settingIntent =new Intent(about.this,SettingActivity.class);
                //用Bundle携带数据
                Bundle settingBundle=new Bundle();
                //传递name参数为tinyphp
                settingBundle.putString("settype", "deviceset");
                settingIntent.putExtras(settingBundle);
                startActivity(settingIntent);
                break;
            case "speakmsg":
                LayoutInflater factory = LayoutInflater.from(about.this);//提示框
                final View dialog_view = factory.inflate(R.layout.editbox_layout, null);//这里必须是final的
                final EditText edit = (EditText) dialog_view.findViewById(R.id.editText);//获得输入框对象
                new android.support.v7.app.AlertDialog.Builder(about.this)
                        .setTitle("请输入要播报的语句")//提示框标题
                        .setView(dialog_view)
                        .setPositiveButton("确定",//提示框的两个按钮
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {

                                    }
                                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create().show();
                break;

            case "cardsearch":
                Intent cardSearchIntent =new Intent(about.this,WebActivity.class);
                //用Bundle携带数据
                Bundle cardSearchBundle=new Bundle();
                //传递name参数为tinyphp
                cardSearchBundle.putString("url", "http://139.224.221.22:8181/sim/getMonthToDateDataUsage?iccid="+did);
                cardSearchIntent.putExtras(cardSearchBundle);
                startActivity(cardSearchIntent);
                break;
            case "cardrec":
                Intent cardRecIntent =new Intent(about.this,WebActivity.class);
                //用Bundle携带数据
                Bundle cardRecBundle=new Bundle();
                //传递name参数为tinyphp
                cardRecBundle.putString("url", "http://139.224.221.22:8181/sim/getSimPay?iccid="+did);
                cardRecIntent.putExtras(cardRecBundle);
                startActivity(cardRecIntent);
                break;

            case "carmap":
                Intent aboutIntent =new Intent(about.this,WebActivity.class);
                //用Bundle携带数据
                Bundle aboutBundle=new Bundle();
                //传递name参数为tinyphp
                aboutBundle.putString("url", "http://wechat.conqueror.cn/jsp/carlocationlastapp.jsp?did=1330105050121288");
                aboutIntent.putExtras(aboutBundle);
                startActivity(aboutIntent);
                break;

            case "monitor":
                startActivity(new Intent(about.this, MonitorActivity.class));
                break;

            case "poirec":
                startActivity(new Intent(about.this, PoiRecActivity.class));
                break;
            case "shutdown":
               mPresenter.shutdown(XGPushConfig.getToken(about.this),"123");
                break;
            case "navmap":
                startActivity(new Intent(about.this, NavActivity.class));
                break;
            case "pickup":
                WXWebpageObject webpage = new WXWebpageObject();
                webpage.webpageUrl = "http://wechat.conqueror.cn/jsp/appsendway.jsp?did=1330105050121288&from=singlemessage&isappinstalled=0";
                WXMediaMessage msg = new WXMediaMessage(webpage);
                msg.title = "请开启位置共享，我来接您";
                msg.description = "请保持手机网络正常，允许获取定位";
                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
                Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, THUMB_SIZE, THUMB_SIZE, true);
                bmp.recycle();
                msg.thumbData = Util.bmpToByteArray(thumbBmp, true);

                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.transaction = buildTransaction("webpage");
                req.message = msg;
                req.scene = mTargetScene;
                api.sendReq(req);
                break;




        }
    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }


    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel,this);
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
    public void showMsg(String msg) {
        App.showText(msg);
    }

    @Override
    public void initDialog() {
        initBaseDialog();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
