package com.example.mytestapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by ncai2 on 5/4/17.
 */

public class DirectoryPage extends Activity {

    private ArrayList<String> fileNames;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.directorypage);
        //rest of the code
        Bundle b = getIntent().getExtras();
        ArrayList<String> subdirs = b.getStringArrayList("sub_dir");
        fileNames = b.getStringArrayList("file_name");

        ListView mainListView = (ListView) findViewById(R.id.mainListView);
        assert subdirs != null;
        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(this, R.layout.simple_list_item_1, subdirs);
        mainListView.setAdapter(listAdapter);

        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String fileName = fileNames.get(position);
                Log.d("clicked:", String.valueOf(position));


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
