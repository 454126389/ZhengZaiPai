package app.conqueror.com.zhengzaipai.receiver;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.WindowManager;
import android.widget.Toast;


import org.json.JSONObject;

import java.io.File;

import app.conqueror.com.zhengzaipai.App;
import app.conqueror.com.zhengzaipai.R;
import app.conqueror.com.zhengzaipai.retrofit.Api;
import app.conqueror.com.zhengzaipai.util.DateUtils;
import app.conqueror.com.zhengzaipai.util.LogUtil;
import app.conqueror.com.zhengzaipai.util.MyUitls;
import okhttp3.ResponseBody;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static app.conqueror.com.zhengzaipai.util.MyUitls.Download;
import static app.conqueror.com.zhengzaipai.util.MyUitls.checkIsImageFile;
import static app.conqueror.com.zhengzaipai.util.MyUitls.checkIsVideoFile;
import static app.conqueror.com.zhengzaipai.util.NetWorkUtil.KEY_EXTRAS;
import static app.conqueror.com.zhengzaipai.util.NetWorkUtil.MESSAGE_RECEIVED_ACTION;
import static app.conqueror.com.zhengzaipai.util.NetWorkUtil.fileUrl;


/**
 * Created by Administrator on 2017/6/8.
 */

public class AppReceiver extends BroadcastReceiver {






    @Override
    public void onReceive(Context context, Intent intent) {


//        final IWXAPI api = WXAPIFactory.createWXAPI(context, null);
//
//        // 将该app注册到微信
//        api.registerApp(Constants.APP_ID);


        Observer<String> observerImage = new Observer<String>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(App.getAppContext(), R.string.loading_failed, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(String xgResult) {
                if(xgResult!=null)
                {

                    AlertDialog.Builder dialogBuilder =new AlertDialog.Builder(context)
                            .setTitle("提示")

                            .setMessage("收到来自服务器的回传信息，是否查看")
                            .setNegativeButton("查看", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if(checkIsImageFile(xgResult))
                                        App.getAppContext().startActivity(MyUitls.getImageFileIntent(xgResult));
                                    else if(checkIsVideoFile(xgResult))
                                        App.getAppContext().startActivity(MyUitls.getVideoFileIntent(xgResult));
                                }
                            }).setPositiveButton("取消",null);

                    AlertDialog alertDialog = dialogBuilder.create();
                    alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                    alertDialog.show();


                }
            }
        };


        if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
            String extras = intent.getStringExtra(KEY_EXTRAS);

            try{
                JSONObject json = new JSONObject(extras);
                final String type = json.getString("type");
                String url=json.getString("path");



                switch (type)
                {
                    case "appimage":
////                            x.image().bind(resultImage, image_url);
                         Api.getInstance().movieService.downloadPicFromNet(url)
                                .subscribeOn(Schedulers.io()).map(new Func1<ResponseBody, String>() {

                                    @Override
                                    public String call(ResponseBody responseBody) {




                                        String fileName=fileUrl + File.separator + DateUtils.getTodayDateTimesDetail()+".jpg";

                                        if(Download(responseBody,fileName)) {//保存图片成功
//                                                Bitmap bitmap = BitmapFactory.decodeFile(fileName);
                                            return fileName;//返回一个bitmap对象
                                        }
                                        return null;
                                    }
                                })
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(observerImage);

                        break;
                    case "appvideo":

                         Api.getInstance().movieService.downloadVideoFromNet(url)
                                .subscribeOn(Schedulers.io()).map(new Func1<ResponseBody, String>() {

                                    @Override
                                    public String call(ResponseBody responseBody) {

                                        String fileName=fileUrl + File.separator + DateUtils.getTodayDateTimesDetail()+".mp4";

                                        if(Download(responseBody,fileName)) {//保存图片成功
//                                                Bitmap bitmap = BitmapFactory.decodeFile(cache.getPath() + File.separator + "AndroidTutorialPoint.jpg");
                                            return fileName;//返回一个bitmap对象
                                        }
                                        return null;
                                    }
                                })
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(observerImage);

                        break;
                }
            }
            catch (Exception e)
            {
                LogUtil.d("AppReceiver",e.toString());
            }


        }
    }

}
