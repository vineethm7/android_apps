// IMovieService.aidl
package com.example.moviecentral;
import com.example.moviecentral.MovieDetail;
// Declare any non-default types here with import statements

interface IMovieService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    List<MovieDetail> getAllMovies();
    MovieDetail getSpecificMovie(int selected);
    String getURL(int selected);
}