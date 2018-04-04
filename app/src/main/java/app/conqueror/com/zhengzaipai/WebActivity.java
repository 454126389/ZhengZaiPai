package app.conqueror.com.zhengzaipai;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import app.conqueror.com.zhengzaipai.view.client.MyWebChromeClient;
import butterknife.Bind;
import butterknife.ButterKnife;

import static app.conqueror.com.zhengzaipai.R.id.webView1;

public class WebActivity extends AppCompatActivity {


    @Bind(webView1)
    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        Bundle bundle = this.getIntent().getExtras();
        //接收name值
        String url = bundle.getString("url");

        initView(url);
    }


    public void initView(String url) {



        mWebView.getSettings().setJavaScriptEnabled(true);
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



        /*
mWebView.addJavascriptInterface(new Object() {
            public void clickOnAndroid() {
                mHandler.post(new Runnable() {
                    public void run() {
                        mWebView.loadUrl("javascript:wave()");
                    }
                });
            }
        }, "demo");*/
//        mWebView.loadUrl("file:///android_asset/shop.html");
        mWebView.loadUrl(url);
    }

    // 如果不做任何处理，浏览网页，点击系统“Back”键，整个Browser会调用finish()而结束自身，
    // 如果希望浏览的网 页回退而不是推出浏览器，需要在当前Activity中处理并消费掉该Back事件。
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }else
        {
            finish();
        }
        return false;
    }

//        webView1.getSettings().setBuiltInZoomControls(false);
//        webView1.setVerticalScrollBarEnabled(false);
//        webView1.setHorizontalScrollBarEnabled(false);
//        webView1.getSettings().setBlockNetworkImage(false);
//        webView1.getSettings().setJavaScriptEnabled(true);
//
//        webView1.loadUrl(url); CookieManager.getInstance().setAcceptCookie(true);



//       webView1.loadUrl(url);
//        // 设置WebView属性，能够执行JavaScript脚本
//        webView1.getSettings().setJavaScriptEnabled(true);
//        // 设置可以支持缩放
//        webView1.getSettings().setSupportZoom(true);
        // 设置出现缩放工具
//        mWebview.getSettings().setBuiltInZoomControls(true);
        // 为图片添加放大缩小功能
//        mWebview.getSettings().setUseWideViewPort(true);

//        webView1.setInitialScale(70);   //100代表不缩放


//        webView1.getSettings().setJavaScriptEnabled(true);
//        webView1.getSettings().setDomStorageEnabled(true);
//        webView1.loadUrl(url);
//        webView1.setWebViewClient(new WebViewClient(){
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view,String url){
//                view.loadUrl(url);
//                return true;
//            }
//        });

//        LinearLayout layout = new LinearLayout(getApplicationContext());
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
//        layout.setOrientation(LinearLayout.VERTICAL);
//        setContentView(layout, params);
//
//        webView1 = new WebView(getApplicationContext());
//        params.weight = 1;
//        webView1.setVisibility(View.VISIBLE);
//        layout.addView(webView1, params);
//
//        WebSettings settings = webView1.getSettings();
//        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
//        settings.setSupportMultipleWindows(true);
//        settings.setJavaScriptEnabled(true);
//        settings.setSavePassword(false);
//        settings.setJavaScriptCanOpenWindowsAutomatically(true);
//        settings.setMinimumFontSize(settings.getMinimumFontSize() + 8);
//        settings.setAllowFileAccess(false);
//        settings.setTextSize(WebSettings.TextSize.NORMAL);
//        webView1.setVerticalScrollbarOverlay(true);
//        webView1.setWebViewClient(new MyWebViewClient());
//        webView1.loadUrl(url);


//    private class MyWebViewClient extends WebViewClient {
//
//        @Override
//        public boolean shouldOverrideUrlLoading(final WebView view, String url) {
//            if (!(url.startsWith("http") || url.startsWith("https"))) {
//                return true;
//            }
//
//            final PayTask task = new PayTask(WebActivity.this);
//            final String ex = task.fetchOrderInfoFromH5PayUrl(url);
//            if (!TextUtils.isEmpty(ex)) {
//                System.out.println("paytask:::::" + url);
//                new Thread(new Runnable() {
//                    public void run() {
//                        System.out.println("payTask:::" + ex);
//                        final H5PayResultModel result = task.h5Pay(ex, true);
//                        if (!TextUtils.isEmpty(result.getReturnUrl())) {
//                            WebActivity.this.runOnUiThread(new Runnable() {
//
//                                @Override
//                                public void run() {
//                                    view.loadUrl(result.getReturnUrl());
//                                }
//                            });
//                        }
//                    }
//                }).start();
//            } else {
//                view.loadUrl(url);
//            }
//            return true;
//        }
//    }

    // 内部类
    public class MyWebViewClient extends WebViewClient {
        // 如果页面中链接，如果希望点击链接继续在当前browser中响应，
        // 而不是新开Android的系统browser中响应该链接，必须覆盖 webview的WebViewClient对象。
        public boolean shouldOverviewUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        public void onPageStarted(WebView view, String url, Bitmap favicon) {
//            showProgress();
        }

        public void onPageFinished(WebView view, String url) {
//            closeProgress();
        }

        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
//            closeProgress();
        }
    }

}
