package com.example.project1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private Button button1;
    private Button button2;
    int flag=1;
    String res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainActivity2();
            }
        });

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialer();
            }
        });
    }

    public static boolean isValid(String s) {
        String pattern ="^\\s*[(]*(\\d{3})[- )]*(\\d{3})[- ]*(\\d{3})\\s*$";
        Matcher m;
        Pattern pat = Pattern.compile(pattern);
        m = pat.matcher(s);
        return (m.find() && m.group().equals(s));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Log.i("MainActivity","Result code "+resultCode);
        flag=resultCode;
        Log.i("Activity"," flag is "+flag);
        res=data.getExtras().getString("NAME");
    }

    public void openMainActivity2() {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

    public void openDialer() {
        String number = MainActivity2.getString();
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Uri.encode(number)));
        String num = MainActivity2.getString();
        try
        {
            // Launch the Phone app's dialer with a phone
            // number to dial a call.
            if (!isValid(num)) {
                Toast.makeText(MainActivity.this, "Please Enter Valid Mobile Number", Toast.LENGTH_LONG).show();
                openMainActivity2();

            } else {
                startActivity(intent);

            }
        }
        catch (SecurityException s)
        {
            // show() method display the toast with
            // exception message
            Toast.makeText(this, "An error occurred", Toast.LENGTH_LONG)
                    .show();
        }
    }
}