package com.example.civiclens;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.civiclens.databinding.ActivityCreateReportBinding;

/**
 * CreateReportActivity - Report Creation Screen
 * Part of Experiment 2: UI Design
 * Part of Experiment 7: GPS Integration (future)
 * Part of Experiment 8: Camera Integration (future)
 * Part of Experiment 10: Room Database (future)
 */
public class CreateReportActivity extends AppCompatActivity {

    private ActivityCreateReportBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateReportBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupToolbar();
        setupCategoryDropdown();
        setupClickListeners();
    }

    private void setupToolbar() {
        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        binding.toolbar.setNavigationOnClickListener(v -> finish());
    }

    private void setupCategoryDropdown() {
        // Categories for civic issues
        String[] categories = new String[]{
                "Road Infrastructure",
                "Waste Management",
                "Street Lights",
                "Water Supply",
                "Drainage",
                "Public Safety",
                "Other"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                categories
        );
        binding.actvCategory.setAdapter(adapter);
    }

    private void setupClickListeners() {
        // Camera/Photo capture button
        binding.fabCapturePhoto.setOnClickListener(v -> {
            // TODO: Implement CameraX integration (Experiment 8)
            Toast.makeText(this, "Camera will be implemented in Experiment 8", Toast.LENGTH_SHORT).show();
        });

        // Refresh location button
        binding.btnRefreshLocation.setOnClickListener(v -> {
            // TODO: Fetch GPS coordinates (Experiment 7)
            Toast.makeText(this, "GPS will be implemented in Experiment 7", Toast.LENGTH_SHORT).show();
        });

        // Save draft button
        binding.btnSaveDraft.setOnClickListener(v -> {
            // TODO: Save to Room Database (Experiment 10)
            Toast.makeText(this, "Save draft will be implemented in Experiment 10", Toast.LENGTH_SHORT).show();
        });

        // Submit report button
        binding.btnSubmitReport.setOnClickListener(v -> {
            // TODO: Validate and save to database
            Toast.makeText(this, "Submit will be implemented in Experiment 10", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
