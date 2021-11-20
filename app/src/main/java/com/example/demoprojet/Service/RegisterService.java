package com.example.demoprojet.Service;



import com.example.demoprojet.Request.RegisterRequest;
import com.example.demoprojet.Response.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegisterService {
    @POST("/auth/register")
    Call<RegisterResponse> registerUser(@Body RegisterRequest registerRequest);

}

