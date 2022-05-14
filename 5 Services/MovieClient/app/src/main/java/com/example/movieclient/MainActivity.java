package com.example.movieclient;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.moviecentral.IMovieService;
import com.example.moviecentral.MovieDetail;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button bind;
    Button unbind;
    TextView status;
    TextView topmovies;
    Button list_of_movies;
    ImageView image1;
    ImageView image2;
    TextView title1;
    TextView title2;
    TextView director1;
    TextView director2;
    RelativeLayout relative1;
    RelativeLayout relative2;
    List<MovieDetail> allMovies = null;



    Boolean b=false;

    public ArrayList<String> title;
    public ArrayList<String> director;
    public ArrayList<String> url;
    private IMovieService info;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind=findViewById(R.id.bindbutton);
        unbind=findViewById(R.id.unbindbutton);
        status=findViewById(R.id.status);
        topmovies=findViewById(R.id.topmovies);
        status.setText("Service UnBinded");
        unbind.setEnabled(b);
        list_of_movies=findViewById(R.id.allmoviesbutton);
        image1=findViewById(R.id.imageView1);
        image2=findViewById(R.id.imageView2);
        title1=findViewById(R.id.title1);
        title2=findViewById(R.id.title2);
        director1=findViewById(R.id.directorname1);
        director2=findViewById(R.id.directorname2);
        relative1=findViewById(R.id.relative1);
        relative2=findViewById(R.id.relative2);
        topmovies.setVisibility(View.INVISIBLE);
        list_of_movies.setVisibility(View.INVISIBLE);
        image1.setVisibility(View.INVISIBLE);
        image2.setVisibility(View.INVISIBLE);
        title1.setVisibility(View.INVISIBLE);
        title2.setVisibility(View.INVISIBLE);
        director1.setVisibility(View.INVISIBLE);
        director2.setVisibility(View.INVISIBLE);
        title = new ArrayList<>();
        director = new ArrayList<>();
        url = new ArrayList<>();

        bind.setOnClickListener(v -> {
            try {
                bindToService();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });

        unbind.setOnClickListener(v -> unBindFromService());

        WebView mView = (WebView) findViewById(R.id.web) ;

        relative1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    mView.loadUrl(info.getURL(0));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        relative2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    mView.loadUrl(info.getURL(1));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        list_of_movies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MovieList.class);
                intent.putStringArrayListExtra("title",title);
                intent.putStringArrayListExtra("director",director);
                intent.putStringArrayListExtra("url",url);
                startActivity(intent);
            }
        });
    }

    public void onbind() throws RemoteException {
        if (b) {
            status.setText("Service Binded");
            unbind.setEnabled(b);
            bind.setEnabled(!b);
            topmovies.setVisibility(View.VISIBLE);
            list_of_movies.setVisibility(View.VISIBLE);
            title1.setVisibility(View.VISIBLE);
            title2.setVisibility(View.VISIBLE);
            director2.setVisibility(View.VISIBLE);
            director1.setVisibility(View.VISIBLE);
            image1.setVisibility(View.VISIBLE);
            image2.setVisibility(View.VISIBLE);
            image1.setImageResource(R.drawable.image1);
            image2.setImageResource(R.drawable.image2);
            MovieDetail movie1 = null;
            MovieDetail movie2 = null;
            //TO GET ALL MOVIES
            try {
                allMovies = info.getAllMovies();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            Log.i("size of movies", String.valueOf(allMovies.size()));
            for(int i=0;i<allMovies.size();i++) {
                title.add(allMovies.get(i).title);
                director.add(allMovies.get(i).director);
                url.add(allMovies.get(i).url);
            }
            // TO GET TOP MOVIES
            try {
                movie1 = info.getSpecificMovie(0);
                movie2= info.getSpecificMovie(1);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            title1.setText(movie1.title);
            title2.setText(movie2.title);
            director1.setText(movie1.director);
            director2.setText(movie2.director);
        }
    }

    private final ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            info = IMovieService.Stub.asInterface(iBinder);
            b = true;
            try {
                onbind();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Toast.makeText(getApplication(),"Connection is Lost with Server", Toast.LENGTH_LONG).show();
        }
    };

    private void bindToService() throws RemoteException{
        if (!b) {
            boolean x;
            Intent i = new Intent(IMovieService.class.getName());
            ResolveInfo resolveInfo = getPackageManager().resolveService(i, 0);
            i.setComponent(new ComponentName("com.example.moviecentral", "com.example.moviecentral.MovieService"));
            x = bindService(i, this.mConnection, Context.BIND_AUTO_CREATE);
            if (x) {
                Log.i("a", "Ugo says bindService() succeeded!");
            } else {
                Log.i("b", "Ugo says bindService() failed!");
            }
        }
    }


    private void unBindFromService() {
        if (b) {
            unbindService(mConnection);
            b = false;
            OnUnbind();
        }
    }


    private void OnUnbind() {
        if (!b) {
            status.setText("Service UnBinded");
            unbind.setEnabled(b);
            bind.setEnabled(!b);
            list_of_movies.setVisibility(View.INVISIBLE);
            title1.setVisibility(View.INVISIBLE);
            title2.setVisibility(View.INVISIBLE);
            director2.setVisibility(View.INVISIBLE);
            director1.setVisibility(View.INVISIBLE);
            image1.setVisibility(View.INVISIBLE);
            image2.setVisibility(View.INVISIBLE);
            topmovies.setVisibility(View.INVISIBLE);
            title = new ArrayList<>();
            director = new ArrayList<>();
            url = new ArrayList<>();
            Intent i = new Intent(IMovieService.class.getName());
            ResolveInfo resolveInfo = getPackageManager().resolveService(i, 0);
            i.setComponent(new ComponentName("com.example.moviecentral", "com.example.moviecentral.MovieService"));
            stopService(i);
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}