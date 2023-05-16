package com.example.liftingstack.ProgramsActivities.StartedPrograms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.example.liftingstack.R;

public class test extends AppCompatActivity {

    private static int setCounter = 2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        TableLayout tableLayout = findViewById(R.id.startedProgramTable);
        CardView cardView = findViewById(R.id.startedProgramCardView);
        ImageView addRowBtn = findViewById(R.id.addRowBtn);
        ImageView removeRowBtn = findViewById(R.id.removeRowBtn);

        addRowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
                TableRow newRow = (TableRow) layoutInflater.inflate(R.layout.table_row_to_expand, null);

                EditText setsText = newRow.findViewById(R.id.setsEditText);
                setsText.setText(String.valueOf(setCounter));
                setCounter++;
                tableLayout.addView(newRow);

                int tableHeight = 300;

                for (int i = 0; i < tableLayout.getChildCount(); i++) {
                    View row = tableLayout.getChildAt(i);
                    tableHeight += row.getHeight();
                }

                // Set the new height for the TableLayout
                tableLayout.getLayoutParams().height = tableHeight;

                // Calculate the new height of the CardView
                int cardViewHeight = tableHeight;

                // Set the new height for the CardView
                cardView.getLayoutParams().height = cardViewHeight;
                cardView.requestLayout();

            }
        });

        removeRowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (tableLayout.getChildCount() > 2) {
                    // Remove the last row from the TableLayout
                    tableLayout.removeViewAt(tableLayout.getChildCount() - 1);
                    setCounter--;

                    int tableRowHeight = 142;
                    cardView.getLayoutParams().height = cardView.getHeight() - tableRowHeight;
                    cardView.requestLayout();
                }
            }
        });
    }

}
