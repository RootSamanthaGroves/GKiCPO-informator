package com.example.diies.gkicpo_informator;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.diies.gkicpo_informator.model.Equipment;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;


/**
 * Created by DiiES on 2017-10-30.
 */

public class ShowInfoActivity extends AppCompatActivity {


    final static String url = "https://app-informacje.herokuapp.com/equipment/name/";
    public static String codeFormat;
    public static String codeContent;


    ProgressDialog progress;

    private TextView tvNazwa;
    private ImageView ivZdjecie;
    private TextView tvOpis;
    private ImageButton btnTEST;

    private static final String TAG = "Druga aktuwność";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showinfo);
        Log.d(TAG, "onCreate: Start 2 ");

        tvNazwa = (TextView) this.findViewById(R.id.tvNazwa);
        tvOpis = (TextView) this.findViewById(R.id.tvOpis);
        tvNazwa.setText(codeContent);

        if(codeContent!= null){
            selectItem();
        }
        btnTEST = (ImageButton) this.findViewById(R.id.imageButton3);
        btnTEST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Toast.makeText(getActivity(), "Informacje", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getActivity(), ShowInfoActivity.class);
//                startActivity(intent);
            }
        });

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

    public void selectItem() {

        tvNazwa.setText(codeContent);
        System.out.println(codeContent);
        new HttpRequestTask().execute();

    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }


    private class HttpRequestTask extends AsyncTask<Void, Void, Equipment> {
        private ProgressDialog pd;
        @Override
        protected Equipment doInBackground(Void... params) {
            try {
//               progress = ProgressDialog.show();
                String urlAll = url + tvNazwa.getText().toString();
                System.out.println("qr code "+tvNazwa.getText().toString());
//             String urlAll = url +"name";


                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Equipment equipment = (Equipment) restTemplate.getForObject(urlAll, Equipment.class);

                return equipment;
            } catch (Exception e) {
                Log.e("conection with rest", e.getMessage(), e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Equipment equipment) {


            TextView nazwa = (TextView) findViewById(R.id.tvNazwa);
            TextView opis = (TextView) findViewById(R.id.tvOpis);
            nazwa.setText(String.valueOf(equipment.getName()));
            opis.setText(String.valueOf(equipment.getDescription()));
            ImageView image = (ImageView) findViewById(R.id.ivPhoto);
            Bitmap bMap = BitmapFactory.decodeByteArray(equipment.getImage(), 0,equipment.getImage().length);
            image.setImageBitmap(bMap);


        }

        }




}