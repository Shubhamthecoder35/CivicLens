package com.example.civiclens;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.civiclens.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

/** Exp 4: Buttons (chips, like, comment, share) and gestures (SwipeRefreshLayout). */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private List<PostItem> allPosts;
    private List<PostItem> filteredPosts;
    private PostAdapter feedAdapter;
    private String selectedCategoryFilter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavHelper.setup(this, binding.bottomNav, R.id.nav_home);
        allPosts = createDummyPosts();
        filteredPosts = new ArrayList<>(allPosts);
        setupFeed();
        setupSwipeRefresh();
        setupCategoryChips();
        binding.tvSearchHint.setOnClickListener(v -> Toast.makeText(this, "Search (UI demo)", Toast.LENGTH_SHORT).show());
        binding.mapArea.setOnClickListener(v -> {
            startActivity(new Intent(this, MapActivity.class));
        });
    }

    private void setupFeed() {
        binding.recyclerFeed.setLayoutManager(new LinearLayoutManager(this));
        feedAdapter = new PostAdapter(filteredPosts);
        feedAdapter.setOnPostActionListener(new PostAdapter.OnPostActionListener() {
            @Override
            public void onLikeClick(int position) {
                if (position >= 0 && position < filteredPosts.size()) {
                    PostItem item = filteredPosts.get(position);
                    filteredPosts.set(position, item.withLikeCount(item.getLikeCount() + 1));
                    feedAdapter.notifyItemChanged(position);
                }
            }

            @Override
            public void onCommentClick(int position) {
                Toast.makeText(MainActivity.this, "Comments (coming soon)", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onShareClick(int position) {
                if (position >= 0 && position < filteredPosts.size()) {
                    PostItem item = filteredPosts.get(position);
                    Intent share = new Intent(Intent.ACTION_SEND);
                    share.setType("text/plain");
                    share.putExtra(Intent.EXTRA_TEXT, item.getCategory() + ": " + item.getBody());
                    startActivity(Intent.createChooser(share, "Share post"));
                }
            }
        });
        binding.recyclerFeed.setAdapter(feedAdapter);
    }

    private void setupSwipeRefresh() {
        binding.swipeRefreshFeed.setOnRefreshListener(() -> {
            binding.swipeRefreshFeed.setRefreshing(true);
            binding.recyclerFeed.postDelayed(() -> {
                filteredPosts.clear();
                filteredPosts.addAll(allPosts);
                applyCategoryFilter();
                feedAdapter.notifyDataSetChanged();
                binding.swipeRefreshFeed.setRefreshing(false);
                Toast.makeText(this, "Feed refreshed", Toast.LENGTH_SHORT).show();
            }, 800);
        });
    }

    private void setupCategoryChips() {
        binding.chipRoads.setOnClickListener(v -> selectCategoryFilter("Roads"));
        binding.chipLighting.setOnClickListener(v -> selectCategoryFilter("Lighting"));
        binding.chipWaste.setOnClickListener(v -> selectCategoryFilter("Waste"));
        binding.chipTransport.setOnClickListener(v -> selectCategoryFilter("Transport"));
    }

    private void selectCategoryFilter(String category) {
        selectedCategoryFilter = category;
        filteredPosts.clear();
        filteredPosts.addAll(allPosts);
        applyCategoryFilter();
        feedAdapter.notifyDataSetChanged();
        Toast.makeText(this, "Filter: " + category, Toast.LENGTH_SHORT).show();
    }

    private void applyCategoryFilter() {
        if (selectedCategoryFilter != null) {
            List<PostItem> keep = new ArrayList<>();
            for (PostItem p : filteredPosts) {
                if (selectedCategoryFilter.equals(p.getCategory())) keep.add(p);
            }
            filteredPosts.clear();
            filteredPosts.addAll(keep);
        }
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
