package com.northcoders.recordshop.ui.main;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.northcoders.recordshop.ui.postalbum.PostAlbumActivity;

public class MainActivityClickHandler {
    private Context context;

    public MainActivityClickHandler(Context context) {
        this.context = context;
    }

    public void onClick(View view){
        Intent intent = new Intent(context, PostAlbumActivity.class);
        context.startActivity(intent);
    };

}
