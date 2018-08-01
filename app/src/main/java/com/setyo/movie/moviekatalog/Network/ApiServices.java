package com.setyo.movie.moviekatalog.Network;

import com.setyo.movie.moviekatalog.Model.MovieModel.ResponseModel;

import java.util.Map;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiServices {
    @GET("3/movie/popular?api_key=8f0079291f0ab41d8e4d01294fc0c2ea&language=en-US&page=1")
    Call<ResponseModel> ambilDataPopularMovie();

    @GET("3/movie/top_rated?api_key=8f0079291f0ab41d8e4d01294fc0c2ea&language=en-US&page=1")
    Call<ResponseModel> ambilDataTopMovie();

    @GET("3/search/movie?api_key=8f0079291f0ab41d8e4d01294fc0c2ea")
    Call<ResponseModel> cariDataMovie(
            @Query("query") String query);
//    Call<ResponseModel> cariDataMovie(@QueryMap  Map<String, String> data);


}
