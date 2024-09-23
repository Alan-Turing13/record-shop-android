package com.northcoders.recordshop.service;

import com.northcoders.recordshop.model.Album;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {

    @GET("records/albums/response")
    Call<List<Album>> getAllAlbums();

    @POST("records")
    Call<Album> postAlbum(@Body Album album);

    @PUT("records/{id}")
    Call<Album> updateAlbum(@Path("id") long id, @Body Album album);

    @DELETE("records/{id}")
    Call<String> deleteAlbum(@Path("id") long id);

}
