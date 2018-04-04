package app.conqueror.com.zhengzaipai.mainfragment.onway;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import app.conqueror.com.zhengzaipai.R;
import butterknife.Bind;
import butterknife.ButterKnife;


public class GCFragment extends Fragment {


    @Bind(R.id.webView1)
    WebView mWebview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gc, container, false);
        //绑定fragment

        ButterKnife.bind(this, view);
        initView();

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);


    }

    public void initView() {
        mWebview.loadUrl("http://cloud.conqueror.cn:808/zzpai/");
//        mWebview.loadUrl("http://app.conqueror.net.cn/zzpai/index.php/Video?url=192.168.191.2:8080");
//        mWebview.loadUrl("http://app.conqueror.net.cn/zzpai/index.php/Video?url=192.168.191.2:8080");
//        mWebview.loadUrl(" file:///android_asset/urlplay.html?url=http://192.168.43.1:8080");

        //load asset file


//        String tpl = getFromAssets("play.html");
////String tpl = getFromAssets("loginPageView");
//        mWebview.loadDataWithBaseURL(null,tpl,"text/html","utf-8",null);

        // 设置WebView属性，能够执行JavaScript脚本
        mWebview.getSettings().setJavaScriptEnabled(true);
        // 设置可以支持缩放
        mWebview.getSettings().setSupportZoom(true);
        // 设置出现缩放工具
//        mWebview.getSettings().setBuiltInZoomControls(true);
        // 为图片添加放大缩小功能
//        mWebview.getSettings().setUseWideViewPort(true);

        mWebview.setInitialScale(70);   //100代表不缩放

    }

    /**
     * 获取html文件
     **/
    public String getFromAssets(String fileName) {
        try {
            InputStreamReader inputReader = new InputStreamReader(
                    getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line;
            String Result = "";
            while ((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
