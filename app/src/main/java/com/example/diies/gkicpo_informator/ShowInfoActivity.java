package com.example.diies.gkicpo_informator;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
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
import android.widget.Toast;

import com.example.diies.gkicpo_informator.model.Equipment;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayOutputStream;


/**
 * Created by DiiES on 2017-10-30.
 */

public class ShowInfoActivity extends AppCompatActivity {


    final static String url = "https://app-informacje.herokuapp.com/equipment/name/";
    public static String codeFormat;
    public static String codeContent;
     public static  FileOperation fo;

    ProgressDialog progress;

    private TextView tvNazwa;
    private ImageView ivZdjecie;
    private TextView tvOpis;
    private ImageButton btnTEST;
    public Equipment equipmentToSave;
    static Bitmap bMap = null;
    private static final String TAG = "Druga aktuwność";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showinfo);
        Log.d(TAG, "onCreate: Start 2 ");

        Context context = getApplicationContext();
        Toast toast = Toast.makeText( context, "Trwa pobieranie danych ...", Toast.LENGTH_SHORT);
        toast.show();

        tvNazwa = (TextView) this.findViewById(R.id.tvNazwa);
        tvOpis = (TextView) this.findViewById(R.id.tvOpis);
        tvNazwa.setText(codeContent);

        if (codeContent != null) {
            selectItem();
        }
        btnTEST = (ImageButton) this.findViewById(R.id.imageButton3);
        btnTEST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView nazwaToSave = (TextView) findViewById(R.id.tvNazwa);
                TextView opisToSave = (TextView) findViewById(R.id.tvOpis);
                ImageView imageEq = (ImageView) findViewById(R.id.ivPhoto);
                Bitmap bitmap = ((BitmapDrawable) imageEq.getDrawable()).getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] imageInByte = baos.toByteArray();

                equipmentToSave = new Equipment(nazwaToSave.getText().toString(), opisToSave.getText().toString(),imageInByte );
//                System.out.println(imageInByte.toString());
                FileOperation.writeToFile(equipmentToSave,  getBaseContext(), "ulubione.txt", false);
                FileOperation.writeToFile(equipmentToSave,getBaseContext(),"photo.png", true);
//                FileOperation.bitmapToFile(bMap,   "photo.txt", true);
                Context context = getApplicationContext();
//             fo = new FileOperation(imageInByte);

                Toast toast = Toast.makeText(context, "Dodano do ulubionych", Toast.LENGTH_SHORT);
                toast.show();
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
//        System.out.println(codeContent);
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


        @Override
        protected Equipment doInBackground(Void... params) {
            try {

//               progress = ProgressDialog.show();
                String urlAll = url + tvNazwa.getText().toString();
              //  System.out.println("qr code " + tvNazwa.getText().toString());
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

            byte[] test = equipment.getImage();

            System.out.println("show info activity "+test.length+ " "+ test[0]);
            bMap = BitmapFactory.decodeByteArray(equipment.getImage(), 0, equipment.getImage().length);
            image.setImageBitmap(bMap);
//            System.out.println(equipment.getImage() + "  -----------------Image z showInfo");

        }
    }
}