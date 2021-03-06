package com.example.diies.gkicpo_informator;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

/**
 * Created by DiiES on 2017-11-02.
 */

public class ActivityAutor extends AppCompatActivity {

    private static final String TAG = "Autor";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autor);
        Log.d(TAG, "onCreate: Start 2 ");


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNav_ViewBar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.ic_back:
                        Intent intent0 = new Intent(ActivityAutor.this, MainActivity.class);
                        startActivity(intent0);

                        break;
//                    case R.id.ic_showmore:
//                        Intent intent1 = new Intent(ActivityAutor.this, ShowInfoActivity.class);
//                        startActivity(intent1);
//                        break;
                    case R.id.ic_like:
                        Intent intent2 = new Intent(ActivityAutor.this, ActivityLike.class);
                        startActivity(intent2);
                        break;
                    case R.id.ic_search:
                        Intent intent3 = new Intent(ActivityAutor.this, ActivitySearchElement.class);
                        startActivity(intent3);

                        break;
                    case R.id.ic_autor:
//                        Intent intent5 = new Intent(ActivityLike.this, ActivitySiteUr.class);
//                        startActivity(intent5);

                        break;
                    case R.id.ic_siteur:
                        Intent intent5 = new Intent(ActivityAutor.this, ActivitySiteUr.class);
                        startActivity(intent5);
                        break;
                }


                return false;

            }


        });

    }
}