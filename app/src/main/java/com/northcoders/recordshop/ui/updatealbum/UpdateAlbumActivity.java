package com.northcoders.recordshop.ui.updatealbum;

import android.os.Build;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.northcoders.recordshop.R;
import com.northcoders.recordshop.databinding.ActivityUpdateAlbumBinding;
import com.northcoders.recordshop.model.Album;
import com.northcoders.recordshop.viewmodel.MainActivityViewModel;

public class UpdateAlbumActivity extends AppCompatActivity {

    private ActivityUpdateAlbumBinding binding;
    private UpdateAlbumClickHandler handler;
    private Album album;
    private static final String ALBUM_KEY = "album";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_album);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            album = getIntent().getParcelableExtra(ALBUM_KEY, Album.class);
        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_update_album);
        binding.setAlbum(album);

        MainActivityViewModel viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        handler = new UpdateAlbumClickHandler(album, this, viewModel, album.getId());
        binding.setClickHandler(handler);
    };

}