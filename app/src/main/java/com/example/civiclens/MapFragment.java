package com.example.civiclens;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.civiclens.databinding.FragmentMapBinding;

/**
 * MapFragment - Interactive Map Display Fragment
 * Part of Experiment 3: Multiple Screens with Intents and Fragments
 * Demonstrates Fragment lifecycle and communication with host Activity
 */
public class MapFragment extends Fragment {

    private FragmentMapBinding binding;
    private MapFragmentListener listener;

    /**
     * Interface for communicating with the host Activity
     */
    public interface MapFragmentListener {
        void onLocationSelected(double latitude, double longitude);
    }

    public static MapFragment newInstance() {
        return new MapFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMapBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupClickListeners();
        updateLocationDisplay();
    }

    private void setupClickListeners() {
        // Zoom controls
        binding.btnZoomIn.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Zoom In (Map integration in Experiment 7)", Toast.LENGTH_SHORT).show();
        });

        binding.btnZoomOut.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Zoom Out (Map integration in Experiment 7)", Toast.LENGTH_SHORT).show();
        });

        // Recenter button
        binding.btnRecenter.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Recentering to current location", Toast.LENGTH_SHORT).show();
            updateLocationDisplay();
        });

        // Select location button
        binding.btnSelectLocation.setOnClickListener(v -> {
            // Communicate with Activity through interface
            if (listener != null) {
                listener.onLocationSelected(40.7128, -74.0060);
            }
            Toast.makeText(getContext(), "Location selected!", Toast.LENGTH_SHORT).show();
        });
    }

    private void updateLocationDisplay() {
        // Simulated location data (will be replaced with real GPS in Experiment 7)
        binding.tvLocationAddress.setText("Mexico Square, City Center");
        binding.tvCoordinates.setText("Lat: 40.7128, Lng: -74.0060");
    }

    /**
     * Set listener for fragment-to-activity communication
     * Called by the host Activity
     */
    public void setListener(MapFragmentListener listener) {
        this.listener = listener;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
