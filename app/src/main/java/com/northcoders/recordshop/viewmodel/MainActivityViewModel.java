package com.northcoders.recordshop.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.northcoders.recordshop.model.Album;
import com.northcoders.recordshop.repository.AlbumRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private AlbumRepository albumRepository;

    public void init(@NonNull Application app) {
        this.albumRepository = new AlbumRepository(app);
    }

    public LiveData<List<Album>> getAllAlbums(){
        return albumRepository.getMutableLiveData();
    }

    public void postAlbum(Album album) {
        Log.i("ViewModel", "Posting album " + album.toString());
        albumRepository.postAlbum(album);
    }

    public void updateAlbum(Long id, Album album){
        albumRepository.updateAlbum(id, album);
    }

    public void deleteAlbum(Long id){
        albumRepository.deleteAlbum(id);
    }
}
