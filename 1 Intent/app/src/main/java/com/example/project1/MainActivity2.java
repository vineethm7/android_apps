package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.BreakIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity2 extends AppCompatActivity {
    private static EditText editText;
    TextView text;
    String pattern ="^\\s*[(]*(\\d{3})[- )]*(\\d{3})[- ]*(\\d{3})\\s*$";;
    Matcher m;
    public static boolean isValid(String s) {
        String pattern ="^\\s*[(]*(\\d{3})[- )]*(\\d{3})[- ]*(\\d{3})\\s*$";
        Matcher m;
        Pattern pat = Pattern.compile(pattern);
        m = pat.matcher(s);
        return (m.find() && m.group().equals(s));
    }
    public static String getString() {
        String number;
        // assign value to string here
        number = editText.getText().toString().trim();
        return number;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        text = findViewById(R.id.text);
        editText = findViewById(R.id.editText);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                Intent var = new Intent();
                String num = editText.getText().toString().trim();
                var.putExtra("NAME", num);
                Pattern r = Pattern.compile(pattern);
//                if (!editText.getText().toString().isEmpty()) {
//                    m = r.matcher(editText.getText().toString().trim());
//
//                }
//                else {
//                    Toast.makeText(MainActivity2.this, "Please Enter Mobile Number", Toast.LENGTH_LONG).show();
//                }
                openMainActivity();
                Log.i("message", "is " + isValid(editText.getText().toString().trim()));
                if (isValid(num)) {
                    setResult(RESULT_OK, var);
                    Log.i("message", "is " + "RESULT_OK");
                }
                else {
                    setResult(RESULT_CANCELED, var);
                    Log.i("message", "is " + "RESULT_CANCELLED");
                }
                finish();
                return true;
            }
        });

    }

    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}