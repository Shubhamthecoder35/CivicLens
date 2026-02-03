package com.example.civiclens;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private com.example.civiclens.databinding.ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = com.example.civiclens.databinding.ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavHelper.setup(this, binding.bottomNav, R.id.nav_home);
        setupFeed();
    }

    private void setupFeed() {
        binding.recyclerFeed.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerFeed.setAdapter(new PostAdapter(createDummyPosts()));

        // UI-only search bar
        binding.tvSearchHint.setOnClickListener(v ->
                android.widget.Toast.makeText(this, "Search (UI demo)", android.widget.Toast.LENGTH_SHORT).show()
        );
    }

    private List<PostItem> createDummyPosts() {
        List<PostItem> items = new ArrayList<>();

        items.add(new PostItem(
                R.drawable.avatar,
                "Aurelia Kincaid",
                "2 hours ago",
                "Roads",
                "I reported a pothole on Elm Street last week, and it's still not fixed. Can someone look into this?",
                R.drawable.pothole_on_street,
                10,
                5
        ));

        items.add(new PostItem(
                R.drawable.avatar,
                "Marcellus Voss",
                "12 hours ago",
                "Lighting",
                "The streetlights on 5th Avenue are malfunctioning. They flicker on and off randomly.",
                null,
                0,
                0
        ));

        return items;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
