package app.conqueror.com.zhengzaipai.app.Function;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import app.conqueror.com.zhengzaipai.App;
import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.WebActivity;
import app.conqueror.com.zhengzaipai.app.CardService.CardServiceActivity;
import app.conqueror.com.zhengzaipai.mainfragment.LockDevice.mirror.MonitorActivity.MonitorActivity;
import app.conqueror.com.zhengzaipai.app.SetFragment.SettingActivity;
import app.conqueror.com.zhengzaipai.mainfragment.LockDevice.mirror.Navi.NavActivity;
import app.conqueror.com.zhengzaipai.mainfragment.LockDevice.mirror.PoiRec.PoiRecActivity;
import app.conqueror.com.zhengzaipai.base.BaseActivity;
import app.conqueror.com.zhengzaipai.mainfragment.LockDevice.mirror.shop.ServiceShopActivity;
import app.conqueror.com.zhengzaipai.util.SpUtil;
import app.conqueror.com.zhengzaipai.util.Util;
import app.conqueror.com.zhengzaipai.wechat.Constants;
import butterknife.Bind;

public class FunctionActivity extends BaseActivity<FunctionPresenter, FunctionModel> implements FunctionContract.View {

    @Bind(R.id.type0)
    LinearLayout type0;
    @Bind(R.id.type3)
    LinearLayout type3;
    @Bind(R.id.typecard)
    LinearLayout typecard;
    private static final int THUMB_SIZE = 150;
    @Bind(R.id.type6)
    LinearLayout type6;
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
        int type;
//        did = bundle.getString("did");
//         type = bundle.getInt("cut");

        did="1330105050121288";
        type=3;

