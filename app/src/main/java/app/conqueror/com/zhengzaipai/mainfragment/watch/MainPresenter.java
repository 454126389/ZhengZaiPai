package app.conqueror.com.zhengzaipai.mainfragment.watch;

import android.content.Context;
import android.util.Log;


import app.conqueror.com.zhengzaipai.App;
import app.conqueror.com.zhengzaipai.base.RxManage;
import app.conqueror.com.zhengzaipai.base.RxSchedulers;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WatchPoiEntity;
import app.conqueror.com.zhengzaipai.mainfragment.watch.entity.WatchPoiResult;
import app.conqueror.com.zhengzaipai.retrofit.ApiService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;



/**
 * Created by Administrator on 2016/7/18 0018.
 */
public class MainPresenter implements MainContract.Presenter {

//    private String baseUrl = "http://news-at.zhihu.com";
//    public   String WATCH_URL ="http://192.168.2.150:8081/";
    //private final UserRepository mUserRepository;
    private MainContract.View mMainView;
    private Context mContext;


    //要靠他来获取消息
    protected ApiService service;
    public RxManage mRxManage = new RxManage();

    public MainPresenter(Context context) {
        this.mContext = context;
    }

    public void setView(MainContract.View view) {
        this.mMainView = view;
        mMainView.setPresenter(this);
//        service = getService();
    }


//    public ApiService getService() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(WATCH_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build();
//        service= retrofit.create(ApiService.class);
//        return service;
//    }



//    @Override
//    public WatchPoiEntity getLatestNews() {
//        WatchPoiEntity watchPoi=new WatchPoiEntity();
//        mRxManage.add(service.test("2016000891").compose(RxSchedulers.io_main()).subscribe(res -> {
////                    mView.hideDialog();
//                    if(res.success.equals("success"))
//                    {
//                        String[] subres=(res.mes).split(",");
//                        watchPoi.setTime(subres[0]);
//                        watchPoi.setType(Integer.parseInt(subres[1]));
//                        watchPoi.setLat(Double.parseDouble(subres[2]));
//                        watchPoi.setLng(Double.parseDouble(subres[4]));
//
//                        watchPoi.setSpeed(Float.parseFloat(subres[6]));
//                        watchPoi.setDirec(Float.parseFloat(subres[7]));
//                        watchPoi.setAlt(Float.parseFloat(subres[8]));
//                        watchPoi.setGSM(Integer.parseInt(subres[9]));
//                        watchPoi.setGSM_SENCE(Integer.parseInt(subres[10]));
//                        watchPoi.setElec(Integer.parseInt(subres[11]));
//                        watchPoi.setStep(Integer.parseInt(subres[12]));
//                        watchPoi.setTrun(Integer.parseInt(subres[13]));
//
//
//                        Log.d("kjx","成功");
//                    }
//                    else
//                    {
////                        mView.showMsg("登录失败!"+res.ret_code);
//                        Log.d("kjx","失败:"+res.success);
//                    }
//                }, e -> {
////                    mView.showMsg("登录失败!"+e);
//                    Log.d("kjx","e:"+e.toString());
//                }
//
//        ));
//
////        return loadData(service.test("2016000891"));
//        return watchPoi;
//    }


//    @Override
//    public WatchPoiEntity getSafety() {
//        return null;
//    }
//
//    @Override
//    public WatchPoiEntity getInterest() {
//        return null;
//    }
//
//    @Override
//    public WatchPoiEntity getSport() {
//        return null;
//    }
//
//    @Override
//    public void start() {
//
//    }



