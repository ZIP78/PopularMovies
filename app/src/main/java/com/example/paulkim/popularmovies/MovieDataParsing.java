package com.example.paulkim.popularmovies;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.ArrayList;

public class MovieDataParsing {
    public static ArrayList<Movie_Items> getParsedMovieData(String jsonMovieData) throws JSONException {
        ArrayList<Movie_Items> movieDataArray = new ArrayList<>();
        JSONObject movieJson = new JSONObject(jsonMovieData);
        JSONArray moviesArray = movieJson.getJSONArray("results");
        for (int i = 0; i < moviesArray.length(); i++) {
            JSONObject resultObject = moviesArray.getJSONObject(i);
            String poster_path = resultObject.getString("poster_path");
            String movie_title = resultObject.getString("title");
            String released_date = resultObject.getString("release_date");
            String vote_avg = resultObject.getString("vote_average");
            String plot = resultObject.getString("overview");
            movieDataArray.add(new Movie_Items(poster_path, movie_title, released_date, vote_avg, plot));
        }
        return movieDataArray;
    }
}

