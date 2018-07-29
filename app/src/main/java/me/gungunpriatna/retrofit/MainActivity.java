package me.gungunpriatna.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import me.gungunpriatna.retrofit.Model.Movie;
import me.gungunpriatna.retrofit.Rest.ApiClient;
import me.gungunpriatna.retrofit.Rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private final static String API_KEY = "your_api_key";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please obtain your API KEY first from " +
                    "themoviedb.org", Toast.LENGTH_LONG).show();
            return;
        }

        final RecyclerView recyclerView = findViewById(R.id.movie_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<MoviewResponse> call = apiService.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MoviewResponse>() {
            @Override
            public void onResponse(Call<MoviewResponse> call, Response<MoviewResponse> response) {
                List<Movie> movies = response.body().getResults();
                Log.d(TAG, "Number of movies received: "+movies.size());
                Toast.makeText(MainActivity.this, "Number of movies receives: "
                        +movies.size(), Toast.LENGTH_LONG).show();
                recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext()));
            }

            @Override
            public void onFailure(Call<MoviewResponse> call, Throwable t) {
                Log.e(TAG, t.toString());

            }
        });



    }
}
