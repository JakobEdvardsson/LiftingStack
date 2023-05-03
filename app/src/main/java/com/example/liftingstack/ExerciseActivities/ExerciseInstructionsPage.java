package com.example.liftingstack.ExerciseActivities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.liftingstack.Controller.ImageHandler;
import com.example.liftingstack.Entity.ExerciseInstructions;
import com.example.liftingstack.R;

import java.io.IOException;

public class ExerciseInstructionsPage extends AppCompatActivity{
    private ImageView imageView;
    private ExerciseInstructions currentExerciseInstruction;
    private String imageBase64;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_instructions_page);

        currentExerciseInstruction = getIntent().getParcelableExtra("Exercise");
        String image = getIntent().getStringExtra("image");

        TextView exerciseName = findViewById(R.id.selectedProgramName);
        exerciseName.setText(currentExerciseInstruction.getExerciseName());

        EditText exerciseDescription = findViewById(R.id.ExerciseDescription);
        exerciseDescription.setText(currentExerciseInstruction.getExerciseDescription());

        imageView = findViewById(R.id.exerciseImage);
        if (image != null) {
            imageView.setImageBitmap(new ImageHandler().convertBase64ToBitmap(image));
        }

    }

    public void onSaveClick(View v) {
        currentExerciseInstruction.setExerciseName(((TextView) findViewById(R.id.selectedProgramName)).getText().toString());
        currentExerciseInstruction.setExerciseDescription(((EditText) findViewById(R.id.ExerciseDescription)).getText().toString());
        currentExerciseInstruction.setImage(imageBase64);

        Intent resultIntent = new Intent();
        resultIntent.putExtra("exercise", currentExerciseInstruction);
        resultIntent.putExtra("image", currentExerciseInstruction.getImage());

        setResult(78, resultIntent);
        finish();
    }

    public void imageChooser(View v) {
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
                            //Resizing the Bitmap to fit the ImageView
                            Bitmap resizedBitmap = Bitmap.createScaledBitmap(
                                    selectedImageBitmap, 100, 100, false);
                            imageBase64= new ImageHandler().convertImageToBase64(resizedBitmap);
                            imageView.setImageBitmap(resizedBitmap);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
}