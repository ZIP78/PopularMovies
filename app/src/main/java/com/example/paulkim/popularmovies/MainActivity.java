package com.example.paulkim.popularmovies;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements movieAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private movieAdapter moviesAdapter;
    private ArrayList<Movie_Items> listItems ;
    private String URL = "https://api.themoviedb.org/3/movie/popular?api_key=e085738f027ededcaabc5b61382906ab";
    private String URL2 = "https://api.themoviedb.org/3/movie/top_rated?api_key=e085738f027ededcaabc5b61382906ab";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        moviesAdapter = new movieAdapter(getApplicationContext(), listItems);
        recyclerView.setAdapter(moviesAdapter);
        moviesAdapter.setOnItemClickListener(MainActivity.this);

        new requestingMovieData().execute(URL);


    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(MainActivity.this, detail_movies.class);
        intent.putExtra("Movie Item",listItems.get(position));
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.movie_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.popular:
                new requestingMovieData().execute(URL);

                return true;

            case R.id.top_rated:
                new requestingMovieData().execute(URL2);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //get http response and parsed data from MovieDataParsing
    private class requestingMovieData extends AsyncTask<String, Void, ArrayList<Movie_Items>> {

        @Override
        protected ArrayList<Movie_Items> doInBackground(String... movieData) {

          if (movieData != null) {
              String movieResult = NetworkUtilis.getJSONResponseFromUrl(movieData[0]);
              if (movieResult != null) {
                  try {
                      listItems = MovieDataParsing.getParsedMovieData(movieResult);
                      return listItems;
                  } catch (JSONException e) {
                      e.printStackTrace();
                  }
              }
          }
            return listItems;
        }

        //set the data to the adapter
        @Override
        protected void onPostExecute(ArrayList<Movie_Items> movie_items_results) {
            if (movie_items_results != null) {
                moviesAdapter.setMovieData(movie_items_results);
            }
        }
    }
}

