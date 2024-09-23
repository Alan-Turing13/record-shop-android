package com.northcoders.recordshop.ui.postalbum;

import android.content.Context;
import android.webkit.URLUtil;
import android.widget.Toast;

import com.northcoders.recordshop.model.Album;
import com.northcoders.recordshop.model.Genre;

public class PostAlbumValidator {

    boolean releaseYearIsValid(String formInput){
        return formInput.matches("-?\\d+(\\.\\d+)?") &&
                Integer.parseInt(formInput) < 2026 &&
                Integer.parseInt(formInput) > 1948;
    }

    public boolean isValid(Context context, Album album){
        boolean validated = true;

        if (album.getName().isBlank()){
            Toast.makeText(context, "Album name can't be blank", Toast.LENGTH_SHORT).show();
            validated = false;
        }
        if (album.getArtist().isBlank()) {
            Toast.makeText(context, "Artist field can't be blank", Toast.LENGTH_SHORT).show();
            validated = false;
        }
        if (!releaseYearIsValid(album.getReleaseYear())){
            Toast.makeText(context, "Release year invalid", Toast.LENGTH_SHORT).show();
            validated = false;
        }
        if (Genre.forInput(album.getGenre().toUpperCase()) == null) {
            Toast.makeText(context, "That genre couldn't be recognised", Toast.LENGTH_SHORT).show();
            validated = false;
        }
        if (!URLUtil.isValidUrl(album.getImageUrl())){
            Toast.makeText(context, "Image URL invalid", Toast.LENGTH_SHORT).show();
            validated = false;
        }
        return validated;
    }
}
