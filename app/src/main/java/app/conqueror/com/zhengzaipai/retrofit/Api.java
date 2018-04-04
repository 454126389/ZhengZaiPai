// (c)2016 Flipboard Inc, All Rights Reserved.

package app.conqueror.com.zhengzaipai.retrofit;


import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import app.conqueror.com.zhengzaipai.util.SpUtil;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    private static OkHttpClient okHttpClient;
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();


//    public static final String BASE_URL = "http://app.conqueror.net.cn/zzpai/index.php/app/";
    public static final String BASE_URL = "http://cloud.conqueror.cn:808/zzpai/index.php/app/";
    public static final String CARD_URL ="http://139.224.221.22:8181/sim/";
    public static final String SIM_URL ="http://sim.conqueror.cn/CiLearn/index.php/";



    public static final String GOOGLE_URL ="https://maps.googleapis.com/maps/api/";

    public  String WATCH_URL;

    //大陆正式
//    public static final String WATCH_URL ="http://61.131.68.156:8081/";
//    大陆测试
//    public static final String WATCH_URL ="http://192.168.1.46:8081/";
//    台湾正式
//    public static final String WATCH_URL ="http://watch.conqueror.net.cn:8082/";

    public ApiService movieService;
    public Retrofit retrofit;

    public ApiService movieService_card;
    public Retrofit retrofit_card;

    public ApiService movieService_sim;
    public Retrofit retrofit_sim;

    public ApiService movieService_watch;
    public Retrofit retrofit_watch;

    public ApiService movieService_google;
    public Retrofit retrofit_google;



    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final Api INSTANCE = new Api();
    }

    //获取单例
    public static Api getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private Api() {

        if(SpUtil.getLanguage().equals("zh-TW"))
            WATCH_URL="http://watch.conqueror.net.cn:8082/";
        else
            WATCH_URL="http://61.131.68.156:8081/";

        OkHttpClient.Builder okhttpBuilder=new OkHttpClient.Builder()
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS).retryOnConnectionFailure(true);

        okHttpClient=okhttpBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        }).build();



        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .build();

        movieService = retrofit.create(ApiService.class);

        retrofit_card = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(CARD_URL)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .build();

        movieService_card = retrofit_card.create(ApiService.class);

        retrofit_sim = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(SIM_URL)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .build();

        movieService_sim = retrofit_sim.create(ApiService.class);


        retrofit_watch = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(WATCH_URL)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)

                .build();

        movieService_watch = retrofit_watch.create(ApiService.class);


        retrofit_google = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(GOOGLE_URL)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)

                .build();

        movieService_google = retrofit_google.create(ApiService.class);


    }





}
