package com.example.demoprojet.Service;



import com.example.demoprojet.GetterAndSetter.Movies;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieService {
    @GET("/movies?filter=new")
    Call<List<Movies>> getMovies();
}
