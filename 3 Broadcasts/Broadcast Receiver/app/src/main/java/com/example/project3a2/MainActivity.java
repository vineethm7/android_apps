package com.example.project3a2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private attr_receiver attr_rec;
    private rest_receiver rest_rec;
    private IntentFilter attr_filter;
    private IntentFilter rest_filter;
    private final int request_permission = 1;


    private static final String PERMISSION = "com.example.project3a1";
    private static final String ACTION_ATTR = "com.example.Attr";
    private static final String ACTION_REST = "com.example.Rest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermissionAndBroadcast();


        attr_rec = new attr_receiver();
        attr_filter = new IntentFilter(ACTION_ATTR);
        rest_rec = new rest_receiver();
        rest_filter = new IntentFilter(ACTION_REST);

        registerReceiver(attr_rec, attr_filter);
        registerReceiver(rest_rec, rest_filter);

        Toast.makeText(getApplicationContext(), "Broadcast Receiver Registered", Toast.LENGTH_SHORT).show();

    }
    private void checkPermissionAndBroadcast() {
        if (ActivityCompat.checkSelfPermission(this, PERMISSION)
                == PackageManager.PERMISSION_GRANTED) {
            Log.i("Permission Check", "Permission Already Granted for App2");
        }
        else {
        requestPermissions(new String[]{PERMISSION},request_permission);
       }

    }

    public void onRequestPermissionsResult(int code, String[] permissions, int[] results) {
        super.onRequestPermissionsResult(code, permissions, results);
        if (results.length > 0) {
            if (results[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission is Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No permission Granted", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }


}