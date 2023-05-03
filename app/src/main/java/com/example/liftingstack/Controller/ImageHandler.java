package com.example.liftingstack.Controller;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class ImageHandler {

    public String convertImageToBase64(Bitmap image) {
        //Make image smaller
        Bitmap bitmap = Bitmap.createScaledBitmap(image, 100, 100, true);
        //Convert image to byte array as JPEG
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imgByte = baos.toByteArray();
        //Convert byte array to Base64 string
        return Base64.encodeToString(imgByte, Base64.DEFAULT);
    }

    public Bitmap convertBase64ToBitmap(String base64String) {
        byte[] decodedString = Base64.decode(base64String, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }
}
