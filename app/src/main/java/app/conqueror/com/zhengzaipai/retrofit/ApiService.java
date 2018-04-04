package app.conqueror.com.zhengzaipai.retrofit;




import java.io.File;

import app.conqueror.com.zhengzaipai.entity.CodeResult;
import app.conqueror.com.zhengzaipai.entity.DateResult;
import app.conqueror.com.zhengzaipai.entity.ServiceResult;
import app.conqueror.com.zhengzaipai.entity.XgResult;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.ActHealthResult;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.ActResult;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.AppResult;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.AuthResult;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.GoogleResult;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.GpsMsg;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.Result;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WatchResult;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WatchPoiResult;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WatchResult2;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Administrator on 2016/3/23.
 */
public interface ApiService {

    //获取设备列表
    @GET("Tools?action=getAppDeviceList")
    Observable<DateResult> getDeviceList(@Query("appid") String appid);

    //获取征卡列表
    @GET("Tools?action=getAppCardList")
    Observable<DateResult> getCardList(@Query("appid") String appid);

    //绑定设备
    @GET("Tools?action=lockAppDevice")
    Observable<DateResult> lockDevice(@Query("appid") String appid,@Query("did") String did,@Query("phone") String phone,@Query("name") String name);

    //绑定征卡
    @GET("Tools?action=lockAppCard")
    Observable<DateResult> lockCard(@Query("appid") String appid,@Query("iccid") String iccid,@Query("phone") String phone,@Query("alias") String alias);


//    短信接口
    @GET("smsActivateCode")
    Observable<CodeResult> getDeviceCode(@Query("phone") String phone);

    //设备关机
    @GET("Tools?action=closeSystem")
        Observable<XgResult> closeSystem(@Query("apptoken") String xgtoken, @Query("did") String did);

    //请求拍照
    @GET("Tools?action=takePhoto")
    Observable<XgResult> takePhoto(@Query("apptoken") String apptoken, @Query("did") String did);

    //请求录影
    @GET("Tools?action=takeVideo")
    Observable<XgResult> takeVideo(@Query("apptoken") String apptoken, @Query("did") String did);

    //语音播报
    @GET("Tools?action=speekWords")
    Observable<XgResult> speekWords(@Query("apptoken") String apptoken, @Query("did") String did,@Query("words") String words);

    //预约导航
    @GET("Tools?action=takeNav")
    Observable<XgResult> takeNav(@Query("apptoken") String apptoken, @Query("did") String did,@Query("lat") String lat,@Query("lng") String lng);


    //下载文件
    @GET
    Observable<ResponseBody> downloadPicFromNet(@Url String fileUrl);

    //下载文件
    @GET
    Observable<ResponseBody> downloadVideoFromNet(@Url String fileUrl);


    //获取充值列表
    @GET("App?action=shop")
    Observable<ServiceResult> getServiceList(@Query("iccid") String iccid);


    //预约导航
    @GET("getGps")
    Observable<WatchPoiResult> test(@Query("id") String id);


    //预约导航
    @GET("getGps")
    Observable<WatchPoiResult> getWatchPoi(@Query("id") String id);

//    http://192.168.2.150:8081/setRadis?id=2088777&paparm=id,alias,lat,lon,radius,onOff

    //上传围栏
    @GET("setRadis")
    Observable<WatchPoiResult> AddZone(@Query("id") String id, @Query("paparm") String paparm);

    //获取轨迹
    @GET("getGpsByIdAndTime")
    Observable<GpsMsg> getTrail(@Query("id") String id, @Query("stime") String stime, @Query("etime") String etime);

    //http://61.131.68.156:8081/findDevicesByUserPhone?phone=18106573838
    //获得所有设备信息
    @GET("findDevicesByUserPhone")
    Observable<WatchResult> findDevicesByUserPhone(@Query("phone") String phone);




    //获得用户信息和设备信息
    @GET("findUserByPhone")
    Observable<AppResult> findUserByPhone(@Query("phone") String phone);


//    http://61.131.68.156:8081/order/message?id=2016000894&content=597D003100320033
//    http://61.131.68.156:8081/order/tk?id=2016000894&content=FFGFDSHRETER434TDFV34GRGHGFHG.............
    //发送文字信息
    @GET("order/message")
    Observable<ActResult> message(@Query("id") String id, @Query("content") String content);

