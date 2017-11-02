package com.example.diies.gkicpo_informator;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainAktywnosc";
    private SekcjaStatePagerAdapter mSekcjaStatePagerAdapter;
    private ViewPager mViewPager;

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

//        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.toolbarBottom);
//        BottomNavigationViewHelper.disableShiftNode(bottomNavigationView);
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()) {
//
//                    case R.id.ic_showmore:
//                        break;
//                    case R.id.ic_search:
//                        break;
//                    case R.id.ic_autor:
//                        break;
//                    case R.id.ic_siteur:
//                        break;
//                }
//
//                return false;
//            }





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
}

