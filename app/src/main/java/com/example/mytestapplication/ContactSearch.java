package com.example.mytestapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

/**
 * Created by ncai2 on 5/2/17.
 */

public class ContactSearch extends Activity{


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_search);
        //rest of the code
        TextView mInfo = (TextView)findViewById(R.id.textView6);

        SpannableString ss = new SpannableString("If you are outside of Nebraska you can find your state's Part C Coordinator at ECTACenter");
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                //startActivity(new Intent(ContactSearch.this, NextActivity.class));
                goToUrl("http://www.nectac.org/contact/ptccoord.asp");
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        };
        ss.setSpan(clickableSpan, 79, 89, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        mInfo.setText(ss);
        mInfo.setMovementMethod(LinkMovementMethod.getInstance());
        mInfo.setHighlightColor(Color.TRANSPARENT);

    }
    public void clickEDU(View v)
    {
        goToUrl("https://www.education.ne.gov/");
    }

    private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }
    // Implement the OnClickListener callback
    public void clickSearch(View v) {
        // do something when the button is clicked
        EditText zipField = (EditText)findViewById(R.id.textView2);
        TextView errorField = (TextView)findViewById(R.id.textView3);
        if (zipField.getText().length()!= 5){

            errorField.setText(R.string.input_5_digit);
            return;
        }

        errorField.setText("");


        GetJsonFromURLJob task = new GetJsonFromURLJob();
        String urlString = "http://edn.ne.gov/referralLookup.php?zip=";
        task.execute(urlString +zipField.getText());

        String result = "";
        try {
            result = task.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        JSONObject rJSON;
        try {
            rJSON = new JSONObject(result);
            String regionNumber = rJSON.getString("regionNumber");
            String name = rJSON.getString("name");
            String address = rJSON.getString("address");
            String cityStateZip = rJSON.getString("cityStateZip");
            String phone = rJSON.getString("phone");
            String email = rJSON.getString("email");
            show_info(regionNumber,name,address,cityStateZip,phone,email );

        } catch (JSONException e) {
            errorField.setText(R.string.zip_not_found);
            e.printStackTrace();
        }


        Log.d("result:", result);


    }

    public void show_info(String region, String name, String address, String city, String phone, String email) {
        Intent intent = new Intent(this, ContactInfo.class);
        intent.putExtra("region",region);
        intent.putExtra("name",name);
        intent.putExtra("address",address);
        intent.putExtra("city",city);
        intent.putExtra("phone",phone);
        intent.putExtra("email",email);
        startActivity(intent);
    }

    private class GetJsonFromURLJob extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String[] params) {
            // do above Server call here
            String str  = "";
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL(params[0]);

                urlConnection = (HttpURLConnection) url
                        .openConnection();

                InputStream in = urlConnection.getInputStream();

                InputStreamReader isw = new InputStreamReader(in);

                int data = isw.read();

                while (data != -1) {
                    char current = (char) data;
                    data = isw.read();
                    System.out.print(current);
                    str = str + current;

                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }

            }
            return str;
        }

        @Override
        protected void onPostExecute(String message) {
            //process message

        }
    }


}
