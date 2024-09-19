package com.northcoders.recordshop.viewmodel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.northcoders.recordshop.R;
import com.northcoders.recordshop.databinding.AlbumRecylerBinding;
import com.northcoders.recordshop.model.Album;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {
    private List<Album> albumList;
    private Context context;

    public static class AlbumViewHolder extends RecyclerView.ViewHolder{
        private AlbumRecylerBinding albumRecylerBinding;

        public AlbumViewHolder(AlbumRecylerBinding albumRecylerBinding){
            super(albumRecylerBinding.getRoot());
            this.albumRecylerBinding = albumRecylerBinding;
        }
    }

    public AlbumAdapter(List<Album> albumList, Context context){
        this.albumList = albumList;
        this.context = context;
    }

    @BindingAdapter("android:src")
    public static void setImageUri(ImageView view, String imageUri){
        Picasso.get()
                .load(imageUri)
                .into(view);
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AlbumRecylerBinding albumRecylerBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.album_recyler, parent, false);
        return new AlbumViewHolder(albumRecylerBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        Album album = albumList.get(position);
        holder.albumRecylerBinding.setRecord(album);
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }
}
