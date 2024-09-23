package com.northcoders.recordshop.ui.main;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface {

    RecyclerView recyclerView;
    List<Album> albumList;
    AlbumAdapter albumAdapter;
    MainActivityViewModel mainActivityViewModel;
    ActivityMainBinding activityMainBinding;
    MainActivityClickHandler mainActivityClickHandler;
    private static final String ALBUM_KEY = "album";
    private SearchView searchView;
    private ArrayList<Album> filteredAlbums;

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

        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // submits text on return
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                // executes the method directly as text is input
                if (!newText.isEmpty()) {
                    filterList(newText);
                    return true;
                }
                return false;
            }
        });

    }

    private void filterList(String newText) {
        filteredAlbums = new ArrayList<>();
        try {
            for (Album album : albumList) {
                if (album.getName().toLowerCase().contains(newText.toLowerCase())) {
                    filteredAlbums.add(album);
                }
            }
        } catch (NullPointerException e){
            getAllAlbums();
        }
        if (filteredAlbums.isEmpty()){
            Toast.makeText(MainActivity.this, "No albums found", Toast.LENGTH_SHORT).show();
        } else {
            albumAdapter.setFilteredAlbums(filteredAlbums);
        }
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
        if (filteredAlbums == null || filteredAlbums.isEmpty()){
            intent.putExtra(ALBUM_KEY, albumList.get(position));
        } else {
            intent.putExtra(ALBUM_KEY, filteredAlbums.get(position));
        }
        startActivity(intent);
    }

}