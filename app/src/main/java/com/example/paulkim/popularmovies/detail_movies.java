package com.example.paulkim.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class detail_movies extends AppCompatActivity {

    Movie_Items movies;

    public ImageView mImageView;
    public TextView mTitleLabel;
    private TextView mTitleText;
    private TextView mReleaseLabel;
    private TextView mReleaseText;
    private TextView mVoteAverageLabel;
    private TextView mVoteAverageText;
    private TextView mPlotLabel;
    private TextView mPlotText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movies);

        mImageView = findViewById(R.id.movie_poster);
        mTitleLabel = findViewById(R.id.title_label);
        mTitleText = findViewById(R.id.movie_title_text);
        mReleaseLabel = findViewById(R.id.release_date_label);
        mReleaseText = findViewById(R.id.release_date_text);
        mVoteAverageLabel = findViewById(R.id.vote_average_label);
        mVoteAverageText = findViewById(R.id.vote_average_text);
        mPlotLabel = findViewById(R.id.plot_label);
        mPlotText = findViewById(R.id.plot_text);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        movies = intent.getParcelableExtra("Movie Item");

        String image = "http://image.tmdb.org/t/p/w185/"+ movies.getMovieThumbnailPoster();
        String title = movies.getMovieTitle();
        String releaseDate = movies.getReleaseDate();
       String vote = movies.getVote_Avg();
        String plot = movies.getmPlot();

        Picasso.with(this)
                .load(image)
                .into(mImageView);

        mTitleText.setText(title);
      mReleaseText.setText(releaseDate);
       mVoteAverageText.setText(vote);
        mPlotText.setText(plot);
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, "Data is not available", Toast.LENGTH_SHORT).show();
    }
}
