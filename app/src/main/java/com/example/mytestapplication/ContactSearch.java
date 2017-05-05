package com.example.mytestapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
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

public class ContactSearch extends Activity implements View.OnClickListener {

    private String urlString = "http://edn.ne.gov/referralLookup.php?zip=";


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_search);
        //rest of the code
        Button mClickButton1 = (Button)findViewById(R.id.button);
        mClickButton1.setOnClickListener(this);
        EditText mInput = (EditText)findViewById(R.id.textView2);

    }

    // Implement the OnClickListener callback
    public void onClick(View v) {
        // do something when the button is clicked
        EditText zipField = (EditText)findViewById(R.id.textView2);
        TextView errorField = (TextView)findViewById(R.id.textView3);
        if (zipField.getText().length()<5){

            errorField.setText("Input 5 digit number for Zip code.");
            return;
        }

        errorField.setText("");


            GetJsonFromURLJob task = new GetJsonFromURLJob();
            task.execute(urlString+zipField.getText());

                String result = "";
        try {
            result = task.get().toString();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        JSONObject rJSON = null;
        try {
            rJSON = new JSONObject(result);
            String regionNumber = (String) rJSON.get("regionNumber");
            String name = (String) rJSON.get("name");
            String address = (String) rJSON.get("address");
            String cityStateZip = (String) rJSON.get("cityStateZip");
            String phone = (String) rJSON.get("phone");
            String email = (String) rJSON.get("email");
            ContactModel contact = new ContactModel(regionNumber,name,phone,address+", "+cityStateZip,email);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        Log.d("result:", result);


    }

    public void show_info(View v) {
        Intent intent = new Intent(this, ContactInfo.class);
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
