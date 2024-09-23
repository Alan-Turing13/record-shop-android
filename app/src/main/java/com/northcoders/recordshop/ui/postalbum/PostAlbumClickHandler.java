package com.northcoders.recordshop.ui.postalbum;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.northcoders.recordshop.model.Album;
import com.northcoders.recordshop.viewmodel.MainActivityViewModel;

public class PostAlbumClickHandler {
    private Album album;
    private Context context;
    MainActivityViewModel mainActivityViewModel;
    PostAlbumValidator postAlbumValidator;

    public PostAlbumClickHandler(Album album, Context context, MainActivityViewModel mainActivityViewModel) {
        this.album = album;
        this.context = context;
        this.mainActivityViewModel = mainActivityViewModel;
    }

    public void onSubmit(View view){
        Log.i("Post Album Click Handler", "Submission received. Album name " + album.getName());
        postAlbumValidator = new PostAlbumValidator();
        if (postAlbumValidator.isValid(context, album)) {
            mainActivityViewModel.postAlbum(album);
            Intent intent = new Intent(context, MainActivityViewModel.class);
            context.startActivity(intent);
        }
    }

    public void exitView(View view){
        Intent intent = new Intent(context, MainActivityViewModel.class);
        context.startActivity(intent);
    }
}
