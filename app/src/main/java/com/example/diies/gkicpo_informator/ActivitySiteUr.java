package com.example.diies.gkicpo_informator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.MenuItem;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by DiiES on 2017-11-02.
 */

public class ActivitySiteUr  extends AppCompatActivity {

    private static final String TAG = "Przejdż do strony";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siteur);
        Log.d(TAG, "onCreate: Start 2 ");
        WebView myWebView = (WebView) findViewById(R.id.wvUR);
        myWebView.setPadding(0, 0, 0, 0);
        myWebView.setInitialScale(getScale());
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
//        myWebView.loadUrl("http://www.ur.edu.pl");
        myWebView.loadUrl("http://www.ur.edu.pl/wydzialy/matematyczno-przyrodnicA-centrum/7-laboratorium-informatyki-stosowanej/7-7-pracownia-grafiki-komputerowej-i-cyfrowego-przetwarzania-obrazow");

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNav_ViewBar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.ic_back:
                        Intent intent0 = new Intent(ActivitySiteUr.this, MainActivity.class);
                        startActivity(intent0);
                        break;
//                    case R.id.ic_showmore:
//                        Intent intent1 = new Intent(ActivitySiteUr.this, ShowInfoActivity.class);
//                        startActivity(intent1);
//                        break;
                    case R.id.ic_like:
                        Intent intent2 = new Intent(ActivitySiteUr.this, ActivityLike.class);
                        startActivity(intent2);
                        break;
                    case R.id.ic_search:
                        Intent intent3 = new Intent(ActivitySiteUr.this, ActivitySearchElement.class);
                        startActivity(intent3);
                        break;
                    case R.id.ic_autor:
                        Intent intent4 = new Intent(ActivitySiteUr.this, ActivityAutor.class);
                        startActivity(intent4);
                        break;
                    case R.id.ic_siteur:
//                        Intent intent5 = new Intent(ActivitySiteUr.this, ActivitySiteUr.class);
//                        startActivity(intent5);
                        break;
                }
                return false;
            }


        });
    }
    private int getScale(){
        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int width = display.getWidth();
        Double val = new Double(width)/new Double(display.getHeight()/2);
        val = val * 100d;
        return val.intValue();
    }
}