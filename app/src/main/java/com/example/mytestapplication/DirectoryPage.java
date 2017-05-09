package com.example.mytestapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ncai2 on 5/4/17.
 */

public class DirectoryPage extends Activity {

    private String mainDir;
    private ArrayList<String> subdirs;
    private ArrayList<String> fileNames;
    private ListView mainListView;
    private ArrayAdapter<String> listAdapter ;

    private DirectoryModel dirModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.directorypage);
        //rest of the code
        Bundle b = getIntent().getExtras();
        mainDir = b.getString("main_dir");
        subdirs = b.getStringArrayList("sub_dir");
        fileNames = b.getStringArrayList("file_name");

        mainListView = (ListView) findViewById( R.id.mainListView );
        listAdapter = new ArrayAdapter<String>(this, R.layout.simple_list_item_1, subdirs);
        mainListView.setAdapter( listAdapter );

        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String subDirName = parent.getItemAtPosition(position).toString();
                String fileName = fileNames.get(position);
                Log.d("clicked:", String.valueOf(position));
                //Intent i = new Intent(Activity.this, WebPage.class);
                //startActivity(i);

                String url = "file:///android_asset/"+fileName;
                Intent intent = new Intent(DirectoryPage.this, WebPage.class);
                Bundle b = new Bundle();
                b.putString("show_page", url);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }


}
