package app.conqueror.com.zhengzaipai.view.pop.popmore;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.mainfragment.LockDevice.mirror.Navi.NavActivity;
import app.conqueror.com.zhengzaipai.mainfragment.LockDevice.mirror.shop.ServiceShopActivity;
import app.conqueror.com.zhengzaipai.util.Util;
import app.conqueror.com.zhengzaipai.wechat.Constants;


public class MoreWindow extends PopupWindow implements OnClickListener {

	private String TAG = MoreWindow.class.getSimpleName();
	Activity mContext;
	private int mWidth;
	private int mHeight;
	private int statusBarHeight ;
	private Bitmap mBitmap= null;
	private Bitmap overlay = null;
	
	private Handler mHandler = new Handler();


	private static final int THUMB_SIZE = 150;
	private IWXAPI api;
	private int mTargetScene = SendMessageToWX.Req.WXSceneSession;

	public MoreWindow(Activity context) {
		mContext = context;
	}

	public void init() {

		api = WXAPIFactory.createWXAPI(mContext, Constants.APP_ID);

		Rect frame = new Rect();
		mContext.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
		statusBarHeight = frame.top;
		DisplayMetrics metrics = new DisplayMetrics();
		mContext.getWindowManager().getDefaultDisplay()
				.getMetrics(metrics);
		mWidth = metrics.widthPixels;
		mHeight = metrics.heightPixels;
		
		setWidth(mWidth);
		setHeight(mHeight);
	}
	
	private Bitmap blur() {
		if (null != overlay) {
			return overlay;
		}
		long startMs = System.currentTimeMillis();

		View view = mContext.getWindow().getDecorView();
		view.setDrawingCacheEnabled(true);
		view.buildDrawingCache(true);
		mBitmap = view.getDrawingCache();
		
		float scaleFactor = 8;
		float radius = 10;
		int width = mBitmap.getWidth();
		int height =  mBitmap.getHeight();

		overlay = Bitmap.createBitmap((int) (width / scaleFactor),(int) (height / scaleFactor), Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(overlay);
		canvas.scale(1 / scaleFactor, 1 / scaleFactor);
		Paint paint = new Paint();
		paint.setFlags(Paint.FILTER_BITMAP_FLAG);
		canvas.drawBitmap(mBitmap, 0, 0, paint);

		overlay = FastBlur.doBlur(overlay, (int) radius, true);
		Log.i(TAG, "blur time is:"+(System.currentTimeMillis() - startMs));
		return overlay;
	}
	
	private Animation showAnimation1(final View view, int fromY , int toY) {
		AnimationSet set = new AnimationSet(true);
		TranslateAnimation go = new TranslateAnimation(0, 0, fromY, toY);
		go.setDuration(300);
		TranslateAnimation go1 = new TranslateAnimation(0, 0, -10, 2);
		go1.setDuration(100);
		go1.setStartOffset(250);
		set.addAnimation(go1);
		set.addAnimation(go);

		set.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationEnd(Animation animation) {
			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}

			@Override
			public void onAnimationStart(Animation animation) {

			}

		});
		return set;
	}
	

	public void showMoreWindow(View anchor, int bottomMargin) {
		final LinearLayout layout = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.center_music_more_window, null);
		setContentView(layout);
		
		ImageView close= (ImageView)layout.findViewById(R.id.center_music_window_close);
