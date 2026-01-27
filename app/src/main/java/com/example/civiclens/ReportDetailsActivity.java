package com.example.civiclens;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.civiclens.databinding.ActivityReportDetailsBinding;

/**
 * ReportDetailsActivity - Report Details Display Screen
 * Part of Experiment 2: UI Design
 * Displays full details of a selected report
 */
public class ReportDetailsActivity extends AppCompatActivity {

    private ActivityReportDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReportDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupToolbar();
        loadReportDetails();
        setupClickListeners();
    }

    private void setupToolbar() {
        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        binding.toolbar.setNavigationOnClickListener(v -> finish());
    }

    private void loadReportDetails() {
        // TODO: Load report details from database (Experiment 10)
        // For now, showing placeholder data from layout

        // Example: Get report ID from intent
        // int reportId = getIntent().getIntExtra("REPORT_ID", -1);
        // Load data based on reportId
    }

    private void setupClickListeners() {
        // View on map button
        binding.btnViewOnMap.setOnClickListener(v -> {
            // TODO: Open map fragment with this location (Experiment 3 & 7)
            Toast.makeText(this, "Map view will be implemented in Experiment 3 & 7", Toast.LENGTH_SHORT).show();
        });

        // Edit report button
        binding.btnEditReport.setOnClickListener(v -> {
            // TODO: Navigate to edit screen (Experiment 3)
            Toast.makeText(this, "Edit functionality will be implemented later", Toast.LENGTH_SHORT).show();
        });

        // Delete report button
        binding.btnDeleteReport.setOnClickListener(v -> {
            // TODO: Show confirmation dialog and delete from database
            Toast.makeText(this, "Delete functionality will be implemented later", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