        if (type == 3)
            type3.setVisibility(View.VISIBLE);
        else if (type == -1)
            typecard.setVisibility(View.VISIBLE);
        else if (type == 0)
            type0.setVisibility(View.VISIBLE);
        else if (type == 6)
            type6.setVisibility(View.VISIBLE);


        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
    }


    public void btn_listen(View view) {
        switch (view.getTag().toString()) {

            case "recorderset":
                Intent recorderSetIntent = new Intent(FunctionActivity.this, SettingActivity.class);
                //用Bundle携带数据
                Bundle recorderSetBundle = new Bundle();
                //传递name参数为tinyphp
                recorderSetBundle.putString("settype", "recorderset");
                recorderSetIntent.putExtras(recorderSetBundle);
                startActivity(recorderSetIntent);
                break;
            case "cardabout":
                Intent cardAboutIntent = new Intent(FunctionActivity.this, WebActivity.class);
                //用Bundle携带数据
                Bundle cardAboutBundle = new Bundle();
                //传递name参数为tinyphp
                cardAboutBundle.putString("url", "http://sim.conqueror.cn/CiLearn/public/images/about.png");
                cardAboutIntent.putExtras(cardAboutBundle);
                startActivity(cardAboutIntent);
                break;
            case "deviceset":
                Intent settingIntent = new Intent(FunctionActivity.this, SettingActivity.class);
                //用Bundle携带数据
                Bundle settingBundle = new Bundle();
                //传递name参数为tinyphp
                settingBundle.putString("settype", "deviceset");
                settingIntent.putExtras(settingBundle);
                startActivity(settingIntent);
                break;
            case "speakmsg":
                LayoutInflater factory = LayoutInflater.from(FunctionActivity.this);//提示框
                final View dialog_view = factory.inflate(R.layout.editbox_layout, null);//这里必须是final的
                final EditText edit = (EditText) dialog_view.findViewById(R.id.editText);//获得输入框对象
                new AlertDialog.Builder(FunctionActivity.this)
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
                Intent cardSearchIntent = new Intent(FunctionActivity.this, WebActivity.class);
                //用Bundle携带数据
                Bundle cardSearchBundle = new Bundle();
                //传递name参数为tinyphp
                cardSearchBundle.putString("url", "http://sim.conqueror.cn/CiLearn/index.php/App?action=search&iccid=" + did);
                cardSearchIntent.putExtras(cardSearchBundle);
                startActivity(cardSearchIntent);
                break;
            case "cardrec":
                Intent cardRecIntent = new Intent(FunctionActivity.this, WebActivity.class);
                //用Bundle携带数据
                Bundle cardRecBundle = new Bundle();
                //传递name参数为tinyphp
                cardRecBundle.putString("url", "http://sim.conqueror.cn/CiLearn/index.php/App?action=flow&iccid=" + did);
                cardRecIntent.putExtras(cardRecBundle);
                startActivity(cardRecIntent);
                break;

            case "cardauth":
                Intent cardAuthIntent = new Intent(FunctionActivity.this, WebActivity.class);
                //用Bundle携带数据
                Bundle cardAuthBundle = new Bundle();
                //传递name参数为tinyphp
                cardAuthBundle.putString("url", "http://sim.conqueror.cn/CiLearn/index.php/UpCard?weid=" + SpUtil.getAppid());
                cardAuthIntent.putExtras(cardAuthBundle);
                startActivity(cardAuthIntent);
                break;
            case "cardservice":
                startActivity(new Intent(FunctionActivity.this, CardServiceActivity.class));
                break;

            case "buyservice":

                /**
                 * url是测试的网站，在app内部打开页面是基于webview打开的，demo中的webview是H5PayDemoActivity，
                 * demo中拦截url进行支付的逻辑是在H5PayDemoActivity中shouldOverrideUrlLoading方法实现，
                 * 商户可以根据自己的需求来实现
                 */

//                20170814 32929
//                2017 814 1328 14 750
//                String url = "http://sim.conqueror.cn/alipay/index.php/PayService?type=alipay&subject=测试&total_amount=0.01&body=购买测试商品0.01元";
//                String url = "http://sim.conqueror.cn/alipay/";
//                String url = "http://m.taobao.com";
//                String url = "http://sim.conqueror.cn/alipay/index.php/Shop?cut=4&did=12121212";

                Intent intent = new Intent(this, ServiceShopActivity.class);
                Bundle extras = new Bundle();
                /**
                 * url是测试的网站，在app内部打开页面是基于webview打开的，demo中的webview是H5PayDemoActivity，
                 * demo中拦截url进行支付的逻辑是在H5PayDemoActivity中shouldOverrideUrlLoading方法实现，
                 * 商户可以根据自己的需求来实现
                 */
                String url = "http://sim.conqueror.cn/alipay/index.php/Shop?cut=4&did=12121212";
                // url可以是一号店或者淘宝等第三方的购物wap站点，在该网站的支付过程中，支付宝sdk完成拦截支付
                extras.putString("url", url);
                intent.putExtras(extras);
                startActivity(intent);



//                String html = "<script>window.location.href='" + orderUrl + "';</script>";
//                webView.loadDataWithBaseURL(SDKConfigs.REFERER, html, "text/html", "utf-8", null);



                break;

            case "carmap":
                Intent aboutIntent = new Intent(FunctionActivity.this, WebActivity.class);
                //用Bundle携带数据
                Bundle aboutBundle = new Bundle();
                //传递name参数为tinyphp
                aboutBundle.putString("url", "http://wechat.conqueror.cn/jsp/carlocationlastapp.jsp?did=1330105050121288");
                aboutIntent.putExtras(aboutBundle);
                startActivity(aboutIntent);
                break;

            case "monitor":
                startActivity(new Intent(FunctionActivity.this, MonitorActivity.class));
                break;

            case "poirec":
                startActivity(new Intent(FunctionActivity.this, PoiRecActivity.class));
                break;
            case "shutdown":
                mPresenter.shutdown(SpUtil.getXGToken(), "123");
                break;
            case "navmap":
                startActivity(new Intent(FunctionActivity.this, NavActivity.class));
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
            case "wificamera":
                Intent wificameraIntent = new Intent(FunctionActivity.this, WebActivity.class);
                //用Bundle携带数据
                Bundle wificameraBundle = new Bundle();
                //传递name参数为tinyphp
                wificameraBundle.putString("url", "file:///android_asset/urlplay.html?url=http://192.168.43.81:8080");
                wificameraIntent.putExtras(wificameraBundle);
                startActivity(wificameraIntent);
                break;

        }
    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
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
    protected void onResume() {
        initDialog();
        super.onResume();
    }


}
