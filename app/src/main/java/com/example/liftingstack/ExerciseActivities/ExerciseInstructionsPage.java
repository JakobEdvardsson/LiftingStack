package com.example.liftingstack.ExerciseActivities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.liftingstack.R;

import java.io.IOException;

public class ExerciseInstructionsPage extends AppCompatActivity{
    Intent resultIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_instructions_page);

        TextView exerciseName = findViewById(R.id.selectedProgramName);
        exerciseName.setText("Exercise Name");

        EditText exerciseDescription = findViewById(R.id.ExerciseDescription);
        exerciseDescription.setText("Exercise Description");

    }

    public void onSaveClick(View v) {
        EditText exerciseName = findViewById(R.id.selectedProgramName);
        EditText exerciseDescription = findViewById(R.id.ExerciseDescription);

        resultIntent = new Intent();
        resultIntent.putExtra("exerciseName", exerciseName.getText().toString());
        resultIntent.putExtra("exerciseDescription", exerciseDescription.getText().toString());

        setResult(78, resultIntent);
        finish();
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
                            //displayImageView.setImageBitmap(selectedImageBitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
}