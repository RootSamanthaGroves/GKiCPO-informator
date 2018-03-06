package com.example.diies.gkicpo_informator;

/**
 * Created by DiiES on 2017-10-30.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;

public class Tab1Scan extends Fragment {

    private static final String Tag = "Tab Scan";
    private Button btnTEST;
    private ImageButton scanQR;

    public static String text = "";
    private TextView tvtest;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scan, container, false);

        tvtest = (TextView) view.findViewById(R.id.textView2);
        scanQR = (ImageButton) view.findViewById(R.id.scanCode);
        scanQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//to wywołuje aparat aby zeskanować qr cod
                IntentIntegrator integrator = new IntentIntegrator(getActivity());
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);// ustawiasz typ skanowanego QR koda albo paskowego
                integrator.setCameraId(0);
                integrator.setBeepEnabled(true);
                integrator.setBarcodeImageEnabled(false); // czy ma skanowąc same QR - false a jeśli ma skanować paskowe to true
                integrator.initiateScan();



            }
        });


        return view;
    }


}