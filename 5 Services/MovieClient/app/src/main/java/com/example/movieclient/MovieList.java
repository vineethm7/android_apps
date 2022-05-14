package com.example.movieclient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class MovieList extends AppCompatActivity {
    private ListView movieList;
    ArrayList<String> title;
    ArrayList<String> director;
    ArrayList<String> url;
    ArrayList<String> movie = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        movieList = findViewById(R.id.list_view);
        title = getIntent().getStringArrayListExtra("title");
        director = getIntent().getStringArrayListExtra("director");
        url = getIntent().getStringArrayListExtra("url");
        Log.i("msf", String.valueOf(title.size()));
        for(int i=0;i<title.size();i++){
            String s = title.get(i) + "\n" + director.get(i);
            movie.add(s);
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, movie);
        movieList.setAdapter(adapter);

        WebView mView = (WebView) findViewById(R.id.web_1);
        movieList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mView.loadUrl(url.get(i));
            }
        });
    }

}