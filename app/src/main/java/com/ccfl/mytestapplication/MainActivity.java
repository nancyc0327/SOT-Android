package com.ccfl.mytestapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;

import com.example.mytestapplication.R;


public class MainActivity extends AppCompatActivity{

    DirectoryModel mDirs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDirs= new DirectoryModel();
        //Button mClickButton1 = (Button)findViewById(R.id.button1);
        //mClickButton1.setOnClickListener(this);

    }

    public void go_to_search(View v) {
        Intent intent = new Intent(this, ContactSearch.class);
        startActivity(intent);
    }

    public void list_sub_directory(View v){
        Button mClickButton = (Button)findViewById(v.getId());
        Intent intent = new Intent(this, DirectoryPage.class);
        Bundle b = new Bundle();
        String mainDir = mClickButton.getText().toString();
        mainDir = mainDir.replace('\n',' ');
        b.putString("main_dir",mainDir);
        b.putStringArrayList("sub_dir",mDirs.getSubDirName(mainDir));
        b.putStringArrayList("file_name", mDirs.getFileNameList(mainDir));
        intent.putExtras(b);

        startActivity(intent);
    }

    public void open_quality_child_care(View v){
        String url = "file:///android_asset/Quality-Child-Care.html";
        Intent intent = new Intent(this, WebPage.class);
        Bundle b = new Bundle();
        b.putString("show_page", url);
        b.putString("title","Choosing Quality Child Care");
        intent.putExtras(b);
        startActivity(intent);
    }

    public void open_about(View v) {
        String url = "file:///android_asset/about.html";
        Intent intent = new Intent(this, WebPage.class);
        Bundle b = new Bundle();
        b.putString("show_page", url);
        b.putString("title","About EDN");
        intent.putExtras(b);
        startActivity(intent);
    }

    public void open_resources(View v){
        String url = "file:///android_asset/resources.html";
        Intent intent = new Intent(this, WebPage.class);
        Bundle b = new Bundle();
        b.putString("show_page", url);
        b.putString("title","More Resources");
        intent.putExtras(b);
        startActivity(intent);
    }

    public void open_credits(View v) {
        String url = "file:///android_asset/credits.html";
        Intent intent = new Intent(this, WebPage.class);
        Bundle b = new Bundle();
        b.putString("show_page", url);
        b.putString("title","Credits");
        intent.putExtras(b);
        //intent.putExtra("directory_model", mDirs);
        startActivity(intent);
    }
}
