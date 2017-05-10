package com.example.mytestapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by ncai2 on 5/2/17.
 */

public class ContactInfo extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_info);
        //rest of the code
        Intent intent = getIntent();

        String nameStr = intent.getStringExtra("name");
        String regionNumStr = intent.getStringExtra("region");
        String phoneStr = intent.getStringExtra("phone");
        String addressStr = intent.getStringExtra("address");
        String emailStr = intent.getStringExtra("email");

        TextView infoField = (TextView)findViewById(R.id.textView5);
        //infoField.setText("test\\ntest\\ntest\\ntesttesttesttesttest");

        infoField.setText(regionNumStr + System.getProperty("line.separator")
                + nameStr + System.getProperty("line.separator")
                + phoneStr + System.getProperty("line.separator")
                + addressStr + System.getProperty("line.separator") + emailStr);

    }
}
