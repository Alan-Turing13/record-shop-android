package com.northcoders.recordshop.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.northcoders.recordshop.model.Album;
import com.northcoders.recordshop.repository.AlbumRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private AlbumRepository albumRepository;

    public MainActivityViewModel(@NonNull Application app) {
        super(app);
        this.albumRepository = new AlbumRepository(app);
    }

    public LiveData<List<Album>> getAllAlbums(){
        return albumRepository.getMutableLiveData();
    }

    public void postAlbum(Album album) {
        albumRepository.postAlbum(album);
    }
}
