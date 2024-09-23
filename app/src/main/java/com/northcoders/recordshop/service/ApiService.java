package com.northcoders.recordshop.service;

import com.northcoders.recordshop.model.Album;
import com.northcoders.recordshop.model.AlbumDetails;

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
    Call<AlbumDetails> postAlbum(@Body AlbumDetails album);

    @PUT("records/{id}")
    Call<AlbumDetails> updateAlbum(@Path("id") long id, @Body AlbumDetails album);

    @DELETE("records/{id}")
    Call<String> deleteAlbum(@Path("id") long id);

}
