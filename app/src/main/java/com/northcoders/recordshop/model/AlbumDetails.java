package com.northcoders.recordshop.model;

import static com.northcoders.recordshop.model.Genre.POP;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class AlbumDetails extends BaseObservable implements Parcelable {
    private String name;
    private int releaseYear;
    private Genre genre;
    private String artist;
    private String imageUrl;

    public AlbumDetails(String name, int releaseYear, Genre genre, String artist) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.artist = artist;
    }

    public AlbumDetails(String name, int releaseYear, Genre genre, String artist, String imageUrl) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.artist = artist;
        this.imageUrl = imageUrl;
    }

    protected AlbumDetails(Parcel in) {
        name = in.readString();
        releaseYear = in.readInt();
        genre = Genre.valueOf(in.readString());
        artist = in.readString();
        imageUrl = in.readString();
    }

    public AlbumDetails() {
        this.releaseYear = 0;
        this.genre = POP;
    }

    public static final Parcelable.Creator<Album> CREATOR = new Parcelable.Creator<Album>() {
        @Override
        public Album createFromParcel(Parcel in) {
            return new Album(in);
        }

        @Override
        public Album[] newArray(int size) {
            return new Album[size];
        }
    };

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
//        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getReleaseYear() {
        return String.valueOf(releaseYear);
    }

    public void setReleaseYear(String releaseYear) {
        try {
            this.releaseYear = Integer.parseInt(releaseYear);
        } catch (NumberFormatException n){
            ;
        }
//        notifyPropertyChanged(BR.releaseYear);
    }

    @Bindable
    public String getGenre() {
        return String.valueOf(genre);
    }

    public void setGenre(String genre) {
        try {
            this.genre = Genre.valueOf(genre);
//            notifyPropertyChanged(BR.genre);
        } catch (IllegalArgumentException e){
            this.genre = Genre.valueOf("POP");
//            notifyPropertyChanged(BR.genre);
        }
//        notifyPropertyChanged(BR.genre);
    }

    @Bindable
    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
//        notifyPropertyChanged(BR.artist);
    }

    @Bindable
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
//        notifyPropertyChanged(BR.imageUrl);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(releaseYear);
        dest.writeString(String.valueOf(genre));
        dest.writeString(artist);
        dest.writeString(imageUrl);
    }

    @Override
    public String toString() {
        return "Album{name=" + name + ", releaseYear=" + releaseYear + ", genre=" + genre + ", artist=" + artist + ", imageUrl=" + imageUrl + "}";
    }
}
