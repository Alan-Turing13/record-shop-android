package com.northcoders.recordshop.ui.updatealbum;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.northcoders.recordshop.model.Album;
import com.northcoders.recordshop.model.Genre;
import com.northcoders.recordshop.ui.main.MainActivity;
import com.northcoders.recordshop.ui.postalbum.PostAlbumValidator;
import com.northcoders.recordshop.viewmodel.MainActivityViewModel;

public class UpdateAlbumClickHandler {
    private Album album;
    private Context context;
    private MainActivityViewModel mainActivityViewModel;
    private Long albumId;
    PostAlbumValidator postAlbumValidator;

    public UpdateAlbumClickHandler(Album album, Context context, MainActivityViewModel viewModel, Long albumId) {
        this.album = album;
        this.context = context;
        this.mainActivityViewModel = viewModel;
        this.albumId = albumId;
    }

    //‼️ endpoint won't accept an album with an id field
    public void onUpdate(View view){
        postAlbumValidator = new PostAlbumValidator();
        if (postAlbumValidator.isValid(context, album)) {
            Intent intent = new Intent(context, MainActivity.class);
            mainActivityViewModel.updateAlbum(
                    albumId,
                    new Album(album.getName(),
                            Integer.parseInt(album.getReleaseYear()),
                            Genre.valueOf(album.getGenre()),
                            album.getArtist(),
                            album.getImageUrl()));
            context.startActivity(intent);
        }
    }

    public void onDelete(View view){
        Intent intent = new Intent(context, MainActivity.class);
        mainActivityViewModel.deleteAlbum(albumId);
        context.startActivity(intent);
    }

    public void exitView(View view){
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }
}
