package com.example.civiclens;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.civiclens.databinding.ActivityMapBinding;

/**
 * MapActivity - Map Container Activity
 * Part of Experiment 3: Multiple Screens with Intents and Fragments
 * 
 * Demonstrates:
 * - Intent navigation (launched from other activities)
 * - Fragment hosting and transactions
 * - Fragment-to-Activity communication via interface
 */
public class MapActivity extends AppCompatActivity implements MapFragment.MapFragmentListener {

    private ActivityMapBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupToolbar();
        
        // Load fragment only if not already loaded (handles configuration changes)
        if (savedInstanceState == null) {
            loadMapFragment();
        }
    }

    private void setupToolbar() {
        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        binding.toolbar.setNavigationOnClickListener(v -> finish());
    }

    /**
     * Demonstrates Fragment Transaction
     * This is a key part of Experiment 3
     */
    private void loadMapFragment() {
        MapFragment mapFragment = MapFragment.newInstance();
        mapFragment.setListener(this);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, mapFragment, "MAP_FRAGMENT");
        transaction.commit();
    }

    /**
     * Interface implementation for Fragment-to-Activity communication
     * Demonstrates how fragments communicate with their host activity
     */
    @Override
    public void onLocationSelected(double latitude, double longitude) {
        String message = String.format("Location received from Fragment:\nLat: %.4f, Lng: %.4f", 
            latitude, longitude);
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        
        // In a real app, you would:
        // - Set result to pass data back to calling activity
        // - Save to database (Experiment 10)
        // - Update UI in other fragments
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
