package com.example.liftingstack;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;


public class TimerActivity extends AppCompatActivity {

    private int seconds;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        startTimer();
    }


    public void onPlay(View view){
        running = true;
    }

    public void onPause(View view){
        running = false;
    }

    public void onReset(View view) {
        running = false;
        seconds = 0;
    }

    private void startTimer(){
        final TextView timer = findViewById(R.id.timer);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                int hours = seconds / 3600;
                int mins = (seconds % 3600) / 60;
                int secs = seconds % 60;

                String time = String.format(Locale.getDefault(),
                                            "%d:%02d:%02d",
                                             hours, mins, secs);

                timer.setText(time);
                if(running){
                    seconds++;
                }
                handler.postDelayed(this, 0);
            }
        });
    }
}
