package com.northcoders.recordshop;

import android.app.Application;
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

import com.northcoders.recordshop.databinding.ActivityMainBinding;
import com.northcoders.recordshop.model.Album;
import com.northcoders.recordshop.repository.AlbumRepository;
import com.northcoders.recordshop.viewmodel.AlbumAdapter;
import com.northcoders.recordshop.viewmodel.MainActivityViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Album> albumList;
    AlbumAdapter albumAdapter;
    MainActivityViewModel mainActivityViewModel;
    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.recycler), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
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
        albumAdapter = new AlbumAdapter(albumList, this);
        recyclerView.setAdapter(albumAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        albumAdapter.notifyDataSetChanged();
    }
}