    @GET("order/vipmessage")
    Observable<ActResult> vipmessage(@Query("id") String id,@Query("uid") String uid, @Query("content") String content);






    @Multipart
    @POST("order/tk")
    Observable<ActResult> tk(@Part("description") RequestBody description,@Part MultipartBody.Part file, @Query("id")String id);




    @GET("order/viptk")
    Observable<ActResult> viptk(@Part("description") RequestBody description,@Part MultipartBody.Part file,@Query("id") String id);


//    @Multipart
//    @POST("order/tk")
//    Observable<ResponseBody> tk2(
//            @Part MultipartBody.Part file,@Part MultipartBody.Part id);

//    MultipartBody.Part no =
//            MultipartBody.Part.createFormData("name", "a");
//    MultipartBody.Part pass =
//            MultipartBody.Part.createFormData("pass", "b");

//    @Multipart  @POST("servlet/TestPostMultipartBodyPart")
//    Observable<ResponseBody> testMultipartBody(
//            @Part("photo1") RequestBody image1,@Part MultipartBody.Part image2,Query("des")String des);
//    @Multipart @POST("servlet/TestPostMultipartBodyPart") Observable<ResponseBody> testMultipartBody(
//            @Part("photo1") RequestBody image1, @Part MultipartBody.Part image2, @Query("des")String des ); 可


    //发送语音信息
//    @GET("order/tk")
//    Observable<ActResult> tk(@Query("id") String id, @Query("content") String content);
//    @POST("/file")
//    @Multipart
//    Observable<DataResponse<UploadFile>> uploadFile(@Part("file\"; filename=\"avatar.png\"") RequestBody file);
//    @POST("order/tk")
//    @Multipart
//    Observable<ActResult> tk(@Query("id") String id,@Part("file") RequestBody file);

//    /*上传文件*/
//    @Multipart
//    @POST("AppYuFaKu/uploadHeadImg")
//    Observable<baseresultentity<uploadresulte>> uploadImage(@Part("uid") RequestBody uid, @Part("auth_key") RequestBody  auth_key,@Part MultipartBody.Part file);



//    http://61.131.68.156:8081/order/tk

    //找手表
    @GET("order/find")
    Observable<ActResult> find(@Query("id") String id);

    //关机
    @GET("order/poweroff")
    Observable<ActResult> poweroff(@Query("id") String id);

    //去下手环报警
    @GET("order/remove")
    Observable<ActResult> remove(@Query("id") String id,@Query("content") String content);

//    http://61.131.68.156:8081/order/sos?id=2016000894&content=13106978653
    //SOS号码设置
    @GET("order/sos")
    Observable<ActResult> sos(@Query("id") String id,@Query("content") String content);


    //工作模式
    @GET("order/upload")
    Observable<ActResult> upload(@Query("id") String id,@Query("content") String content);


//    http://61.131.68.156:8081/unbunding?phone=1385426555&id=2016000984
    //设备解绑
    @GET("unbunding")
    Observable<ActResult> unbunding(@Query("phone") String id,@Query("id") String content);


    //监听
    @GET("order/monitor")
    Observable<ActResult> monitor(@Query("id") String id,@Query("content") String content);

//    @GET("order/monitor")
//    Observable<ActResult> monitor(@Query("id") String id，@Query("id") String id);

//    http://61.131.68.156:8081/order/silenceTime?id=2016000894&content=5
    //免打扰时间段设置
    @GET("order/silenceTime")
    Observable<ActResult> silenceTime(@Query("id") String id,@Query("content") String content);

//    小红花个数设置指令
    @GET("order/flower")
    Observable<ActResult> flower(@Query("id") String id,@Query("content") String content);

    //    通过ID和时间获取步数
    @GET("getWlakByIdAndTime")
    Observable<ActHealthResult> getWlakByIdAndTime(@Query("id") String id,@Query("time") String time);

    //   通过ID和时间获取翻转次数（睡眠统计）
    @GET("getSleepByIdAndTime")
    Observable<ActHealthResult> getSleepByIdAndTime(@Query("id") String id, @Query("time") String time);

    //    http://61.131.68.156:8081/order/tw?lat=24.685&lon=118.6524
    //29.获取天气
    @GET("order/tw")
    Observable<ActResult> tw(@Query("lat") String lat,@Query("lon") String lon);


