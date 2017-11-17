package com.example.diies.gkicpo_informator;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;


/**
 * Created by DiiES on 2017-10-30.
 */

public class ShowInfoActivity extends AppCompatActivity {



    // get some fake data
    //private static final String TEST_URL                 = "http://jsonplaceholder.typicode.com/comments";
    private static final String TEST_URL                   = "http://192.168.1.200:2020/equipment/all";
    private static final String ACTION_FOR_INTENT_CALLBACK = "THIS_IS_A_UNIQUE_KEY_WE_USE_TO_COMMUNICATE";

    ProgressDialog progress;
    private TextView ourTextView;


    private static final String TAG = "Druga aktuwność";
    @Override
    protected  void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showinfo);
        Log.d(TAG, "onCreate: Start 2 ");


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNav_ViewBar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.ic_back:
                        Intent intent0 = new Intent(ShowInfoActivity.this, MainActivity.class);
                        startActivity(intent0);

                        break;
//                    case R.id.ic_showmore:
////                        Intent intent1 = new Intent(ShowInfoActivity.this, ShowInfoActivity.class);
////                        startActivity(intent1);
//                        break;
                    case R.id.ic_like:
                        Intent intent2 = new Intent(ShowInfoActivity.this, ActivityLike.class);
                       startActivity(intent2);
                        break;
                    case R.id.ic_search:
                        Intent intent3 = new Intent(ShowInfoActivity.this, ActivitySearchElement.class);
                        startActivity(intent3);

                        break;
                    case R.id.ic_autor:
                        Intent intent4 = new Intent(ShowInfoActivity.this, ActivityAutor.class);
                        startActivity(intent4);

                        break;
                    case R.id.ic_siteur:
                        Intent intent5 = new Intent(ShowInfoActivity.this, ActivitySiteUr.class);
                        startActivity(intent5);
                        break;
                }


                return false;

            }


        });

    }





}