package com.example.liftingstack.Controller;

public class ExternalStorageUtils {
/*    Måste läggas till i manifest ifall vi ska ha permission att läsa och skriva till external storage
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.MANAGE_EXTERNAL_STORAGE" />*/


    // Ifall vi vill ha permission att läsa och skriva till external storage:: de flesta metoderna måste ligga i main activity(alla kan ligga där)
    /*    *//**
     * Checks if external storage is available for read and write
     * from video https://www.youtube.com/watch?v=0313bhp-8uA
     * @param context
     * @return
     *//*
    public static boolean isPermissionGranted(Context context) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) { // Checks to see if device runs Android 11 or later
            return Environment.isExternalStorageManager();
        } else { // if older than Android 11
            int result = ContextCompat.checkSelfPermission(context, android.Manifest.permission.READ_EXTERNAL_STORAGE);
            return result == PackageManager.PERMISSION_GRANTED;
        }
    }
*//** if external storage is not granted, asks the user for permission
     * @param
     * @return
     *//*
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
    *//**
     * sets permisson for external storage
     * @param
     * @return
     *//*
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
*//**
     * @param
     * @return
     *//*
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
    }*/
}
