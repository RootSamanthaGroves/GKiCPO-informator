package com.example.diies.gkicpo_informator;

/**
 * Created by DiiES on 2017-10-30.
 */

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import org.apache.http.client.methods.HttpGet;

import java.net.URI;

public class Tab1SzukajPoNazwach extends Fragment {
    private static final String TAG = "Tab1 Szukaj";

    private ImageButton btnSearch;

    // get some fake data
    //private static final String TEST_URL                 = "http://jsonplaceholder.typicode.com/comments";
    private static final String TEST_URL                   = "http://192.168.1.200:2020/equipment/all";
    private static final String ACTION_FOR_INTENT_CALLBACK = "THIS_IS_A_UNIQUE_KEY_WE_USE_TO_COMMUNICATE";



    ProgressDialog progress;
    private TextView ourTextView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_searchelement,container,false);
//        btnSearch = (ImageButton) view.findViewById(R.id.imageButton2);
//
//        btnSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                Toast.makeText(getActivity(), "dsd", Toast.LENGTH_SHORT).show();
//
//            }
//        });


        return view;
    }


    /**
     * any code to access activity fields must be handled in this method.
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ourTextView = (TextView)getActivity().findViewById(R.id.myTextView);
        getContent();
    }

    private void getContent() {
        // the request
        try {
            HttpGet httpGet = new HttpGet(new URI(TEST_URL));
            RestTask task = new RestTask(getActivity(), ACTION_FOR_INTENT_CALLBACK);
            task.execute(httpGet);
            progress = ProgressDialog.show(getActivity(), "Getting Data ...", "Waiting For Results...", true);

        }
        catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(receiver, new IntentFilter(ACTION_FOR_INTENT_CALLBACK));
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(receiver);
    }

    /**
     * Our Broadcast Receiver. We get notified that the data is ready, and then we
     * put the content we receive (a string) into the TextView.
     */
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // clear the progress indicator
            if (progress != null) {
                progress.dismiss();
            }
            String response = intent.getStringExtra(RestTask.HTTP_RESPONSE);
            ourTextView.setText(response);
            Log.i(TAG, "RESPONSE = " + response);
            //
            // my old json code was here. this is where you would parse it.
            //
        }
    };


}