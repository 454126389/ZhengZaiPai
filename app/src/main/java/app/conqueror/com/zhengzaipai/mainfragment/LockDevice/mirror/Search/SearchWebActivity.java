package app.conqueror.com.zhengzaipai.mainfragment.LockDevice.mirror.Search;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.CookieManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import app.conqueror.com.zhengzaipai.MainActivity;
import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.base.BaseActivity;
import app.conqueror.com.zhengzaipai.util.NetWorkUtil;
import app.conqueror.com.zhengzaipai.view.client.MyWebChromeClient;
import app.conqueror.com.zhengzaipai.view.pop.PopupMenuUtil;
import butterknife.Bind;
import butterknife.ButterKnife;

import static app.conqueror.com.zhengzaipai.R.id.webView1;

public class SearchWebActivity extends BaseActivity {


    @Bind(webView1)
    WebView mWebView;



    @Override
    public int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    public void initView() {
        String url="http://wechat.conqueror.cn/jsp/carlocationlastapp.jsp?did=1330105050121288";
        initView(url);


    }

    @Override
    public void initPresenter() {

    }

    // JS回调Android
    public class JSCallBack {
        @JavascriptInterface
        public void jsCallBack(String orderInfo) {
            // orderInfo是JS传入的参数
            Toast.makeText(SearchWebActivity.this, "JS调用Android", Toast.LENGTH_SHORT).show();
        }
    }

    // Android回调JS
    private void callJSPayResult(String payResult) {
        mWebView.loadUrl("javascript:alipayCallBack('" + payResult + "')");
    }


    public void initView(String url) {


        mWebView.requestFocus();
//        mWebView.setWebViewClient(new WebViewClient())
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 如下方案可在非微信内部WebView的H5页面中调出微信支付
                if(url.startsWith("weixin://wap/pay?")) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);

                    return true;
                }
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // TODO Auto-generated method stub
                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode,
                                      String description, String failingUrl) {
                // TODO Auto-generated method stub
                super.onReceivedError(view, errorCode, description, failingUrl);
            }

        });
        mWebView.setWebChromeClient(new MyWebChromeClient());//让WebView支持弹出框


//        if (NetWorkUtil.isNetConnected(SearchWebActivity.this)) {
//            mWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
//        } else {
//            mWebView.getSettings().setCacheMode(
//                    WebSettings.LOAD_CACHE_ELSE_NETWORK);
//        }

        mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);


        // webView.getSettings().setBlockNetworkImage(true);// 把图片加载放在最后来加载渲染
        mWebView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        mWebView.getSettings().setSupportMultipleWindows(true);
        mWebView.getSettings().setJavaScriptEnabled(true);
        // 开启 DOM storage API 功能
//        mWebView.getSettings().setDomStorageEnabled(true);


        // 设置可以支持缩放
        mWebView.getSettings().setSupportZoom(true);
//         设置出现缩放工具
        mWebView.getSettings().setBuiltInZoomControls(true);
//         为图片添加放大缩小功能
        mWebView.getSettings().setUseWideViewPort(true);

        mWebView.setInitialScale(70);   //100代表不缩放

        // 开启 Application Caches 功能
        mWebView.getSettings().setAppCacheEnabled(true);

//        // 不使用缓存
//        mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        // 支持JavaScript
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new WebViewClient());
        // JS回调Android
        mWebView.addJavascriptInterface(new JSCallBack(), "JSCallBack");


        onLoad(url);


//        mWebView.loadUrl();
    }

    // 如果不做任何处理，浏览网页，点击系统“Back”键，整个Browser会调用finish()而结束自身，
    // 如果希望浏览的网 页回退而不是推出浏览器，需要在当前Activity中处理并消费掉该Back事件。
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (PopupMenuUtil.getInstance()._isShowing()) {
                PopupMenuUtil.getInstance()._rlClickAction();
            } else  if((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
                mWebView.goBack();
                return true;
            }else
            {
                finish();
            }
            return true;
        }

//        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
//            mWebView.goBack();
//            return true;
//        }else
//        {
//            finish();
//        }
        return false;
    }

    public void onLoad(String url) {

        try {
            mWebView.setWebViewClient(new WebViewClient() {

                @Override
                public void onLoadResource(WebView view, String url) {

                    Log.i("tag", "onLoadResource url=" + url); // 开始加载
                    super.onLoadResource(view, url);
                }

                @Override
                public boolean shouldOverrideUrlLoading(WebView webview,
                                                        String url) {

                    Log.i("tag", "intercept url=" + url);
                    // 重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
                    webview.loadUrl(url);

                    return true;
                }

                @Override
                public void onPageFinished(WebView view, String url) {

                    String title = view.getTitle(); // 得到网页标题

                    Log.e("tag", "onPageFinished WebView title=" + title);

                }

                @Override
                public void onReceivedError(WebView view, int errorCode,
                                            String description, String failingUrl) {

                    Toast.makeText(getApplicationContext(), "加载错误",
                            Toast.LENGTH_LONG).show();
                }
            });
            mWebView.loadUrl(url);
        } catch (Exception e) {
            return;
        }
    }




}
