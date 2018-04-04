package app.conqueror.com.zhengzaipai.mainfragment.LockDevice.mirror.shop;


import app.conqueror.com.zhengzaipai.base.BaseActivity;

public class ServiceShopActivity extends BaseActivity {
	@Override
	public int getLayoutId() {
		return 0;
	}

	@Override
	public void initView() {

	}

	@Override
	public void initPresenter() {

	}

//	private WebView mWebView;
//
//
//	@Override
//	public void onBackPressed() {
//		if (mWebView.canGoBack()) {
//			mWebView.goBack();
//		} else {
//			finish();
//		}
//	}
//
//	@Override
//	public void finish() {
//		super.finish();
//	}
//
//	@Override
//	public void onConfigurationChanged(Configuration newConfig) {
//		super.onConfigurationChanged(newConfig);
//	}
//
//	private class MyWebViewClient extends WebViewClient {
//
//		@Override
//		public boolean shouldOverrideUrlLoading(final WebView view, String url) {
//			if (!(url.startsWith("http") || url.startsWith("https"))) {
//				return true;
//			}
//
//			final PayTask task = new PayTask(ServiceShopActivity.this);
//			final String ex = task.fetchOrderInfoFromH5PayUrl(url);
//			if (!TextUtils.isEmpty(ex)) {
//				System.out.println("paytask:::::" + url);
//				new Thread(new Runnable() {
//					public void run() {
//						System.out.println("payTask:::" + ex);
//						final H5PayResultModel result = task.h5Pay(ex, true);
//						if (!TextUtils.isEmpty(result.getReturnUrl())) {
//							ServiceShopActivity.this.runOnUiThread(new Runnable() {
//
//								@Override
//								public void run() {
//									view.loadUrl(result.getReturnUrl());
//								}
//							});
//						}
//					}
//				}).start();
//			} else {
//				view.loadUrl(url);
//			}
//			return true;
//		}
//	}
//
//	@Override
//	protected void onDestroy() {
//		super.onDestroy();
//		if (mWebView != null) {
//			mWebView.removeAllViews();
//			try {
//				mWebView.destroy();
//			} catch (Throwable t) {
//			}
//			mWebView = null;
//		}
//	}
//
//	@Override
//	public int getLayoutId() {
//		return R.layout.activity_web;
//	}
//
//	@Override
//	public void initView() {
//
//
///*		final String url = "http://wechat.conqueror.cn/TopayServletForKF?userId=1&orderNo=2&money=3&code=4&did=5&type=app";
//		mWebView= (WebView) findViewById(R.id.webView1);
//		Map<String,String> extraHeaders = new HashMap<String, String>();
//		extraHeaders.put("Referer", "wechat.conqueror.cn");
//		mWebView.loadUrl(url, extraHeaders);*/
//
//
//	Bundle extras = null;
//		try {
//			extras = getIntent().getExtras();
//		} catch (Exception e) {
//			finish();
//			return;
//		}
//		if (extras == null) {
//			finish();
//			return;
//		}
//		String url = null;
//		try {
//			url = extras.getString("url");
//		} catch (Exception e) {
//			finish();
//			return;
//		}
//		if (TextUtils.isEmpty(url)) {
//			// 测试H5支付，必须设置要打开的url网站
//			new AlertDialog.Builder(ServiceShopActivity.this).setTitle("警告")
//					.setMessage("必须配置需要打开的url 站点，请在PayDemoActivity类的h5Pay中配置")
//					.setPositiveButton("确定", new OnClickListener() {
//
//						@Override
//						public void onClick(DialogInterface arg0, int arg1) {
//							finish();
//						}
//					}).show();
//
//		}
////		super.requestWindowFeature(Window.FEATURE_NO_TITLE);
////		LinearLayout layout = new LinearLayout(getApplicationContext());
////		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
////		layout.setOrientation(LinearLayout.VERTICAL);
////		setContentView(layout, params);
//
////		mWebView = new WebView(getApplicationContext());
//		mWebView= (WebView) findViewById(R.id.webView1);
////		params.weight = 1;
////		mWebView.setVisibility(View.VISIBLE);
////		layout.addView(mWebView, params);
//
//		WebSettings settings = mWebView.getSettings();
//		settings.setRenderPriority(RenderPriority.HIGH);
//		settings.setSupportMultipleWindows(true);
//		settings.setJavaScriptEnabled(true);
//		settings.setSavePassword(false);
//		settings.setJavaScriptCanOpenWindowsAutomatically(true);
//		settings.setMinimumFontSize(settings.getMinimumFontSize() + 8);
//		settings.setAllowFileAccess(false);
//		settings.setTextSize(WebSettings.TextSize.NORMAL);
//		mWebView.setVerticalScrollbarOverlay(true);
//		mWebView.setWebViewClient(new MyWebViewClient());
//
//		mWebView.setWebChromeClient(new WebChromeClient(){
//
////			*//**
////			 * 覆盖默认的window.alert展示界面，避免title里显示为“：来自file:////”
////			 *//*
//			@Override
//			public boolean onJsAlert(WebView view, String url, String message,
//									 JsResult result) {
//				final AlertDialog.Builder builder = new AlertDialog.Builder(ServiceShopActivity.this);
//
//				builder.setTitle("对话框")
//						.setMessage(message)
//						.setPositiveButton("确定", null);
//
//				// 不需要绑定按键事件
//				// 屏蔽keycode等于84之类的按键
//				builder.setOnKeyListener(new DialogInterface.OnKeyListener() {
//					public boolean onKey(DialogInterface dialog, int keyCode,KeyEvent event) {
//						Log.v("onJsAlert", "keyCode==" + keyCode + "event="+ event);
//						return true;
//					}
//				});
//				// 禁止响应按back键的事件
//				builder.setCancelable(false);
//				AlertDialog dialog = builder.create();
//				dialog.show();
//				result.confirm();// 因为没有绑定事件，需要强行confirm,否则页面会变黑显示不了内容。
//				return true;
//				// return super.onJsAlert(view, url, message, result);
//			}
//
//
//
//
//		});//让WebView支持弹出框
//
//		mWebView.loadUrl(url);
//	}
//
//	@Override
//	public void initPresenter() {
//
//	}
}
