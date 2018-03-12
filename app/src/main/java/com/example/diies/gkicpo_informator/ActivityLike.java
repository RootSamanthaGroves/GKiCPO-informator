package com.example.diies.gkicpo_informator;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.diies.gkicpo_informator.model.Equipment;

import java.io.IOException;

/**
 * Created by DiiES on 2017-11-02.
 */

public class ActivityLike extends AppCompatActivity {

    private static final String TAG = "Ulubione";
    static Equipment  equipmentFromFile ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like);
        Log.d(TAG, "onCreate: Start 2 ");

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNav_ViewBar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        TextView tvFileContent = (TextView) findViewById(R.id.tvFromFile);
        TextView tvDescriptionContent = (TextView) findViewById(R.id.tvOpis);
         equipmentFromFile = FileOperation.readFromFile(getBaseContext(), "ulubione.txt");
        tvFileContent.setText(equipmentFromFile.getName());
        tvDescriptionContent.setText(equipmentFromFile.getDescription());


        Bitmap bitmap = null;
        try {
            bitmap = FileOperation.readPhotoFromFile(getBaseContext(),"test.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        ImageView image = (ImageView) findViewById(R.id.ivPhotoEq);
        image.setImageBitmap(bitmap);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.ic_back:
                        Intent intent0 = new Intent(ActivityLike.this, MainActivity.class);
                        startActivity(intent0);

                        break;
//                    case R.id.ic_showmore:
//                        Intent intent1 = new Intent(ActivityLike.this, ShowInfoActivity.class);
//                        startActivity(intent1);
//                        break;
                    case R.id.ic_like:
//                        Intent intent2 = new Intent(ActivityLike.this, ActivityLike.class);
//                        startActivity(intent2);
                        break;
                    case R.id.ic_search:
                        Intent intent3 = new Intent(ActivityLike.this, ActivitySearchElement.class);
                        startActivity(intent3);

                        break;
                    case R.id.ic_autor:
                        Intent intent4 = new Intent(ActivityLike.this, ActivityAutor.class);
                        startActivity(intent4);

                        break;
                    case R.id.ic_siteur:
                        Intent intent5 = new Intent(ActivityLike.this, ActivitySiteUr.class);
                        startActivity(intent5);
                        break;
                }
                return false;
            }
        });
    }
}