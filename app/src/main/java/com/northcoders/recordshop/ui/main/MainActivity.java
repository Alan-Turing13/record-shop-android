package com.northcoders.recordshop.ui.main;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.northcoders.recordshop.R;
import com.northcoders.recordshop.databinding.ActivityMainBinding;
import com.northcoders.recordshop.model.Album;
import com.northcoders.recordshop.repository.AlbumRepository;
import com.northcoders.recordshop.ui.updatealbum.UpdateAlbumActivity;
import com.northcoders.recordshop.viewmodel.AlbumAdapter;
import com.northcoders.recordshop.viewmodel.MainActivityViewModel;
import com.northcoders.recordshop.viewmodel.RecyclerViewInterface;

import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface {

    RecyclerView recyclerView;
    List<Album> albumList;
    AlbumAdapter albumAdapter;
    MainActivityViewModel mainActivityViewModel;
    ActivityMainBinding activityMainBinding;
    MainActivityClickHandler mainActivityClickHandler;
    private static final String ALBUM_KEY = "album";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        mainActivityViewModel.init(this.getApplication());
        mainActivityClickHandler = new MainActivityClickHandler(this);
        activityMainBinding.setPost(mainActivityClickHandler);
        Application app = new Application();
        AlbumRepository albumRepository = new AlbumRepository(app);
        albumRepository.getMutableLiveData();

        getAllAlbums();
    }

    private void getAllAlbums() {
        mainActivityViewModel.getAllAlbums().observe(this, new Observer<List<Album>>() {
            @Override
            public void onChanged(List<Album> albums) {
                albumList = albums;
                displayAlbums();
            }
        });
    }

    private void displayAlbums() {
        recyclerView = activityMainBinding.recycler;
        albumAdapter = new AlbumAdapter(albumList, this, this);
        recyclerView.setAdapter(albumAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        albumAdapter.notifyDataSetChanged();
    }

    @Override
    public void onAlbumClick(int position) {
        Intent intent = new Intent(MainActivity.this, UpdateAlbumActivity.class);
        intent.putExtra(ALBUM_KEY, albumList.get(position));
        startActivity(intent);
    }

}