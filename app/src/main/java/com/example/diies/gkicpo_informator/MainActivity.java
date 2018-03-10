package com.example.diies.gkicpo_informator;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.diies.gkicpo_informator.model.Equipment;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainAktywnosc";
    private SekcjaStatePagerAdapter mSekcjaStatePagerAdapter;
    private ViewPager mViewPager;
public Equipment equipmentG ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mSekcjaStatePagerAdapter = new SekcjaStatePagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_camera_black_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_search_black);
        tabLayout.getTabAt(2).setIcon(R.drawable.image);


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNav_ViewBar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

//                    case R.id.ic_showmore:
//                        Intent intent0 = new Intent(MainActivity.this, ShowInfoActivity.class);
//                        startActivity(intent0);
//                        break;
                    case R.id.ic_back:
//                        Intent intent1 = new Intent(MainActivity.this, ShowInfoActivity.class);
//                        startActivity(intent1);
                        break;
                    case R.id.ic_like:
                        Intent intent2 = new Intent(MainActivity.this, ActivityLike.class);
                        startActivity(intent2);
                        break;
                    case R.id.ic_search:
                        Intent intent3 = new Intent(MainActivity.this, ActivitySearchElement.class);
                        startActivity(intent3);

                        break;
                    case R.id.ic_autor:
                        Intent intent4 = new Intent(MainActivity.this, ActivityAutor.class);
                        startActivity(intent4);
                        break;
                    case R.id.ic_siteur:
                        Intent intent5 = new Intent(MainActivity.this, ActivitySiteUr.class);
                        startActivity(intent5);
                        break;
                }


                return false;

            }




});
}
    public void  setViewPager(int fragmentNumber){
        mViewPager.setCurrentItem(fragmentNumber);
    }




    private void setupViewPager(ViewPager mViewPager) {

        SekcjaStatePagerAdapter adapter = new SekcjaStatePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1Scan());
        adapter.addFragment(new Tab1SzukajPoNazwach());
        adapter.addFragment(new Tab3Ulubione());
        mViewPager.setAdapter(adapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = com.google.zxing.integration.android.IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        ShowInfoActivity.codeContent = scanningResult.getContents();
        ShowInfoActivity.codeFormat = scanningResult.getFormatName();
        if( ShowInfoActivity.codeContent != null){
            Intent intent3 = new Intent(MainActivity.this, ShowInfoActivity.class);
            startActivity(intent3);

        }

    }
}

