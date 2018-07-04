package com.example.paulkim.popularmovies;

import android.app.ProgressDialog;
import android.content.Intent;
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
    private List<Movie_Items> listItems ;
    private RequestQueue mRequestQueue;
    private String URL = "https://api.themoviedb.org/3/movie/popular?api_key=e085738f027ededcaabc5b61382906ab";

    private String URL2 = "https://api.themoviedb.org/3/movie/top_rated?api_key=e085738f027ededcaabc5b61382906ab";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        listItems = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);
        moviesAdapter = new movieAdapter(getApplicationContext(), listItems);
        recyclerView.setAdapter(moviesAdapter);
        moviesAdapter.setOnItemClickListener(MainActivity.this);

        loadData(URL2);


    }

    private void loadData(String request) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Data");
        progressDialog.show();



        StringRequest stringRequest = new StringRequest(Request.Method.GET, request, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray moviesArray = jsonObject.getJSONArray("results");
                    for (int i = 0; i<moviesArray.length(); i++) {
                        JSONObject resultObject = moviesArray.getJSONObject(i);
                        Movie_Items items = new Movie_Items(resultObject.getString("poster_path"),
                                resultObject.getString("title"),
                                resultObject.getString("release_date"),
                                resultObject.getString("vote_average"),
                                resultObject.getString("overview"));
                        listItems.add(items);

                    }
                    moviesAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
        mRequestQueue.add(stringRequest);
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
                loadData(URL);
                return true;

            case R.id.top_rated:
                loadData(URL2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

