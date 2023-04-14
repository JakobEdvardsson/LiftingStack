package com.example.liftingstack;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
/** Testklass för att prova all funktionalitet för spara/läsa bilder, text och objekt med bilder och text.
 *
 * @author Benni */
public class TestReadWrite extends AppCompatActivity {

    Button selectImageButton;

    ImageView displayImageView;

    int SELECT_IMAGE_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_read_write);
        selectImageButton = findViewById(R.id.selectImageButton);
        displayImageView = findViewById(R.id.displayImageView);

        selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageChooser();
            }
        });
    }
    private void imageChooser()
    {
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
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });



    // ******************* Gammalt *******************
    //Har att göra med external storage, tas förmodligen bort


/*

       */
/* private void imageChooser()
    {
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
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                        imageView.setImageBitmap(
                                selectedImageBitmap);
                    }
                }
            });*//*

    */
/*
     *//*
*/
/**
     * Checks if external storage is available for read and write
     * from video https://www.youtube.com/watch?v=0313bhp-8uA
     * @param context
     * @return
     *//*
*/
/*
    public static boolean isPermissionGranted(Context context) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) { // Checks to see if device runs Android 11 or later
            return Environment.isExternalStorageManager();
        } else { // if older than Android 11
            int result = ContextCompat.checkSelfPermission(context, android.Manifest.permission.READ_EXTERNAL_STORAGE);
            return result == PackageManager.PERMISSION_GRANTED;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!isPermissionGranted(this)) {
            new AlertDialog.Builder(this)
                    .setTitle("All files permission")
                    .setMessage("Due to Android 11 restrictions, you need to grant permission to access all files")
                    .setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            takePermission();
                        }
                    })
                    .setNegativeButton("Deny", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    }).setIcon(android.R.drawable.ic_dialog_alert).show();

        } else{
            Toast.makeText(this, "Permission already granted", Toast.LENGTH_LONG).show();
        }
    }
    private void takePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) { // if Android 11 or later
            try {
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.addCategory("android.intent.category.DEFAULT");
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivityForResult(intent, 101);
            } catch (Exception e) {
                e.printStackTrace();
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                startActivityForResult(intent, 101);
            }
        } else {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 101);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length > 0) {
            if(requestCode ==101){
                boolean readExternalStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if(!readExternalStorage) {
                    takePermission();
                }
            }
        }
    }*//*



    */
/* public void saveStringToFile(Object saveObject) {
     *//*
*/
/*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            System.out.println("Test 1 inside 1if");

            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                System.out.println("Test 1 inside 2if");

                requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 123);
*//*
*/
/*

                if (isStorageWritable() && checkSavePermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    Toast.makeText(this, "Test 1 inside 3if", Toast.LENGTH_LONG).show();
                    System.out.println("Test 1 inside 3if");
                    //File file = new File(Environment.getExternalStorageDirectory(), fileName);

                    if (saveObject != null) {
                        FileOutputStream outputStream = null;
                        File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/TestLiftingStack");
                        dir.mkdir();
                        System.out.println(Environment.getExternalStorageDirectory().getAbsolutePath().toString());
                        //Log.i("Save dir", Environment.getExternalStorageDirectory().getAbsolutePath().toString());
                        String fileName = System.currentTimeMillis() + ".txt";
                        File file = new File(dir, fileName);
                    try {
                        System.out.println("Test1 inside try");
                        outputStream = new FileOutputStream(file);
                        // FileOutputStream fos = new FileOutputStream(file);
                        System.out.println("Test2 inside try");
                        outputStream.write(saveObject);
                        //fos.write(testString.getBytes());
                        System.out.println("Test3 inside try");
                        outputStream.close();
                        //fos.close();
                        Log.i("File", "Object saved to file");
                        Toast.makeText(this, "Object saved to file", Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        System.out.println("");
                        Toast.makeText(this, "Error saving object to file", Toast.LENGTH_LONG).show();
                        Log.i("File", "Error saving object to file");
                        e.printStackTrace();
                    }
              *//*
*/
/*  }
            }*//*
*/
/*
        } else {
            System.out.println("Test 1 inside else");
        }
    }}
*//*

    */
/*public void saveObjectToFile(Object object) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            System.out.println("Test 1 inside 1if");

            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                System.out.println("Test 1 inside 2if");

                requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 123);

        if (isStorageWritable() && checkSavePermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(this, "Test 1 inside 3if", Toast.LENGTH_LONG).show();
            System.out.println("Test 1 inside 3if");
            File file = new File(Environment.getExternalStorageDirectory(), fileName);
            try {
                FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(object);
                oos.close();
                fos.close();
                Log.i("File", "Object saved to file");
                Toast.makeText(this, "Object saved to file", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                System.out.println("");
                Toast.makeText(this, "Error saving object to file", Toast.LENGTH_LONG).show();
                Log.i("File", "Error saving object to file");
                e.printStackTrace();
            }
              *//*
*/
/*  }
            }*//*
*/
/*
        } else {
            System.out.println("Test 1 inside else");
        }
    }*//*


    public boolean checkSavePermission(String permission) {
        int check = ContextCompat.checkSelfPermission(this, permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }

    public boolean isStorageWritable() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            System.out.println("Storage is writable");
            Log.i("Storage", "Storage is writable");
            return true;
        } else {
            Log.i("Storage", "Storage is not writable");
            return false;
        }
    }

    public boolean isStorageReadable() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ||
                Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            System.out.println("Storage is readable");
            Log.i("Storage", "Storage is readable");
            return true;
        } else {
            Log.i("Storage", "Storage is not readable");
            return false;
        }
    }
*/


}