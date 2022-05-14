package com.example.moviecentral;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.os.RemoteException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MovieService extends Service{
    List<String> movie = Arrays.asList("Rise Roar Revolt", "Aravinda Sametha", "Jai Lava Kusa", "Janatha Garage", "Nannaku Prematho", "Temper", "Aadi", "Simhadri", "Adhurs", "Brindadavanam");
    List<String> director_name = Arrays.asList("SS Rajamouli", "Trivikram", "Bobby", "Koratala Siva", "Sukumar", "Puri Jagannadh", "VV Vinayak", "SS Rajamouli", "VV Vinayak", "Vamsi Paidipally");
    List<String> url = Arrays.asList("https://youtu.be/NgBoMJy386M", "https://youtu.be/dNMe5oRfsCE", "https://youtu.be/5N-wb-OGa1I", "https://youtu.be/7O4Hm070Bc8", "https://youtu.be/Om69gF1iiT4", "https://youtu.be/SQgRN5tu1f4","https://youtu.be/5-FLupVhAlQ", "https://youtu.be/6G5quVclbHA", "https://youtu.be/6X438MDx-BE", "https://youtu.be/9FGy0c8xJkc");

    public MovieService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    private final IMovieService.Stub binder = new IMovieService.Stub() {

        @Override
        public List<MovieDetail> getAllMovies() throws RemoteException {
            List<MovieDetail> all_movies = new ArrayList<>();
            for (int i = 0; i < movie.size(); i++) {
                String title = movie.get(i);
                String director = director_name.get(i);
                String url_link = url.get(i);

                MovieDetail movie = new MovieDetail();
                movie.title = title;
                movie.director = director;
                movie.url = url_link;
                all_movies.add(movie);
            }
            return all_movies;
        }

        @Override
        public MovieDetail getSpecificMovie(int selected) throws RemoteException {
            String name = movie.get(selected);
            String director = director_name.get(selected);
            String url_link = url.get(selected);

            MovieDetail movie = new MovieDetail();
            movie.title = name;
            movie.director = director;
            movie.url = url_link;
            return movie;
        }

        @Override
        public String getURL(int selected) throws RemoteException {
            String song_url=url.get(selected);
            return song_url;
        }
    };
}
