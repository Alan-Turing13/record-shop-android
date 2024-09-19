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
    RetrofitInstance retrofitInstance;

    public AlbumRepository(Application app){
        this.app = app;
        RetrofitInstance retrofitInstance = new RetrofitInstance();
    }

    public MutableLiveData<List<Album>> getMutableLiveData(){
        ApiService apiService = retrofitInstance.getService();
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
        ApiService apiService = retrofitInstance.getService();
        Call<Album> call = apiService.postAlbum(album);
        call.enqueue(new Callback<Album>() {
            @Override
            public void onResponse(Call<Album> call, Response<Album> response) {
                Toast.makeText(app, "Album posted successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Album> call, Throwable t) {
                Toast.makeText(app, "There was a problem posting that album", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