    public WatchPoiEntity loadData(Observable<WatchPoiResult> observable) {
        WatchPoiEntity watchPoi=new WatchPoiEntity();
         observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                 .map(new Func1<ResponseBody, WatchPoiEntity>() {
//
//                     @Override
//                     public WatchPoiEntity call(ResponseBody responseBody) {
//
//                         WatchPoiEntity watchPoi=new WatchPoiEntity();
////                         responseBody.string();
//
//                        /*         1504027253,
//                                 1,
//                                 24.651203,
//                                         N,
//                                 118.1513733,E,
//                                         6====1.54,
//                                 77.6,0.0,8,80,5,0,0,00000019
//*/
//                         try {
//                             String[] res=(responseBody.string()).split(",");
//                             watchPoi.setTime(res[0]);
//                             watchPoi.setType(Integer.parseInt(res[1]));
//                             watchPoi.setLat(Double.parseDouble(res[2]));
//                             watchPoi.setLng(Double.parseDouble(res[4]));
//
//                             watchPoi.setSpeed(Integer.parseInt(res[6]));
//                             watchPoi.setDirec(Integer.parseInt(res[7]));
//                             watchPoi.setAlt(Integer.parseInt(res[8]));
//                             watchPoi.setGSM(Integer.parseInt(res[9]));
//                             watchPoi.setGSM_SENCE(Integer.parseInt(res[10]));
//                             watchPoi.setSpeed(0);
//                             watchPoi.setSpeed(0);
//                         } catch (IOException e) {
//                             e.printStackTrace();
//                         }
//
//                         return watchPoi;
//                      /*   String fileName=fileUrl + File.separator + DateUtils.getTodayDateTimesDetail()+".jpg";
//
//                         if(Download(responseBody,fileName)) {//保存图片成功
////                                                Bitmap bitmap = BitmapFactory.decodeFile(fileName);
//                             return fileName;//返回一个bitmap对象
//                         }
//                         return null;*/
//                     }
//                 })
                .subscribe(new Observer<WatchPoiResult>() {
                    @Override
                    public void onCompleted() {
                        App.showText("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        App.showText("e:"+e);
                    }

                    @Override
                    public void onNext(WatchPoiResult s) {
                        App.showText("s:"+s);
                        if(s.success.equals("success"))
                        {
                             String[] res=(s.mes).split(",");
                             watchPoi.setTime(res[0]);
                             watchPoi.setType(Integer.parseInt(res[1]));
                             watchPoi.setLat(Double.parseDouble(res[2]));
                             watchPoi.setLng(Double.parseDouble(res[4]));

                             watchPoi.setSpeed(Float.parseFloat(res[6]));
                             watchPoi.setDirec(Float.parseFloat(res[7]));
                             watchPoi.setAlt(Float.parseFloat(res[8]));
                             watchPoi.setGSM(Integer.parseInt(res[9]));
                             watchPoi.setGSM_SENCE(Integer.parseInt(res[10]));
                             watchPoi.setElec(11);
                             watchPoi.setStep(12);
                             watchPoi.setTrun(13);
                        }
                    }

                }
                )
         ;



        return watchPoi;
    }




    @Override
    public void start() {

    }


    @Override
    public void getWatchPoi(String id) {
        WatchPoiEntity watchPoi=new WatchPoiEntity();
        mRxManage.add(service.test(id).compose(RxSchedulers.io_main()).subscribe(res -> {

                    if(res.success.equals("success"))
                    {
                        String[] subres=res.mes.split(",");
                        watchPoi.setTime(""+(Long.parseLong(subres[0])+28800));
                        watchPoi.setType(Integer.parseInt(subres[1]));
                        watchPoi.setLat(Double.parseDouble(subres[2]));
                        watchPoi.setLng(Double.parseDouble(subres[4]));

                        watchPoi.setSpeed(Float.parseFloat(subres[6]));
                        watchPoi.setDirec(Float.parseFloat(subres[7]));
                        watchPoi.setAlt(Float.parseFloat(subres[8]));
                        watchPoi.setGSM(Integer.parseInt(subres[9]));
                        watchPoi.setGSM_SENCE(Integer.parseInt(subres[10]));
                        watchPoi.setElec(Integer.parseInt(subres[11]));
                        watchPoi.setStep(Integer.parseInt(subres[12]));
                        watchPoi.setTrun(Integer.parseInt(subres[13]));

                        Log.d("kjx","成功");

                        mMainView.setWatchPoi(watchPoi);
                    }
                    else
                    {
                        Log.d("kjx","失败:"+res.success);
                    }
                }, e -> {
                    Log.d("kjx","e:"+e.toString());
                }

        ));

        mMainView.setWatchPoi(watchPoi);

    }


}
