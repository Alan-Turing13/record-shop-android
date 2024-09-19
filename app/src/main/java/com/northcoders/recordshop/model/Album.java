package com.northcoders.recordshop.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class Album extends BaseObservable {
    private long id;
    private String name;
    private int releaseYear;
    private Genre genre;
    private String artist;
    private String imageUrl;

    public Album(long id, String name, int releaseYear, Genre genre, String artist) {
        this.id = id;
        this.name = name;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.artist = artist;
    }

    public Album(String name, int releaseYear, Genre genre, String artist) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.artist = artist;
    }

    public Album(String name, int releaseYear, Genre genre, String artist, String imageUrl) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.artist = artist;
        this.imageUrl = imageUrl;
    }

    @Bindable
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Bindable
    public String getReleaseYear() {
        return String.valueOf(releaseYear);
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Bindable
    public String getGenre() {
        return String.valueOf(genre);
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Bindable
    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    @Bindable
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
