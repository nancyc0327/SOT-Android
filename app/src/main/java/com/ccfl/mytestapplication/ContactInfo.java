package com.ccfl.mytestapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.mytestapplication.R;

/**
 * Created by ncai2 on 5/2/17.
 */

public class ContactInfo extends AppCompatActivity {
    String phoneStr;
    String emailStr;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_info);
        //rest of the code
        Intent intent = getIntent();

        String nameStr = intent.getStringExtra("name");
        String regionNumStr = intent.getStringExtra("region");
        phoneStr = intent.getStringExtra("phone");
        String addressStr = intent.getStringExtra("address");
        emailStr = intent.getStringExtra("email");

        TextView infoField = (TextView) findViewById(R.id.textView5);
        //infoField.setText("test\\ntest\\ntest\\ntesttesttesttesttest");

        infoField.setText(nameStr + System.getProperty("line.separator")
                + phoneStr + System.getProperty("line.separator")
                + addressStr + System.getProperty("line.separator") + emailStr);

    }

    public void call(View v) {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:"+Uri.encode(phoneStr.trim())));
        callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(callIntent);
    }

    public void email(View v)
    {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto",emailStr, null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
        startActivity(Intent.createChooser(emailIntent, "Send email..."));

        //String[] emails = {emailStr};

        //Intent email = new Intent(Intent.ACTION_SEND);
        //email.putExtra(Intent.EXTRA_EMAIL, emails);
        //email.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        //email.putExtra(Intent.EXTRA_TEXT, "Body");

        // need this to prompts email client only
        //email.setType("message/rfc822");

        //startActivity(Intent.createChooser(emailIntent, "Choose an Email client :"));
    }
}
