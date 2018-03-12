package com.example.diies.gkicpo_informator;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.diies.gkicpo_informator.model.Equipment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by Dii on 2018-03-08.
 */

class FileOperation {



    public static void writeToFile(Equipment data, Context context, String nameFile, boolean isImage) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(nameFile, Context.MODE_PRIVATE));

            if (!isImage) {
//                Equipment data = (Equipment) data1;
                outputStreamWriter.write("name:" + data.getName().toString() + "desctiptions:" + data.getDescription().toString());
                outputStreamWriter.close();
            } else {
//                equ = data.getImage();
//                FileOutputStream out = null;
//
//byte[] test = data.getImage();
//
//                System.out.println(test.length+ " "+ test[0]);
//                String fileName = "123456789" + ".png";
////                System.out.println(fileName);
//                try {
//
//                    outputStreamWriter.write(String.valueOf(data.getImage()));
//                    outputStreamWriter.close();
//                    OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput(fileName, Context.MODE_PRIVATE));
//
////                    osw.write(data.getImage().toString());
//                    Log.i(TAG, "Image stored at: " + fileName);
//                } catch (Exception e) {
//                    Log.w(TAG, e.toString());
//                    fileName = null;
//                }

                Bitmap bitmap =  BitmapFactory.decodeByteArray(data.getImage(), 0, data.getImage().length);
                //  private String saveToInternalStorage(Bitmap bitmapImage){
                ContextWrapper cw = new ContextWrapper(context);
                // path to /data/data/yourapp/app_data/imageDir
                File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
                // Create imageDir
                File mypath=new File(directory,"profile.jpg");
                System.out.println(" zapisywanie zdjecia");
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(mypath);
                    // Use the compress method on the BitMap object to write image to the OutputStream
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                //return directory.getAbsolutePath();



            }
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }






    public static Equipment readFromFile(Context context, String nameFile) {

        Equipment equipmentFromFile = new Equipment();
        String ret = "";


        try {
            InputStream inputStream = context.openFileInput(nameFile);

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();
                System.out.println("===================================================================");
                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                  //  System.out.println(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }


        equipmentFromFile.setName(ret.substring(5, ret.indexOf("desctiptions:")));
        equipmentFromFile.setDescription(ret.substring(ret.indexOf("desctiptions:") + 13));


        // image







        return equipmentFromFile;
    }

    public static Bitmap readPhotoFromFile(Context cw, String nameFile) throws IOException {
        // path to /data/data/yourapp/app_data/imageDir
       Bitmap b;
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
//        File mypath=new File(directory,"profile.jpg");
        System.out.println(" odczytywanie zdjecia");
        try {
            File f=new File(directory, "profile.jpg");
             b = BitmapFactory.decodeStream(new FileInputStream(f));
//            ImageView img=(ImageView)findViewById(R.id.imgPicker);
//            img.setImageBitmap(b);
        }
        catch (FileNotFoundException e)
        {     b = null;
            e.printStackTrace();
        }



        return b;
    }

    public static Bitmap  readFromPhoto(Context context, String nameFile) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput(nameFile);
            System.out.println(inputStream.toString());
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();
                System.out.println("===================================================================");
                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }


        Bitmap b = BitmapFactory.decodeByteArray(ret.getBytes(), 0, ret.getBytes().length);



        return  b;
    }

}
