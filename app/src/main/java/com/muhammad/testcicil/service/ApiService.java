package com.muhammad.testcicil.service;

import com.muhammad.testcicil.model.DetailModel;
import com.muhammad.testcicil.model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("?apikey=faa01a26")
    Call<ResponseModel> getFilm(
            @Query("s") String s,
            @Query("page") String page
    );

    @GET("?apikey=faa01a26")
    Call<DetailModel> getDetil(
            @Query("i") String i
    );
}
