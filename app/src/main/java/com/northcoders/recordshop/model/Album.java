package com.northcoders.recordshop.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

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

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = Integer.parseInt(releaseYear);
        notifyPropertyChanged(BR.releaseYear);
    }

    @Bindable
    public String getGenre() {
        return String.valueOf(genre);
    }

    public void setGenre(String genre) {
        try {
            this.genre = Genre.valueOf(genre);
            notifyPropertyChanged(BR.genre);
        } catch (IllegalArgumentException e){
            this.genre = Genre.valueOf("POP");
            notifyPropertyChanged(BR.genre);
        }
    }

    @Bindable
    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
        notifyPropertyChanged(BR.artist);
    }

    @Bindable
    public String getImageUrl() {
        return imageUrl;
    }

    // TODO: add error handling if not a valid URL
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        notifyPropertyChanged(BR.imageUrl);
    }
}
