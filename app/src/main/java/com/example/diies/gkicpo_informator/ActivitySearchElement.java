package com.example.diies.gkicpo_informator;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by DiiES on 2017-11-02.
 */

public class ActivitySearchElement extends AppCompatActivity {

    private ListView lvLanguages;
    private String[] languages;
    private String[] helloPhrases;
        private static final String TAG = "Szukaj";

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_searchelement);


            lvLanguages = (ListView) findViewById(R.id.lvLanguages);
            initResources();
            initLanguagesListView();

            Log.d(TAG, "onCreate: Start 2 ");

            BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNav_ViewBar);
            BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()) {

                        case R.id.ic_back:
                            Intent intent0 = new Intent(ActivitySearchElement.this, MainActivity.class);
                            startActivity(intent0);

                            break;
//                        case R.id.ic_showmore:
//                            Intent intent1 = new Intent(ActivitySearchElement.this, ShowInfoActivity.class);
//                            startActivity(intent1);
//                            break;
                        case R.id.ic_like:
//                        Intent intent2 = new Intent(ActivityLike.this, ActivityLike.class);
//                        startActivity(intent2);
                            break;
                        case R.id.ic_search:
                            Intent intent3 = new Intent(ActivitySearchElement.this, ActivitySearchElement.class);
                            startActivity(intent3);

                            break;
                        case R.id.ic_autor:
                            Intent intent4 = new Intent(ActivitySearchElement.this, ActivityAutor.class);
                            startActivity(intent4);

                            break;
                        case R.id.ic_siteur:
                            Intent intent5 = new Intent(ActivitySearchElement.this, ActivitySiteUr.class);
                            startActivity(intent5);
                            break;
                    }


                    return false;

                }


            });

        }


    private void initResources() {
        Resources res = getResources();
        languages = res.getStringArray(R.array.languages);
        helloPhrases = res.getStringArray(R.array.hello_phrases);
    }

    private void initLanguagesListView() {
        lvLanguages.setAdapter(new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                languages));

        lvLanguages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
                Toast.makeText(getApplicationContext(),
                        helloPhrases[pos],
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

}


