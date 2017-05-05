package com.example.mytestapplication;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * Created by ncai2 on 5/4/17.
 */

public class WebPage extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.webpage);
        //rest of the code
        WebView webView = (WebView)findViewById(R.id.webview1);
        Bundle b = getIntent().getExtras();
        String url = b.getString("show_page");
        webView.loadUrl(url);
        //webView.loadUrl("file:///android_asset/about.html");
    }
}
