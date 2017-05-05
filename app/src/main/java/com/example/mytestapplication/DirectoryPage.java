package com.example.mytestapplication;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by ncai2 on 5/4/17.
 */

public class DirectoryPage extends Activity {

    private String mainDir;
    private ArrayList<String> subdirs;
    private ListView mainListView;
    private ArrayAdapter<String> listAdapter ;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.directorypage);
        //rest of the code
        Bundle b = getIntent().getExtras();
        mainDir = b.getString("main_dir");
        subdirs = b.getStringArrayList("sub_dir");

        mainListView = (ListView) findViewById( R.id.mainListView );
        listAdapter = new ArrayAdapter<String>(this, R.layout.simple_list_item_1, subdirs);
        mainListView.setAdapter( listAdapter );
    }
}
