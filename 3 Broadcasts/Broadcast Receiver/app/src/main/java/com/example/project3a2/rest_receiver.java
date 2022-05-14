package com.example.project3a2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class rest_receiver extends BroadcastReceiver {
    //Restaurant Broadcast Receiver Connection
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context.getApplicationContext(), "Restaurant Broadcast Received", Toast.LENGTH_SHORT).show();
        Intent rest_int = new Intent(context, RestActivity.class);
        context.startActivity(rest_int);
    }
}