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
 * MapFragment - Interactive Map Display
 * Part of Experiment 2: UI Design
 * Part of Experiment 3: Fragments (navigation)
 * Part of Experiment 7: GPS & Location (future)
 */
public class MapFragment extends Fragment {

    private FragmentMapBinding binding;

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
    }

    private void setupClickListeners() {
        // Filter button
        binding.btnFilter.setOnClickListener(v -> {
            // TODO: Show filter dialog (Experiment 4)
            Toast.makeText(getContext(), "Filter dialog will be implemented in Experiment 4", Toast.LENGTH_SHORT).show();
        });

        // My Location button
        binding.btnMyLocation.setOnClickListener(v -> {
            // TODO: Center map on user's current location (Experiment 7)
            Toast.makeText(getContext(), "Location tracking will be implemented in Experiment 7", Toast.LENGTH_SHORT).show();
        });

        // Search functionality
        // TODO: Implement search/autocomplete (Experiment 4)
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
