package com.ccfl.mytestapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.URLUtil;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.mytestapplication.R;

/**
 * Created by ncai2 on 5/4/17.
 */

public class WebPage extends AppCompatActivity {

    WebView wv;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webpage);
        wv = (WebView) findViewById(R.id.webview1);
        Bundle b = getIntent().getExtras();
        String url = b.getString("show_page");
        String title = b.getString("title");
        this.setTitle(title);
        wv.loadUrl(url);
        wv.setWebViewClient(new WebViewClient(){

            @SuppressWarnings("deprecation")
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                final Uri uri = Uri.parse(url);
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

                if (url.equals("inapp://contactForm")) {
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

    @Override
    public void onBackPressed()
    {
        if(wv.canGoBack())
            wv.goBack();
        else
            super.onBackPressed();
    }
}
