package com.lau.finalassign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class pg2 extends AppCompatActivity {
    WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pg2);
        web = (WebView) findViewById(R.id.web);
        Intent getIntent = getIntent();
        String link = getIntent.getStringExtra("link");
        web.setWebViewClient(new WebViewClient());
        web.loadUrl(link);

    }

}