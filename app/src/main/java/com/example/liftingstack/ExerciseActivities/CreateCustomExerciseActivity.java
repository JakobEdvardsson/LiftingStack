package com.example.liftingstack.ExerciseActivities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.liftingstack.Entity.ExerciseInstructions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;

import com.example.liftingstack.R;

public class CreateCustomExerciseActivity extends AppCompatActivity {
    EditText customExerciseNameInput;
    EditText customExerciseDescriptionInput;

    ImageView displayImageView;

    Button selectImageButton, saveButton, loadButton;

    String imgString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_custom_exercise);

        customExerciseNameInput = (EditText) findViewById(R.id.customExerciseNameInput);
        customExerciseDescriptionInput = (EditText) findViewById(R.id.customExerciseDescriptionInput);


        selectImageButton = findViewById(R.id.selectImageButton);
        displayImageView = findViewById(R.id.displayImageView);

        selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageChooser();
            }
        });

    }

    public void saveCustomExercise(View v) {
        ExerciseInstructions customExercise;
        String customExerciseName = customExerciseNameInput.getText().toString();
        String customExerciseDescription = customExerciseDescriptionInput.getText().toString();
        Bitmap bitmapImage = displayImageView.getDrawingCache();
        System.out.println(customExerciseName);
        if (bitmapImage == null) {
            System.out.println("No image selected");
            customExercise = new ExerciseInstructions(customExerciseName, customExerciseDescription);
        }
        else {
            System.out.println("Image selected");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte []imgByte = baos.toByteArray();

            imgString = Base64.encodeToString(imgByte, Base64.DEFAULT);
            customExercise = new ExerciseInstructions(customExerciseName, customExerciseDescription, imgString);
        }
        //String json = convertObjectToJson(customExercise);
        //System.out.println(getFilesDir());

        try {
            File file = new File(this.getFilesDir(), "Test");

            // if we want to append the saved file we can use ** new FileWriter(file, true); **
            //https://stackoverflow.com/questions/69582517/how-can-i-save-every-data-in-json-file-android-studio

            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            //bufferedWriter.write(json);
            bufferedWriter.close();
            System.out.println("File saved");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadExercise(View v) {

        File file = new File(this.getFilesDir(), "Test");
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line).append("\n");
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            String response = stringBuilder.toString();
            convertJsonToObject(response);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

 /*   public String convertObjectToJson(Object object) {
        *//*Gson gson = new Gson();
        String json = gson.toJson(object);
        System.out.println(json);
        return json;*//*
    }*/

    public void convertJsonToObject(String json) {
        /*Gson gson = new Gson();
        ExerciseInstructions exerciseInstructions = gson.fromJson(json, ExerciseInstructions.class);
        System.out.println(json);
        //visa texten i gui -- ta bort senare
        customExerciseNameInput.setText(exerciseInstructions.getName());
        customExerciseDescriptionInput.setText(exerciseInstructions.getDescription());*/
    }

    private void imageChooser() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        launchSomeActivity.launch(i);
    }

    ActivityResultLauncher<Intent> launchSomeActivity
            = registerForActivityResult(
            new ActivityResultContracts
                    .StartActivityForResult(),
            result -> {
                if (result.getResultCode()
                        == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    // do your operation from here....
                    if (data != null
                            && data.getData() != null) {
                        Uri selectedImageUri = data.getData();
                        Bitmap selectedImageBitmap;
                        try {
                            selectedImageBitmap
                                    = MediaStore.Images.Media.getBitmap(
                                    this.getContentResolver(),
                                    selectedImageUri);
                            displayImageView.setImageBitmap(
                                    selectedImageBitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
}
