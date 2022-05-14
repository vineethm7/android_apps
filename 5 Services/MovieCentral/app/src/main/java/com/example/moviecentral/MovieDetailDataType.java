package com.example.moviecentral;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class MovieDetailDataType implements Parcelable {
    String title;
    String director;
    String url;

    protected MovieDetailDataType(Parcel in) {
        title = in.toString();
        director = in.readString();
        url = in.readString();
    }

    public static final Creator<MovieDetailDataType> CREATOR = new Creator<MovieDetailDataType>() {
        @Override
        public MovieDetailDataType createFromParcel(Parcel in) {
            return new MovieDetailDataType(in);
        }

        @Override
        public MovieDetailDataType[] newArray(int size) {
            return new MovieDetailDataType[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
