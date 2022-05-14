package com.example.project3a2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class attr_receiver extends BroadcastReceiver {
    //Attraction Broadcast Receiver Connection
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context.getApplicationContext(), "Attraction Broadcast Received", Toast.LENGTH_SHORT).show();
        Intent attr_int = new Intent(context, AttrActivity.class);
        context.startActivity(attr_int);
    }
}