    //38.远程拍照
    @GET("order/rcapture")
    Observable<ActResult> rcapture(@Query("id") String id);


    //登录时候保存token
    @GET("saveToken")
    Observable<ActResult> saveToken(@Query("phone") String phone,@Query("token") String token,@Query("dtype") String dtype);


    //登录
    @GET("login")
    Observable<AppResult> login(@Query("phone") String phone,@Query("password") String pasword);
//    http://61.131.68.156:8081/login?phone=18106573838&pasword=12345678
//    {"success":"error","mes":{"phone":"18106573838","nickName":"征服者","sex":true,"address":"福建省 厦门市","synopsis":"","devices":[{"rank":1,"nickName":"宝宝","nexus":"爸爸","id":"2016000891"}]}}

//    http://61.131.68.156:8081/order/center?id=2016000894&content=17720847126
    //中心号码设置
    @GET("order/center")
    Observable<ActResult> center(@Query("id") String id,@Query("content") String content);

//    http://61.131.68.156:8081/order/sossms?id=2016000894&content=1
    //SOS短信报警开关
    @GET("order/sossms")
    Observable<ActResult> sossms(@Query("id") String id,@Query("content") String content);


    //低电短信报警开关
    @GET("order/lowbat")
    Observable<ActResult> lowbat(@Query("id") String id,@Query("content") String content);

    //设置电话本
    @GET("order/phb")
    Observable<ActResult> phb(@Query("id") String id,@Query("content") String content);

    //23.闹钟设置指令
    @GET("order/remind")
    Observable<ActResult> remind(@Query("id") String id,@Query("content") String content);


//    http://61.131.68.156:8081/sendVcode?phone=18106976827
    //获取验证码
    @GET("sendVcode")
    Observable<ActResult> sendVcode(@Query("phone") String phone);

    //   http://61.131.68.156:8081/register?phone=18106976827&password=9876542&vcode=9867
    //注册
    @GET("register")
    Observable<ActResult> register(@Query("phone") String phone,@Query("password") String password,@Query("vcode") String vcode);

//    http://61.131.68.156:8081/bunding?phone=1385426555&code=220916500039845&nickName=宝宝&name=爸爸
    //设备绑定
    @GET("bunding")
    Observable<WatchResult2> bunding(@Query("phone") String phone, @Query("code") String code, @Query("nickName") String nickName, @Query("name") String name);


    //34.设备授权
    @GET("auth")
    Observable<WatchResult2> auth(@Query("phone") String phone, @Query("id") String id, @Query("nickName") String nickName, @Query("name") String name);

//    http://61.131.68.156:8081/getOfflineMes?phone=1375683266
    //34.设备授权
    @GET("getOfflineMes")
    Observable<ActResult> getOfflineMes(@Query("phone") String phone);


    //    30.WIFI搜索
    @GET("order/wifisearch")
    Observable<ActResult> wifisearch(@Query("id") String id);

    @GET("order/wifiset")
    Observable<ActResult> wifiset(@Query("id") String id,@Query("content") String content);

    //pedo
    @GET("order/pedo")
    Observable<ActResult> pedo(@Query("id") String id,@Query("content") String content);

    @GET("order/weight")
    Observable<ActResult> weight(@Query("id") String id,@Query("content") String content);

    @GET("order/setp")
    Observable<ActResult> setp(@Query("id") String id,@Query("content") String content);

    @GET("order/walktime")
    Observable<ActResult> walktime(@Query("id") String id,@Query("content") String content);

    @GET("order/sleepTime")
    Observable<ActResult> sleepTime(@Query("id") String id,@Query("content") String content);

    @GET("order/ppr")
    Observable<ActResult> ppr(@Query("id") String id,@Query("fid") String fid);

//    https://maps.googleapis.com/maps/api/geocode/json?latlng=40.714224,-73.961452&key=YOUR_API_KEY
    @GET("geocode/json")
    Observable<GoogleResult> geocode(@Query("latlng") String latlng, @Query("key") String key);

//    http://61.131.68.156:8081/order/cr?id=2016000894
    //登录
    @GET("order/cr")
    Observable<AppResult> cr(@Query("id") String id);


}
