package com.AVD.WI_FTP.receiver;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.AVD.WI_FTP.R;

/**
 * Created by aruna on 04-May-17.
 */

public class Fback extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fback);
        WebView webview = new WebView(this);
        setContentView(webview);
        webview.setWebViewClient(new WebViewClient());
        webview.getSettings().setBuiltInZoomControls(true);
        webview.getSettings().supportZoom();
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webview.getSettings().getJavaScriptEnabled();
        webview.getSettings().getJavaScriptCanOpenWindowsAutomatically();
        webview.loadUrl("https://goo.gl/VU6X3H");
    }
}
