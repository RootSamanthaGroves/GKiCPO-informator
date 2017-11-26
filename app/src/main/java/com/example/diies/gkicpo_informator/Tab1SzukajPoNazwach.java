package com.example.diies.gkicpo_informator;

/**
 * Created by DiiES on 2017-10-30.
 */

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.diies.gkicpo_informator.model.Equipment;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tab1SzukajPoNazwach extends Fragment {
    private static final String TAG = "Tab1 Szukaj";
    private ImageButton btnSearch;
    private String idOfEqu = "all";
    EditText mEdit;
    TextView greetingIdText;
    private static String path = "id/1";
    private static String url = "https://app-informacje.herokuapp.com/equipment/";
    private String urlAll = url + path;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_searchelement, container, false);


        btnSearch = (ImageButton) view.findViewById(R.id.imageButton2);
        mEdit = (EditText) view.findViewById(R.id.editText2);
        greetingIdText = (TextView) view.findViewById(R.id.tvtest);
//        list = (ListView) this.findViewById(R.id.);
//
//        String cars[] = {"Mercedes", "Fiat", "Ferrari", "Aston Martin", "Lamborghini", "Skoda", "Volkswagen", "Audi", "Citroen"};
//
//        ArrayList<String> carL = new ArrayList<String>();
//        carL.addAll( Arrays.asList(cars) );
//
//        adapter = new ArrayAdapter<String>(this, R.layout.row, carL);

//        list.setAdapter(adapter);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idOfEqu = (String) mEdit.getText().toString();
                Log.d("id", idOfEqu);

                new HttpRequestTask().execute();

                Toast.makeText(getActivity(), " id", Toast.LENGTH_SHORT).show();

            }

        });


        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    private class HttpRequestTask extends AsyncTask<Void, Void, List<Equipment>> {
        @Override
        protected List<Equipment> doInBackground(Void... params) {
            try {
                urlAll = url + mEdit.getText().toString();
                RestTemplate restTemplate = new RestTemplate();

                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                ResponseEntity<Equipment[]> responseEntity = restTemplate.getForEntity(urlAll, Equipment[].class);
                Equipment[] equipmentsArray = responseEntity.getBody();
                MediaType contentType = responseEntity.getHeaders().getContentType();
                HttpStatus statusCode = responseEntity.getStatusCode();
                List<Equipment> equipmentsList = new ArrayList<>();
                for (Equipment e : equipmentsArray) {
                    equipmentsList.add(e);
                }
                return equipmentsList;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Equipment> equipments) {


            String cars[] = {"Mercedes", "Fiat", "Ferrari", "Aston Martin", "Lamborghini", "Skoda", "Volkswagen", "Audi", "Citroen"};

            ArrayList<String> carL = new ArrayList<String>();
            carL.addAll(Arrays.asList(cars));

            if (equipments.get(0).getName() == null) {
                greetingIdText.setText("null");
            }
            greetingIdText.setText(String.valueOf(equipments.get(0).getName()));
        }
    }


    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
//    /**
//     * any code to access activity fields must be handled in this method.
//     */
//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        ourTextView = (TextView) getActivity().findViewById(R.id.myTextView);
//        getContent();
//    }
//
//    private void getContent() {
//        // the request
//        try {
//
//            HttpGet httpGet = new HttpGet(new URI(TEST_URL+idOfEqu));
//            RestTask task = new RestTask(getActivity(), ACTION_FOR_INTENT_CALLBACK);
//            task.execute(httpGet);
//
////            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
////            Feature feature = (Feature) restTemplate.getForObject(url, Feature.class);
//            progress = ProgressDialog.show(getActivity(), "Getting Data ...", "Waiting For Results...", true);
//
//        } catch (Exception e) {
//            Log.e(TAG, e.getMessage());
//        }
//
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        getActivity().registerReceiver(receiver, new IntentFilter(ACTION_FOR_INTENT_CALLBACK));
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        getActivity().unregisterReceiver(receiver);
//    }
//
//    /**
//     * Our Broadcast Receiver. We get notified that the data is ready, and then we
//     * put the content we receive (a string) into the TextView.
//     */
//    private BroadcastReceiver receiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            // clear the progress indicator
//            if (progress != null) {
//                progress.dismiss();
//            }
//            String response = intent.getStringExtra(RestTask.HTTP_RESPONSE);
//
//            ourTextView.setText(response);
//
//
//
//
//            Log.i(TAG, "RESPONSE = " + response);
//            //
//            // my old json code was here. this is where you would parse it.
//            //
//        }
//    };


}