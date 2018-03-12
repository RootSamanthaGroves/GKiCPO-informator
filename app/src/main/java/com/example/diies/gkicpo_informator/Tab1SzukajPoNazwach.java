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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.diies.gkicpo_informator.model.Equipment;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class Tab1SzukajPoNazwach extends Fragment {
    private static final String TAG = "Tab1 Szukaj";
    private ImageButton btnSearch;
    private String idOfEqu = "all";
    EditText mEdit;
//    TextView greetingIdText;
    private static String path = "id/1";
    private static String url = "https://app-informacje.herokuapp.com/equipment/";
    private String urlAll = url + path;

    private ListView lvLanguages;
    private String[] languages;
    private String[] helloPhrases;

    private List<Equipment> equipmentsList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_searchelement, container, false);

        lvLanguages = (ListView) view.findViewById(R.id.lvLanguages);
        btnSearch = (ImageButton) view.findViewById(R.id.imageButton2);
        mEdit = (EditText) view.findViewById(R.id.editText2);
//        greetingIdText = (TextView) view.findViewById(R.id.tvtest);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idOfEqu = (String) mEdit.getText().toString();
                Log.d("Pobieranie", idOfEqu);

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
//                List<Equipment> equipmentsList = new ArrayList<>();
                for (Equipment e : equipmentsArray) {
                    equipmentsList.add(e);
                    System.out.println(e.toString());
                }
                return equipmentsList;
            } catch (Exception e) {
                Log.e("Pobieranie danych", e.getMessage(), e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Equipment> equipments) {



            lvLanguages.setAdapter(new ArrayAdapter<Equipment>(
                    getActivity().getApplicationContext(),
                    android.R.layout.simple_list_item_1,
                    equipmentsList));

            lvLanguages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
                    Toast.makeText(getActivity().getApplicationContext(),
                            helloPhrases[pos],
                            Toast.LENGTH_SHORT).show();

                }


            });
        }
    }
}





