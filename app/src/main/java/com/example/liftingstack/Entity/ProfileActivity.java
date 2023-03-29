package com.example.liftingstack.Entity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liftingstack.R;

public class ProfileActivity extends AppCompatActivity {
    private String myString = "Hello, world!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TextView textView23 = findViewById(R.id.textViewProfile);
        textView23.setText(myString);
    }
}
