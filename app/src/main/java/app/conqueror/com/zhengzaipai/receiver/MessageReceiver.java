package app.conqueror.com.zhengzaipai.receiver;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.tencent.android.tpush.XGPushBaseReceiver;
import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushRegisterResult;
import com.tencent.android.tpush.XGPushShowedResult;
import com.tencent.android.tpush.XGPushTextMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import app.conqueror.com.zhengzaipai.App;
import app.conqueror.com.zhengzaipai.base.RxSchedulers;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActRemoteCamera.ActCameraActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.device.ActRemoteCamera.ImageScaleActivity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.ActResult;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.ChatMsg;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WatchDevice;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WatchMsg;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WatchPoiEntity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.Wifi;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WifiMsg;
import app.conqueror.com.zhengzaipai.receiver.bean.XGNotification;
import app.conqueror.com.zhengzaipai.receiver.common.NotificationService;
import app.conqueror.com.zhengzaipai.retrofit.Api;
import app.conqueror.com.zhengzaipai.util.Actions;
import app.conqueror.com.zhengzaipai.util.DateUtils;
import app.conqueror.com.zhengzaipai.util.LogUtil;
import app.conqueror.com.zhengzaipai.util.MyUitls;
import app.conqueror.com.zhengzaipai.util.NetWorkUtil;
import app.conqueror.com.zhengzaipai.util.SpUtil;
import rx.Observer;

import static app.conqueror.com.zhengzaipai.util.MyUitls.checkIsImageFile;
import static app.conqueror.com.zhengzaipai.util.MyUitls.checkIsVideoFile;


public class MessageReceiver extends XGPushBaseReceiver {
	private Intent intent = new Intent("com.qq.xgdemo.activity.UPDATE_LISTVIEW");
	public static final String LogTag = "TPushReceiver";

	private void show(Context context, String text) {
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}

	// 通知展示
	@Override
	public void onNotifactionShowedResult(Context context,
			XGPushShowedResult notifiShowedRlt) {
		if (context == null || notifiShowedRlt == null) {
			return;
		}

		String extras = notifiShowedRlt.getCustomContent();
//		String showmsg="";
//
//		XGNotification notific = new XGNotification();
//		notific.setMsg_id(notifiShowedRlt.getMsgId());
//		notific.setTitle(notifiShowedRlt.getTitle());


        try {
            JSONObject object=new JSONObject(extras);
			String  message=object.getString("message");

            if(message.split(",")[1].equals("TK"))
            {
                //语音
//			2016000891,TK,http://webservice.conqueror.cn:8181/arm/20160008911504849410850.arm
                List<WatchDevice> watchDeviceList= SpUtil.getAppUser().deviceList;
                for(WatchDevice watch : watchDeviceList) {
                    if(watch.id.equals(message.split(",")[0]))
                    {
                        ChatMsg chatMsg=new ChatMsg(0,1,watch.nickName,message.split(",")[2],true, DateUtils.getCurrentTime_Today());
                        SpUtil.addChatMsgList(message.split(",")[0],chatMsg);


						Intent intent = new Intent(Actions.msg);
//						intent.putExtra("data", "yes i am data");
						context.sendBroadcast(intent);

                    }
                }

            }else if(message.split(",")[1].equals("SAVEZONE"))
			{
				//围栏
//			2016000891,savezone,lat,lon,time,msg
				show(context, "设备"+message.split(",")[0] + "触发围栏");
				List<WatchMsg> watchMsgList=SpUtil.getWatchMsgList(SpUtil.getUsername());
				watchMsgList.add(new WatchMsg(message.split(",")[0],message.split(",")[1],message.split(",")[2],DateUtils.getCurrentTime()));
				SpUtil.setWatchMsgList(SpUtil.getUsername(),watchMsgList);

			}else if(message.split(",")[1].equals("AL"))
			{
				//围栏
//			2016000891,AL,lat,lon,time,msg
				show(context, "设备"+message.split(",")[0] + "报警");
				List<WatchMsg> watchMsgList=SpUtil.getWatchMsgList(SpUtil.getUsername());
				if (watchMsgList==null)
					watchMsgList=new ArrayList<>();
				watchMsgList.add(new WatchMsg(message.split(",")[0],message.split(",")[1],message.split(",")[2],DateUtils.getCurrentTime()));
				SpUtil.setWatchMsgList(SpUtil.getUsername(),watchMsgList);
			}



        } catch (JSONException e) {


        }

//        if(extras.split(",")[1].equals("TK"))
//		{
//
////			showmsg="您有一条来自设备的语音";
//			//语音
////			2016000891,TK,http://webservice.conqueror.cn:8181/arm/20160008911504849410850.arm
//			List<WatchDevice> watchDeviceList= SpUtil.getAppUser().deviceList;
//			for(WatchDevice watch : watchDeviceList) {
//				if(watch.id.equals(extras.split(",")[0]))
//				{
//					ChatMsg chatMsg=new ChatMsg(0,1,watch.nexus,extras.split(",")[2],true, DateUtils.getCurrentTime_Today());
//					SpUtil.addChatMsgList(extras.split(",")[0],chatMsg);
//				}
//			}
//
//		}

//		notific.setContent(showmsg);
//		// notificationActionType==1为Activity，2为url，3为intent
//		notific.setNotificationActionType(notifiShowedRlt
//				.getNotificationActionType());
//		//Activity,url,intent都可以通过getActivity()获得
//		notific.setActivity(notifiShowedRlt.getActivity());
//		notific.setUpdate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//				.format(Calendar.getInstance().getTime()));
//		NotificationService.getInstance(context).save(notific);
//		context.sendBroadcast(intent);





//		show(context, "您有1条新消息, " + "通知被展示 ， " + notifiShowedRlt.toString());
//		Log.d("LC","+++++++++++++++++++++++++++++展示通知的回调");
	}

