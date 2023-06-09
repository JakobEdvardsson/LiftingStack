package com.example.liftingstack.ExerciseActivities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.liftingstack.Controller.ImageHandler;
import com.example.liftingstack.Entity.AllExerciseInstructions;
import com.example.liftingstack.Entity.ExerciseGraph;
import com.example.liftingstack.Entity.ExerciseInstruction;
import com.example.liftingstack.R;

import java.io.IOException;

public class ExerciseInstructionsPage extends AppCompatActivity {
    private ImageView imageView;
    private ExerciseInstruction currentExerciseInstruction;
    private AllExerciseInstructions allExerciseInstructions;
    private String idForExercise;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_instructions_page);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        allExerciseInstructions = new AllExerciseInstructions(this);
        idForExercise = getIntent().getStringExtra("ExerciseID");


        boolean exerciseFound = false;
        //Get the exercise from the list
        for (ExerciseInstruction exercise : allExerciseInstructions.getExercisesInstructionsList()) {
            if (exercise.getId().equals(idForExercise)) {
                currentExerciseInstruction = exercise;
                exerciseFound = true;
                break;
            }
        }

        if (!exerciseFound) {
            currentExerciseInstruction = new ExerciseInstruction("New Exercise", "New Description");
        }


        TextView exerciseName = findViewById(R.id.selectedProgramName);
        exerciseName.setText(currentExerciseInstruction.getExerciseName());

        EditText exerciseDescription = findViewById(R.id.ExerciseDescription);
        exerciseDescription.setText(currentExerciseInstruction.getExerciseDescription());


        imageView = findViewById(R.id.exerciseImage);
        if (currentExerciseInstruction.getImage() != null) {
            imageView.setImageBitmap(new ImageHandler().convertBase64ToBitmap(currentExerciseInstruction.getImage()));
        }


    }

    public void rotateImage(View v) {
        try {
            Matrix matrix = new Matrix();
            matrix.postRotate(90);
            ImageHandler imageHandler = new ImageHandler();
            Bitmap bitmap = imageHandler.convertBase64ToBitmap(currentExerciseInstruction.getImage());
            // Rotate the bitmap
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            currentExerciseInstruction.setImage(imageHandler.convertImageToBase64(bitmap));
            imageView.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onSaveClick(View v) {

        currentExerciseInstruction.setExerciseName(((TextView) findViewById(R.id.selectedProgramName)).getText().toString());
        currentExerciseInstruction.setExerciseDescription(((EditText) findViewById(R.id.ExerciseDescription)).getText().toString());

        if (!allExerciseInstructions.getExercisesInstructionsList().contains(currentExerciseInstruction)) {
            allExerciseInstructions.addExerciseInstructions(currentExerciseInstruction);
        } else {
            for (ExerciseInstruction exercise : allExerciseInstructions.getExercisesInstructionsList()) {
                if (exercise.getId().equals(idForExercise)) {
                    exercise.setExerciseName(currentExerciseInstruction.getExerciseName());
                    exercise.setExerciseDescription(currentExerciseInstruction.getExerciseDescription());
                    exercise.setImage(currentExerciseInstruction.getImage());
                    break;
                }
            }
        }
        allExerciseInstructions.saveExercisesInstructionsList(this);
        Toast.makeText(this, "Exercise saved", Toast.LENGTH_SHORT).show();


        finish();
    }

    public void imageChooser(View v) {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        chooseImage.launch(i);
    }

    ActivityResultLauncher<Intent> chooseImage
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
                                    selectedImageBitmap, 300, 300, false);

                            currentExerciseInstruction.setImage(new ImageHandler().convertImageToBase64(resizedBitmap));
                            imageView.setImageBitmap(resizedBitmap);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

    public void finishActivity(View v) {
        finish();
    }

    public void launchExerciseGraphFromExercisePage(View v) {
        Intent intent = new Intent(this, ExerciseGraph.class);
        intent.putExtra("ExerciseName", currentExerciseInstruction.getExerciseName());

        startActivity(intent);
    }

}