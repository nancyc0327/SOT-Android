package com.example.mytestapplication;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.webkit.URLUtil;
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
                    String url = request.getUrl().toString();
                    boolean isUrl = URLUtil.isNetworkUrl(url);
                    /*if(isUrl ) {
                        return false;
                    }*/


                    if (url.endsWith(".pdf"))
                    {
                        // Download pdf
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                        // if want to download pdf manually create AsyncTask here
                        // and download file
                        return true;
                    }

                    if(isUrl ) {
                        return false;
                    }

                    if ( url.startsWith("file:///android_asset/") ) {
                        wv.loadUrl(url);
                        return true;
                    }

                    if (request.getUrl().toString().equals("inapp://contactForm")) {
                        go_to_search();
                        return true;
                    }

                    if (url.startsWith("mailto:")) {  // Load the URL in `webView`.


                        /*Intent email = new Intent(Intent.ACTION_SEND,Uri.parse(url));
                        email.putExtra(Intent.EXTRA_EMAIL, url.replace("mailto:",""));
                        email.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                        email.putExtra(Intent.EXTRA_TEXT, "Body");
                        startActivity(Intent.createChooser(email, "Choose an Email client :"));
                        */

                        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                                "mailto",url.replaceFirst("mailto:",""), null));
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                        emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
                        startActivity(Intent.createChooser(emailIntent, "Send email..."));
                        startActivity(Intent.createChooser(emailIntent, "Choose an Email client :"));

                        return true;
                    }
                    else {
                        // Otherwise allow the OS to handle things like tel, mailto, etc.
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
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
