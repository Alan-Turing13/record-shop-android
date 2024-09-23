package com.northcoders.recordshop.repository;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.northcoders.recordshop.model.Album;
import com.northcoders.recordshop.service.ApiService;
import com.northcoders.recordshop.service.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumRepository {
    private List<Album> albumList;
    private MutableLiveData<List<Album>> mutableLiveData = new MutableLiveData<>();
    private Application app;

    public AlbumRepository(Application app){
        this.app = app;
    }

    public MutableLiveData<List<Album>> getMutableLiveData(){
        ApiService apiService = RetrofitInstance.getService();
        Call<List<Album>> call = apiService.getAllAlbums();
        call.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                albumList = response.body();
                mutableLiveData.setValue(albumList);
            }
            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
                Log.e("AlbumRepository", "HTTP Failure getting all albums");
            }
        });

        return mutableLiveData;
    }

    public void postAlbum(Album album){
        ApiService apiService = RetrofitInstance.getService();
        Log.i("Repo", "****** created Api");
        Call<Album> call = apiService.postAlbum(album);
        Log.i("Repo", album.getName());
        Log.i("Repo", "****** created Call");
        call.enqueue(new Callback<Album>() {
            @Override
            public void onResponse(Call<Album> call, Response<Album> response) {
                Toast.makeText(app, "Album posted successfully", Toast.LENGTH_SHORT).show();
                Log.i("POST req", "Posted album " + album.toString());
            }
            @Override
            public void onFailure(Call<Album> call, Throwable t) {
                Toast.makeText(app, "There was a problem posting that album", Toast.LENGTH_SHORT).show();
                Log.e("POST req", t.getMessage());
            }
        });
    }

    public void updateAlbum(Long id, Album album){
        ApiService apiService = RetrofitInstance.getService();
        Call<Album> call = apiService.updateAlbum(id, album);
        call.enqueue(new Callback<Album>() {
            @Override
            public void onResponse(Call<Album> call, Response<Album> response) {
                Toast.makeText(app, "Album updated successfully", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<Album> call, Throwable t) {
                Toast.makeText(app, "There was a problem updating that album", Toast.LENGTH_SHORT).show();
                Log.e("PUT req", t.getMessage());
            }
        });
    }

    public void deleteAlbum(Long id){
        ApiService apiService = RetrofitInstance.getService();
        Call<String> call = apiService.deleteAlbum(id);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(app, "Album deleted successfully", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(app, "There was a problem deleting that album", Toast.LENGTH_SHORT).show();
                Log.e("DEL req", t.getMessage());
            }
        });
    }
}
