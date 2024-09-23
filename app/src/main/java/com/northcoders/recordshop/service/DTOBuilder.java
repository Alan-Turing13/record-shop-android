package com.northcoders.recordshop.service;

import com.northcoders.recordshop.model.Album;
import com.northcoders.recordshop.model.AlbumDetails;
import com.northcoders.recordshop.model.Genre;

public class DTOBuilder {

    public static AlbumDetails buildDTO(Album album) {
        return new AlbumDetails(
                album.getName(),
                Integer.parseInt(album.getReleaseYear()),
                Genre.valueOf(album.getGenre()),
                album.getArtist(),
                album.getImageUrl()
        );
    }
}
