package me.gungunpriatna.retrofit.Rest;

import me.gungunpriatna.retrofit.MoviewResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("movie/top_rated")
    Call<MoviewResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<MoviewResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
}
