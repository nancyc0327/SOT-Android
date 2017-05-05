package com.example.mytestapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by ncai2 on 5/2/17.
 */

public class ContactInfo extends Activity {
  private String regionNumStr = "Region Number:";
    private String nameStr = "testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttest";
    private String phoneStr = "Phone Number: ";
    private String addressStr = "Address:";
    private String emailStr = "Email:";

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_info);
        //rest of the code
        TextView infoField = (TextView)findViewById(R.id.textView5);
        //infoField.setText("test\\ntest\\ntest\\ntesttesttesttesttest");

        infoField.setText(regionNumStr + System.getProperty("line.separator")
                +nameStr+ System.getProperty("line.separator")
                +phoneStr+ System.getProperty("line.separator")
                +addressStr+ System.getProperty("line.separator") +emailStr);

    }
}
