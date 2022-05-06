package com.lau.finalassign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView list;
    WebView web;
    ArrayList<String> cname, clink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView) findViewById(R.id.clist);
        //web = (WebView) findViewById(R.id.web);
        cname = new ArrayList<>();
        clink = new ArrayList<>();


        try{

            SQLiteDatabase sql = this.openOrCreateDatabase("cousedb", MODE_PRIVATE, null);
            sql.execSQL("CREATE Table IF NOT EXISTS course (course_name VARCHAR, course_link VARCHAR)");
            //sql.execSQL("INSERT INTO course(course_name, course_link) VALUES ('Mobile Computing', 'https://ionicframework.com/'), ('scripting', 'https://www.regexone.com/'), ('DataBase Management Systems', 'https://www.dev.mysql.com/')");

            Cursor c = sql.rawQuery("Select * from course", null);
            int c_name_Index = c.getColumnIndex("course_name");
            int c_link_Index = c.getColumnIndex("course_link");
            c.moveToFirst();

            for (int x=0; x<c.getCount(); x++){
                cname.add(c.getString(c_name_Index));
                clink.add(c.getString(c_link_Index));
                c.moveToNext();
            }
            ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, cname);
            list.setAdapter(adapter);
        }catch(Exception e){
            e.printStackTrace();
        }


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //accidentally made it in the same page but now fixed!
                //same page
                //web.setWebViewClient(new WebViewClient());
                //web.loadUrl(clink.get(i));
                Intent intent = new Intent(getApplicationContext(), pg2.class);
                intent.putExtra("link", clink.get(i));
                startActivity(intent);
            }
        });
    }

}