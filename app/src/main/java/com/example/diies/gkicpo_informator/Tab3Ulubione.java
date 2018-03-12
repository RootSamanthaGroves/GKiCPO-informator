package com.example.diies.gkicpo_informator;

/**
 * Created by DiiES on 2017-10-30.
 */

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.diies.gkicpo_informator.model.Equipment;

import java.io.IOException;

public class Tab3Ulubione extends Fragment {
    private static final String TAG = "Tab3 Ulubione";

    private TextView nazwa;
    private TextView opis;
    private ImageView ivObraz;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       View view = inflater.inflate(R.layout.activity_like_in_fragment,container,false);
        nazwa = (TextView) view.findViewById(R.id.tvNazwawTab);
        opis = (TextView) view.findViewById(R.id.tvOpisTab);
        ivObraz = (ImageView) view.findViewById(R.id.ivPhotoEqTab);
        String zm ="fff";
        nazwa.setText(zm);

        Equipment equipmentFromFile = FileOperation.readFromFile(getContext(), "ulubione.txt");
        nazwa.setText(equipmentFromFile.getName());
        opis.setText(equipmentFromFile.getDescription());


        Bitmap bitmap = null;
        try {
            bitmap = FileOperation.readPhotoFromFile(getContext(),"test.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }



        ivObraz.setImageBitmap(bitmap);




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

}