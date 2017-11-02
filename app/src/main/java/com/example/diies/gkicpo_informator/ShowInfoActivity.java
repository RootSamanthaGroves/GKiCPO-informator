package com.example.diies.gkicpo_informator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


/**
 * Created by DiiES on 2017-10-30.
 */

public class ShowInfoActivity extends AppCompatActivity {

    private static final String TAG = "Druga aktuwność";
    @Override
    protected  void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showinfo);
        Log.d(TAG, "onCreate: Start 2 ");


    }


}
