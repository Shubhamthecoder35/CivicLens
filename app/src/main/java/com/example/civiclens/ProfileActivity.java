package com.example.civiclens;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.civiclens.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity {

    private ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavHelper.setup(this, binding.bottomNav, R.id.nav_profile);
        setupFeed();
        setupSwipeRefresh();
        setupTabClicks();

        binding.btnSettings.setOnClickListener(v -> Toast.makeText(this, "Settings (UI demo)", Toast.LENGTH_SHORT).show());
        binding.btnEditProfile.setOnClickListener(v -> Toast.makeText(this, "Edit Profile (UI demo)", Toast.LENGTH_SHORT).show());
    }

    private void setupSwipeRefresh() {
        binding.swipeRefreshProfile.setOnRefreshListener(() -> {
            binding.swipeRefreshProfile.setRefreshing(true);
            binding.scroll.postDelayed(() -> {
                binding.swipeRefreshProfile.setRefreshing(false);
                android.widget.Toast.makeText(this, "Profile refreshed", android.widget.Toast.LENGTH_SHORT).show();
            }, 600);
        });
    }

    private void setupTabClicks() {
        binding.tabPosts.setOnClickListener(v -> android.widget.Toast.makeText(this, "Posts tab", android.widget.Toast.LENGTH_SHORT).show());
        binding.tabLikes.setOnClickListener(v -> android.widget.Toast.makeText(this, "Likes tab", android.widget.Toast.LENGTH_SHORT).show());
        binding.tabComments.setOnClickListener(v -> android.widget.Toast.makeText(this, "Comments tab", android.widget.Toast.LENGTH_SHORT).show());
        binding.tabShares.setOnClickListener(v -> android.widget.Toast.makeText(this, "Shares tab", android.widget.Toast.LENGTH_SHORT).show());
    }

    private void setupFeed() {
        binding.recyclerProfileFeed.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerProfileFeed.setAdapter(new PostAdapter(java.util.Collections.singletonList(
                new PostItem(
                        R.drawable.avatar,
                        "John Cena",
                        "22 hours ago",
                        "Safety",
                        "The pedestrian signal at the main intersection near Mexico Square is not functioning. Cars don’t stop, and people are forced to cross dangerously. This puts children and elderly at high risk. Please fix urgently.",
                        R.drawable.pothole_on_street,
                        10,
                        5
                )
        )));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}

