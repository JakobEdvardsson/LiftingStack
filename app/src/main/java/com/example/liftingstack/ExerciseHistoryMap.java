package com.example.liftingstack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.liftingstack.Controller.LoadFromDevice;
import com.example.liftingstack.Controller.SaveToDevice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ExerciseHistoryMap extends AppCompatActivity {

    private EditText repsSet1;
    private EditText repsSet2;
    private EditText repsSet3;
    private EditText weightSet1;
    private EditText weightSet2;
    private EditText weightSet3;
    private String exerciseId;
    private Map<String, ArrayList<String>> setDataMap = new HashMap<>();
    // key = which set, value list of reps and weight
    private Map<String, Map<String, ArrayList<String>>> dateDataMap = new HashMap<>();
    // key = date (String), value = setDataMap
    private Map<String, Map<String, Map<String, ArrayList<String>>>> exerciseHistoryMap = new HashMap<>();
    // key = exersice id (String), value = dateDataMap




    // **BNI** Vi kan använda en datepicker som det finns färdig kod för om vi vill att
    // man ska kunna välja vilket datum man loggar för, se https://www.youtube.com/watch?v=qCoidM98zNk
    // då behöver vi ha tre int som parametrar.

    // just nu kör vi att det automatiskt loggas som dagens datum.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_history_map);


        repsSet1 = findViewById(R.id.repsInputSet1);
        repsSet2 = findViewById(R.id.repsInputSet2);

        repsSet3 = findViewById(R.id.repsInputSet3);
        weightSet1 = findViewById(R.id.weightInputSet1);
        weightSet2 = findViewById(R.id.weightInputSet2);
        weightSet3 = findViewById(R.id.weightInputSet3);

    }

    public void saveExerciseHistory(View v) {
        // **skapa en exerciseHistoryMap i savefilen när övningen skapas
        //load exerciseHistoryFile
        // check if theres already a date for the specific date if so, add to the HashMap
        // else just create a new date HashMap
        // add as many sets as have been done in a for loop
        //exerciseHistoryMap = new LoadFromDevice().loadHashMapFromDevice(this, "exerciseHistory");


        // find some way to make a loop which checks how many sets have been enetered and set as many variables
        String repsString1 = repsSet1.getText().toString();
        String weightString1 = weightSet1.getText().toString();
        String repsString2 = repsSet2.getText().toString();
        String weightString2 = weightSet2.getText().toString();
        Log.i("Testhistory repsset2", repsString2);


        // ***********Test med loop för att hämta värden + skapa textfält i java med en loop två länkar:
        //https://stackoverflow.com/questions/42713464/looping-through-an-array-of-edittexts-and-getting-the-texts-of-each
        //https://stackoverflow.com/questions/56880051/how-to-loop-through-all-controls-and-get-its-id-and-values-in-android
        /*for (int i = 0; i < 16; i++) {
            int id = getResources().getIdentifier("button_"+i, "id", getPackageName());
            button[i] = findViewById(id);
        } */
        // **********test för loop för att hämta värden



        // find some way to make a loop which checks how many sets have been enetered and call setSetDataMap as many times instead of below

            setSetDataMap("1", repsString1, weightString1);
            setSetDataMap("2", repsString2, weightString2);

        // get exercise id instead of hardcoding it
    }

    public void setExerciseId(String exerciseId) {
        this.exerciseId = exerciseId;
    }

    public void setSetDataMap(String set, String reps, String weight) {
        ArrayList<String> setStringArrayList = new ArrayList<>();
        setStringArrayList.add(reps);
        setStringArrayList.add(weight);

        setDataMap.put(set, setStringArrayList);
    }

    public void setExerciseHistoryMap() {
        //sets the date to today and parses it to a string

        LocalDate dateObject = LocalDate.now();
        String dateString = dateObject.toString();

        // puts the logged sets into hashmap with date as key
        //dateDataMap.put(dateString, setDataMap);

        dateDataMap.put("2023-04-21", setDataMap);
        // puts above hashmap into hashmap with the exerciseId as key
        exerciseHistoryMap.put(exerciseId, dateDataMap);

        // saves above hashmap as json to file
        new SaveToDevice().saveExerciseHashMapToDevice(exerciseHistoryMap, this, "exerciseHistory");

        //loads the savefile and returns a hashmap which is saved in instancevariable hashmap
        exerciseHistoryMap = new LoadFromDevice().loadExerciseHashMapFromDevice(this, "exerciseHistory");


        // below only for testing purposes
        dateDataMap = exerciseHistoryMap.get("1");
        setDataMap = dateDataMap.get("2023-05-08");

        Log.i("TestHistory TestSet4", exerciseHistoryMap.toString());
        Log.i("TestHistory TestSet5", exerciseHistoryMap.get("1").toString());
        Log.i("TestHistory TestSet6", exerciseHistoryMap.get("1").get("2023-05-08").toString());
        Log.i("TestHistory TestSet7", " " + exerciseHistoryMap.get("1").get("2023-05-08").get("1"));
        Log.i("TestHistory getId", " " + exerciseHistoryMap.keySet());
        Log.i("TestHistory getDates", " " + exerciseHistoryMap.get("1").keySet());
        if(exerciseHistoryMap.get("1").keySet().toString().equals("[2023-05-08]") ){
            Log.i("TestHistory if test", "ok");
        }
        Log.i("TestHistory getSets", " " + exerciseHistoryMap.get("1").get("2023-05-08").keySet());

        Log.i("TestHistory getReps", " " + exerciseHistoryMap.get("1").get("2023-05-08").get("1").get(1));

    }
}