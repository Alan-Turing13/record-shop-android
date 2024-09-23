package com.northcoders.recordshop.ui.postalbum;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.northcoders.recordshop.R;
import com.northcoders.recordshop.databinding.ActivityPostAlbumBinding;
import com.northcoders.recordshop.model.Album;
import com.northcoders.recordshop.viewmodel.MainActivityViewModel;

public class PostAlbumActivity extends AppCompatActivity {

    MainActivityViewModel mainActivityViewModel;
    ActivityPostAlbumBinding activityPostAlbumBinding;
    PostAlbumClickHandler postAlbumClickHandler;
    Album album;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_album);
        album = new Album();
        activityPostAlbumBinding = DataBindingUtil.setContentView(this, R.layout.activity_post_album);
        Log.i("Post Album Activity", "Getting album from the data bind.");
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        mainActivityViewModel.init(this.getApplication());
        postAlbumClickHandler = new PostAlbumClickHandler(album, this, mainActivityViewModel);
        activityPostAlbumBinding.setClickHandler(postAlbumClickHandler);

        activityPostAlbumBinding.setAlbum(album);
    }
}