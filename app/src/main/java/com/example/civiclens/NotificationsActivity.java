package com.example.civiclens;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.civiclens.databinding.ActivityNotificationsBinding;

/**
 * NotificationsActivity - Notifications Display Screen
 * Part of Experiment 2: UI Design
 * Part of Experiment 9: Push Notifications (future)
 */
public class NotificationsActivity extends AppCompatActivity {

    private ActivityNotificationsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotificationsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupToolbar();
        setupRecyclerView();
        setupTabs();
        setupClickListeners();
        setupSwipeRefresh();

        showEmptyState(true);
    }

    private void setupSwipeRefresh() {
        binding.swipeRefreshNotifications.setOnRefreshListener(() -> {
            binding.swipeRefreshNotifications.setRefreshing(true);
            binding.rvNotifications.postDelayed(() -> {
                binding.swipeRefreshNotifications.setRefreshing(false);
                Toast.makeText(this, "Notifications refreshed", Toast.LENGTH_SHORT).show();
            }, 600);
        });
    }

    private void setupToolbar() {
        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        binding.toolbar.setNavigationOnClickListener(v -> finish());
    }

    private void setupRecyclerView() {
        binding.rvNotifications.setLayoutManager(new LinearLayoutManager(this));
        // TODO: Set adapter with notification data (Experiment 9)
    }

    private void setupTabs() {
        binding.tabLayout.addOnTabSelectedListener(new com.google.android.material.tabs.TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(com.google.android.material.tabs.TabLayout.Tab tab) {
                // TODO: Filter notifications based on selected tab
                Toast.makeText(NotificationsActivity.this, 
                    "Filter: " + tab.getText(), 
                    Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabUnselected(com.google.android.material.tabs.TabLayout.Tab tab) {
                // Not needed
            }

            @Override
            public void onTabReselected(com.google.android.material.tabs.TabLayout.Tab tab) {
                // Not needed
            }
        });
    }

    private void setupClickListeners() {
        binding.fabClearAll.setOnClickListener(v -> {
            showEmptyState(true);
            Toast.makeText(this, "All notifications cleared", Toast.LENGTH_SHORT).show();
        });
    }

    private void showEmptyState(boolean show) {
        binding.layoutEmptyState.setVisibility(show ? View.VISIBLE : View.GONE);
        binding.rvNotifications.setVisibility(show ? View.GONE : View.VISIBLE);
        binding.fabClearAll.setVisibility(show ? View.GONE : View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
