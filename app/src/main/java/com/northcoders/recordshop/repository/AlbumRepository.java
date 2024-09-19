package com.northcoders.recordshop.repository;

import android.app.Application;
import android.util.Log;

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
        RetrofitInstance retrofitInstance = new RetrofitInstance();
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
}
