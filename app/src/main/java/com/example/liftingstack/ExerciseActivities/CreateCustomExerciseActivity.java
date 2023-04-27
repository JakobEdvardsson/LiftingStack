package com.example.liftingstack.ExerciseActivities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.liftingstack.Controller.LoadFromDevice;
import com.example.liftingstack.Controller.SaveToDevice;
import com.example.liftingstack.Entity.ExerciseInstructions;
import com.example.liftingstack.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class CreateCustomExerciseActivity extends AppCompatActivity {
    EditText customExerciseNameInput;
    EditText customExerciseDescriptionInput;

    ImageView displayImageView;
    Button selectImageButton, saveButton, loadButton;
    String imgString;

    ArrayList<ExerciseInstructions> listAllExercises = new ArrayList<>();

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
    public void testWriteAndReadJson(View v){
        //
        ArrayList<ExerciseInstructions> allExerciseInstructions = new ArrayList<>();
        for (int i = 0; i < 50; i++){
            allExerciseInstructions.add(new ExerciseInstructions("test" + i, "test"));
        }
        //
        new SaveToDevice().saveListToDevice(allExerciseInstructions,this, "Test");
        //

        ArrayList<ExerciseInstructions> list = new LoadFromDevice().loadListFromDevice(this,"Test");

        for (int i = 0; i < list.size(); i++){
            System.out.println("xxxxxxxxxxxxxxxx" + list.get(i).getExerciseName());
        }
        //
    }

    public void testWriteAndReadJson1(View v){
        //
        ArrayList<ExerciseInstructions> allExerciseInstructions = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            allExerciseInstructions.add(new ExerciseInstructions("test" + i, "test"));
        }
        //
        String json = new Gson().toJson(allExerciseInstructions);
        //

        //
        File file = new File(this.getFilesDir(), "Test");
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(json);
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //
        //
        ArrayList<ExerciseInstructions> list;
        try {

            BufferedReader reader = new BufferedReader(new FileReader(file));

            // read the contents of the file as a string
            StringBuilder jsonString = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }

            // close the reader
            reader.close();


            Gson g = new Gson();
            Type listType = new TypeToken<ArrayList<ExerciseInstructions>>(){}.getType();
            list = g.fromJson(String.valueOf(jsonString), listType);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < list.size(); i++){
            System.out.println("xxxxxxxxxxxxxxxx" + list.get(i).getExerciseName());
        }

    }



    public void saveCustomExercise(View v) {
        Bitmap bitmapImage = null;
        ExerciseInstructions customExercise;
        String customExerciseName = customExerciseNameInput.getText().toString();
        String customExerciseDescription = customExerciseDescriptionInput.getText().toString();
        // bitmapImage = displayImageView.getDrawingCache(); // always returns null
        // bitmapImage =((BitmapDrawable)displayImageView.getDrawable()).getBitmap(); // crashes if no image is selected

        if (displayImageView.getDrawable() instanceof BitmapDrawable) {
            bitmapImage = ((BitmapDrawable) displayImageView.getDrawable()).getBitmap();
        }
        System.out.println(customExerciseName);

        System.out.println(bitmapImage);
        if(bitmapImage == null){

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
        // TODO check if savefile already exists, if so, load the list from there.
        listAllExercises = loadExercise(v);
        listAllExercises.add(customExercise);

        String json = convertObjectToJson(listAllExercises);
        // Test parse Json array enl: https://howtodoinjava.com/gson/gson-parse-json-array/
        /*ExerciseInstructions[] userArray = new Gson().fromJson(json, ExerciseInstructions[].class);
        Type listType = new TypeToken<ArrayList<ExerciseInstructions>>(){}.getType();

        ArrayList<ExerciseInstructions> list = userArray.fromJson(json, listType);*/
        // slut
        System.out.println(getFilesDir());

        try {
            File file = new File(this.getFilesDir(), "Test");

            // if we want to append the saved file we can use ** new FileWriter(file, true); **
            //https://stackoverflow.com/questions/69582517/how-can-i-save-every-data-in-json-file-android-studio

            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(json);
            bufferedWriter.close();

            System.out.println("File saved");
            Log.i("Savetest", json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ExerciseInstructions> loadExercise(View v) {

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
            System.out.println("response");
            Log.i("loadtest", response);
            return convertJsonToObject(response);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String convertObjectToJson(Object object) {
        Gson gson = new Gson();
        String json = gson.toJson(object);
        System.out.println(json);
        return json;
    }

    public ArrayList<ExerciseInstructions> convertJsonToObject(String json) {
        Gson gson = new Gson();
        Type userListType = new TypeToken<ArrayList<ExerciseInstructions>>(){}.getType();
        // listAllExercises = gson.fromJson(json, userListType);
        ArrayList<ExerciseInstructions> testList = gson.fromJson(json, userListType);
        //visa texten i gui -- ta bort senare
        // testa typecasta till ExerciseInstructions
        Log.i("Testdisplay", "-3");
        // raden nedan funkar ej, appen kraschar
        ExerciseInstructions displayObj = ((ExerciseInstructions)testList.get(0));
        Log.i("Testdisplay", "-2");
        displayObjectOnScreen(displayObj);
        Log.i("Testdisplay", "-1");
        return testList;
    }

    public void displayObjectOnScreen(Object object) {
        Log.i("Testdisplay", "1");
        Log.i("Testdisplay", String.valueOf(object.getClass()));
        if(object instanceof ExerciseInstructions) {
            Log.i("Testdisplay", "2");


            ExerciseInstructions exerciseInstructions = ((ExerciseInstructions) object);

            customExerciseNameInput.setText(exerciseInstructions.getExerciseName());
            customExerciseDescriptionInput.setText(exerciseInstructions.getExerciseDescription());
            String imageString = exerciseInstructions.getImage();
            StringToBitMap(imageString);
            displayImageView.setImageBitmap(StringToBitMap(imageString));
            Log.i("Testdisplay", "3");

        }
    }
    public Bitmap StringToBitMap(String encodedString){
        try{
            byte [] encodeByte = Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }
        catch(Exception e){
            e.getMessage();
            return null;
        }
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


//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
// Old working code for saving every exercise as an object.
// Every save overwrites the previous file.
// Not implemented: appending exercises to list of object or to json file.
//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
/*

package com.example.liftingstack.ExerciseActivities;

        import androidx.activity.result.ActivityResultLauncher;
        import androidx.activity.result.contract.ActivityResultContracts;
        import androidx.appcompat.app.AppCompatActivity;

        import android.app.Activity;
        import android.content.Intent;
        import android.graphics.Bitmap;

        import android.graphics.BitmapFactory;
        import android.graphics.drawable.BitmapDrawable;


        import android.net.Uri;
        import android.os.Bundle;
        import android.provider.MediaStore;
        import android.util.Base64;
        import android.util.Log;
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

        import com.google.gson.Gson;
        import com.google.gson.GsonBuilder;

        import com.example.liftingstack.R;
        import com.google.gson.Gson;

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
        Bitmap bitmapImage = null;
        ExerciseInstructions customExercise;
        String customExerciseName = customExerciseNameInput.getText().toString();
        String customExerciseDescription = customExerciseDescriptionInput.getText().toString();
        // bitmapImage = displayImageView.getDrawingCache(); // always returns null
        // bitmapImage =((BitmapDrawable)displayImageView.getDrawable()).getBitmap(); // crashes if no image is selected

        if (displayImageView.getDrawable() instanceof BitmapDrawable) {
            bitmapImage = ((BitmapDrawable) displayImageView.getDrawable()).getBitmap();
        }
        System.out.println(customExerciseName);

        System.out.println(bitmapImage);
        if(bitmapImage == null){

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
        String json = convertObjectToJson(customExercise);
        System.out.println(getFilesDir());

        try {
            File file = new File(this.getFilesDir(), "Test");

            // if we want to append the saved file we can use ** new FileWriter(file, true); **
            //https://stackoverflow.com/questions/69582517/how-can-i-save-every-data-in-json-file-android-studio

            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(json);
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
            System.out.println("response");
            Log.i("load", response);
            convertJsonToObject(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String convertObjectToJson(Object object) {
        Gson gson = new Gson();
        String json = gson.toJson(object);
        System.out.println(json);
        return json;
    }

    public void convertJsonToObject(String json) {
        Gson gson = new Gson();
        ExerciseInstructions exerciseInstructions = gson.fromJson(json, ExerciseInstructions.class);
        System.out.println(json);
        //visa texten i gui -- ta bort senare
        displayObjectOnScreen(exerciseInstructions);


    }

    public void displayObjectOnScreen(Object object) {
        if(object instanceof ExerciseInstructions) {
            ExerciseInstructions exerciseInstructions = ((ExerciseInstructions) object);

            customExerciseNameInput.setText(exerciseInstructions.getExerciseName());
            customExerciseDescriptionInput.setText(exerciseInstructions.getExerciseDescription());
            String imageString = exerciseInstructions.getImage();
            StringToBitMap(imageString);
            displayImageView.setImageBitmap(StringToBitMap(imageString));
        }
    }
    public Bitmap StringToBitMap(String encodedString){
        try{
            byte [] encodeByte = Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }
        catch(Exception e){
            e.getMessage();
            return null;
        }
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
*/
