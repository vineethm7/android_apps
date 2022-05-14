package com.example.project3a1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String PERMISSION = "com.example.project3a1";
    private static final String ACTION_ATTR = "com.example.Attr";
    private static final String ACTION_REST = "com.example.Rest";
    private final int request = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button attr_btn = (Button) findViewById(R.id.button1);
        final Button rest_btn = (Button) findViewById(R.id.button2);

        attr_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent place_intent = new Intent();
                place_intent.setAction(ACTION_ATTR);
                place_intent.putExtra("description","attraction broadcast");
                place_intent.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                checkPermissionAndBroadcast(place_intent);
                Toast.makeText(getApplicationContext(), "Broadcast Request For Attraction", Toast.LENGTH_SHORT).show();
            }
        });

        rest_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rest_intent = new Intent();
                rest_intent.setAction(ACTION_REST);
                rest_intent.putExtra("description","restaurant broadcast");
                rest_intent.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                checkPermissionAndBroadcast(rest_intent);
                Toast.makeText(getApplicationContext(), "Broadcast Request For Restaurant", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkPermissionAndBroadcast(Intent intent) {
        if (ActivityCompat.checkSelfPermission(this, PERMISSION)
                == PackageManager.PERMISSION_GRANTED) {
            sendBroadcast(intent, PERMISSION) ;
        }
        else {
            requestPermissions(new String[]{PERMISSION},request);
        }

    }

    public void onRequestPermissionsResult(int code, String[] permissions, int[] results) {
        super.onRequestPermissionsResult(code, permissions, results);
        if (results.length > 0) {
            if (results[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission is Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No Permission is Granted", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

}