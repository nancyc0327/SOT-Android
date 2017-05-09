package com.example.mytestapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by ncai2 on 5/4/17.
 */

public class WebPage extends Activity {

    @Override
    /*public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.webpage);
        //rest of the code
        WebView webView = (WebView)findViewById(R.id.webview1);
        Bundle b = getIntent().getExtras();
        String url = b.getString("show_page");
        webView.loadUrl(url);
        //webView.loadUrl("file:///android_asset/about.html");
    }*/

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webpage);
        final WebView wv = (WebView) findViewById(R.id.webview1);
        Bundle b = getIntent().getExtras();
        String url = b.getString("show_page");
        wv.loadUrl(url);
        wv.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    if (request.getUrl().toString().equals("inapp://contactForm")) {
                        go_to_search();
                        return true;
                    } else {  // Load the URL in `webView`.
                        wv.loadUrl(request.getUrl().toString());
                        return true;
                    }
                }
                return true;
            }
        });
    }

    public void go_to_search() {
        Intent intent = new Intent(this, ContactSearch.class);
        startActivity(intent);
    }
}