	@Override
	public void onUnregisterResult(Context context, int errorCode) {
		if (context == null) {
			return;
		}
		String text = "";
		if (errorCode == XGPushBaseReceiver.SUCCESS) {
			text = "反注册成功";
		} else {
			text = "反注册失败" + errorCode;
		}
		Log.d(LogTag, text);
		show(context, text);

	}

	@Override
	public void onSetTagResult(Context context, int errorCode, String tagName) {
		if (context == null) {
			return;
		}
		String text = "";
		if (errorCode == XGPushBaseReceiver.SUCCESS) {
			text = "\"" + tagName + "\"设置成功";
		} else {
			text = "\"" + tagName + "\"设置失败,错误码：" + errorCode;
		}
		Log.d(LogTag, text);
		show(context, text);

	}

	@Override
	public void onDeleteTagResult(Context context, int errorCode, String tagName) {
		if (context == null) {
			return;
		}
		String text = "";
		if (errorCode == XGPushBaseReceiver.SUCCESS) {
			text = "\"" + tagName + "\"删除成功";
		} else {
			text = "\"" + tagName + "\"删除失败,错误码：" + errorCode;
		}
		Log.d(LogTag, text);
		show(context, text);

	}

	// 通知点击回调 actionType=1为该消息被清除，actionType=0为该消息被点击
	@Override
	public void onNotifactionClickedResult(Context context,
			XGPushClickedResult message) {
		if (context == null || message == null) {
			return;
		}
		String text = "";
		if (message.getActionType() == XGPushClickedResult.NOTIFACTION_CLICKED_TYPE) {
			// 通知在通知栏被点击啦。。。。。
			// APP自己处理点击的相关动作
			// 这个动作可以在activity的onResume也能监听，请看第3点相关内容
			text = "通知被打开 :" + message;
		} else if (message.getActionType() == XGPushClickedResult.NOTIFACTION_DELETED_TYPE) {
			// 通知被清除啦。。。。
			// APP自己处理通知被清除后的相关动作
			text = "通知被清除 :" + message;
		}
//		Toast.makeText(context, "广播接收到通知被点击:" + message.toString(),
//				Toast.LENGTH_SHORT).show();
		// 获取自定义key-value
		String customContent = message.getCustomContent();
		if (customContent != null && customContent.length() != 0) {
			try {
				JSONObject obj = new JSONObject(customContent);
				// key1为前台配置的key
				if (!obj.isNull("key")) {
					String value = obj.getString("key");
					Log.d(LogTag, "get custom value:" + value);
				}
				// ...
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		// APP自主处理的过程。。。
//		Log.d(LogTag, text);
//		show(context, text);
	}

	@Override
	public void onRegisterResult(Context context, int errorCode,
                                 XGPushRegisterResult message) {
		// TODO Auto-generated method stub
		if (context == null || message == null) {
			return;
		}
		String text = "";
		if (errorCode == XGPushBaseReceiver.SUCCESS) {
			text = message + "注册成功";
			// 在这里拿token
			String token = message.getToken();
			SpUtil.setXGToken(token);


		} else {
			text = message + "注册失败，错误码：" + errorCode;
			show(context, text);
		}
		Log.d(LogTag, text);
//		show(context, text);
	}

	// 消息透传
	@Override
	public void onTextMessage(Context context, XGPushTextMessage message) {
		// TODO Auto-generated method stub
//		String text = "收到消息:" + message.toString();
		// 获取自定义key-value
		String extras = message.getContent();
	try {
//		照片
		if(extras.split(",")[1].equals("img"))
		{
			SpUtil.addImgList(extras.split(",")[0],extras.split(",")[2]);
			AlertDialog.Builder dialogBuilder =new AlertDialog.Builder(context)
					.setTitle("提示")
					.setMessage("收到来自服务器的回传信息，是否查看")
					.setNegativeButton("查看", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
//							String contextString = context.toString();
//							 String name=contextString.substring(contextString.lastIndexOf(".") + 1, contextString.indexOf("@"));

							App.getAppContext().startActivity((new Intent(App.getAppContext(),ImageScaleActivity.class).putExtra("filePath", extras.split(",")[2])).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

						}
					}).setPositiveButton("取消",null);

			AlertDialog alertDialog = dialogBuilder.create();
			alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
			alertDialog.show();

		}else if(extras.split(",")[1].equals("UD"))
		{
			//定位
			//2016000891,UD,1504820735,0,24.651092,N,118.1513583,E,0.00,0.0,0.0,0,100,96,0,0,00000018
			WatchPoiEntity watchPoiEntity=SpUtil.getWatchPoiEntity(extras.split(",")[0]);
			watchPoiEntity.setLat(Double.parseDouble(extras.split(",")[4]));
			watchPoiEntity.setLng(Double.parseDouble(extras.split(",")[6]));
			SpUtil.setWatchPoiEntity(watchPoiEntity,extras.split(",")[0]);

			Intent intent = new Intent();
			intent.setAction(Actions.ACTION_CHANGE_GPS);
			intent.putExtra("watchPoi",watchPoiEntity);
			context.sendBroadcast(intent);

		}else if(extras.split(",")[1].equals("TK"))
		{
			//语音
//			2016000891,TK,http://webservice.conqueror.cn:8181/arm/20160008911504849410850.arm
			List<WatchDevice> watchDeviceList= SpUtil.getAppUser().deviceList;
			for(WatchDevice watch : watchDeviceList) {
				if(watch.id.equals(extras.split(",")[0]))
				{
					ChatMsg chatMsg=new ChatMsg(0,1,watch.nickName,extras.split(",")[2],true, DateUtils.getCurrentTime_Today());
					SpUtil.addChatMsgList(extras.split(",")[0],chatMsg);

					Intent intent = new Intent(Actions.msg);
//					intent.putExtra("data", "yes i am data");
					context.sendBroadcast(intent);

				}
			}

		}else if(extras.split(",")[1].equals("WIFISEARCH"))
		{
			//语音
//			2016000891,TK,http://webservice.conqueror.cn:8181/arm/20160008911504849410850.arm
//			List<WatchDevice> watchDeviceList= SpUtil.getAppUser().deviceList;

			String [] wifimap=extras.split(",");
			int num=Integer.parseInt(wifimap[2]);
			List<WifiMsg> list=new ArrayList<>();
			for(int i=3;i<3+(num*2);i=i+2) {
				list.add(new WifiMsg(wifimap[i],wifimap[i+1]));
			}
			SpUtil.setWifiMsgList(wifimap[0],list);
		}

	} catch (Exception e) {
		LogUtil.d(LogTag,e.toString());
	}

	}

}