//		android.widget.RelativeLayout.LayoutParams params =new android.widget.RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//		params.bottomMargin = bottomMargin;
//		params.addRule(RelativeLayout.BELOW, R.id.more_window_auto);
//		params.addRule(RelativeLayout.RIGHT_OF, R.id.more_window_collect);
//		params.topMargin = 200;
//		params.leftMargin = 18;
//		close.setLayoutParams(params);
		
		close.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (isShowing()) {
					closeAnimation(layout);
				}
			}

		});
		
		showAnimation(layout);
		setBackgroundDrawable(new BitmapDrawable(mContext.getResources(), blur()));
		setOutsideTouchable(true);
		setFocusable(true);
		showAtLocation(anchor, Gravity.BOTTOM, 0, statusBarHeight);
	}

	private void showAnimation(ViewGroup layout){
		for(int i=0;i<layout.getChildCount();i++){
			final View child = layout.getChildAt(i);
			if(child.getId() == R.id.center_music_window_close){
				continue;
			}
			child.setOnClickListener(this);
			child.setVisibility(View.INVISIBLE);
			mHandler.postDelayed(new Runnable() {
				
				@Override
				public void run() {
					if(child.getId() == R.id.more_window_collect||child.getId() == R.id.more_window_auto||child.getId() == R.id.more_window_external){

					}else
						child.setVisibility(View.VISIBLE);
					ValueAnimator fadeAnim = ObjectAnimator.ofFloat(child, "translationY", 600, 0);
					fadeAnim.setDuration(300);
					KickBackAnimator kickAnimator = new KickBackAnimator();
					kickAnimator.setDuration(150);
					fadeAnim.setEvaluator(kickAnimator);
					fadeAnim.start();
				}
			}, i * 50);
		}
		
	}

	private void closeAnimation(ViewGroup layout){
		for(int i=0;i<layout.getChildCount();i++){
			final View child = layout.getChildAt(i);
			if(child.getId() == R.id.center_music_window_close||child.getId() == R.id.more_window_collect||child.getId() == R.id.more_window_auto||child.getId() == R.id.more_window_external){
				continue;
			}
			child.setOnClickListener(this);
			mHandler.postDelayed(new Runnable() {
				
				@Override
				public void run() {
					if(child.getId() == R.id.more_window_collect||child.getId() == R.id.more_window_auto||child.getId() == R.id.more_window_external){

					}else
						child.setVisibility(View.VISIBLE);
					ValueAnimator fadeAnim = ObjectAnimator.ofFloat(child, "translationY", 0, 600);
					fadeAnim.setDuration(200);
					KickBackAnimator kickAnimator = new KickBackAnimator();
					kickAnimator.setDuration(100);
					fadeAnim.setEvaluator(kickAnimator);
					fadeAnim.start();
					fadeAnim.addListener(new AnimatorListener() {
						
						@Override
						public void onAnimationStart(Animator animation) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void onAnimationRepeat(Animator animation) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void onAnimationEnd(Animator animation) {
							child.setVisibility(View.INVISIBLE);
						}
						
						@Override
						public void onAnimationCancel(Animator animation) {
							// TODO Auto-generated method stub
							
						}
					});
				}
			}, (layout.getChildCount()-i-1) * 30);
			
			if(child.getId() == R.id.more_window_local){
				mHandler.postDelayed(new Runnable() {
					
					@Override
					public void run() {
						dismiss();
					}
				}, (layout.getChildCount()-i) * 30 + 80);
			}
		}
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.more_window_local:
			WXWebpageObject webpage = new WXWebpageObject();
			webpage.webpageUrl = "http://wechat.conqueror.cn/jsp/appsendway.jsp?did=1330105050121288&from=singlemessage&isappinstalled=0";
			WXMediaMessage msg = new WXMediaMessage(webpage);
			msg.title = "请开启位置共享，我来接您";
			msg.description = "请保持手机网络正常，允许获取定位";
			Bitmap bmp = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_launcher);
			Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, THUMB_SIZE, THUMB_SIZE, true);
			bmp.recycle();
			msg.thumbData = Util.bmpToByteArray(thumbBmp, true);

			SendMessageToWX.Req req = new SendMessageToWX.Req();
			req.transaction = buildTransaction("webpage");
			req.message = msg;
			req.scene = mTargetScene;
			api.sendReq(req);
			break;
		case R.id.more_window_online:
			mContext.startActivity(new Intent(mContext, NavActivity.class));
			break;
		case R.id.more_window_delete:
			Intent intent = new Intent(mContext, ServiceShopActivity.class);
			Bundle extras = new Bundle();

			String url = "http://sim.conqueror.cn/alipay/index.php/Shop?cut=4&did=12121212";
			// url可以是一号店或者淘宝等第三方的购物wap站点，在该网站的支付过程中，支付宝sdk完成拦截支付
			extras.putString("url", url);
			intent.putExtras(extras);
			mContext.startActivity(intent);
			break;


		default:
			break;
		}
	}


	private String buildTransaction(final String type) {
		return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
	}


	public void destroy() {
		if (null != overlay) {
			overlay.recycle();
			overlay = null;
			System.gc();
		}
		if (null != mBitmap) {
			mBitmap.recycle();
			mBitmap = null;
			System.gc();
		}
	}
	
}
