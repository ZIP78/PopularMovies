package com.example.paulkim.popularmovies;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie_Items implements Parcelable {

    private String movieThumbnailPoster;
    private String movieTitle;
    private String releaseDate;
    private String vote_Avg;
    private String mPlot;




    public Movie_Items(String movieThumbnail, String title, String date, String vote, String plot) {
        movieThumbnailPoster = movieThumbnail;
        movieTitle = title;
        releaseDate = date;
        vote_Avg = vote;
        mPlot = plot;
    }

    protected Movie_Items(Parcel in) {
        movieThumbnailPoster = in.readString();
        movieTitle = in.readString();
        releaseDate = in.readString();
        vote_Avg = in.readString();
        mPlot = in.readString();
    }

    public static final Creator<Movie_Items> CREATOR = new Creator<Movie_Items>() {
        @Override
        public Movie_Items createFromParcel(Parcel in) {
            return new Movie_Items(in);
        }

        @Override
        public Movie_Items[] newArray(int size) {
            return new Movie_Items[size];
        }
    };

    public String getMovieThumbnailPoster() {
        return movieThumbnailPoster;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getVote_Avg() {
        return vote_Avg;
    }

    public String getmPlot() {
        return mPlot;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(movieThumbnailPoster);
        dest.writeString(movieTitle);
        dest.writeString(releaseDate);
        dest.writeString(vote_Avg);
        dest.writeString(mPlot);
    }
}
