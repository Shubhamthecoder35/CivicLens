package com.example.civiclens;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.civiclens.databinding.ActivityReportDetailsBinding;

/**
 * ReportDetailsActivity - Report Details Display Screen
 * Exp 4: Button handling (back, share, edit, delete, map card).
 */
public class ReportDetailsActivity extends AppCompatActivity {

    private ActivityReportDetailsBinding binding;

    public static final String EXTRA_TITLE = "EXTRA_TITLE";
    public static final String EXTRA_CATEGORY = "EXTRA_CATEGORY";
    public static final String EXTRA_STATUS = "EXTRA_STATUS";
    public static final String EXTRA_DATE = "EXTRA_DATE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReportDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupClickListeners();
        bindFromIntent();
    }

    private void setupClickListeners() {
        binding.btnBack.setOnClickListener(v -> finish());

        binding.btnShare.setOnClickListener(v -> {
            String title = binding.tvTitle.getText() != null ? binding.tvTitle.getText().toString() : "";
            String desc = binding.tvDescription.getText() != null ? binding.tvDescription.getText().toString() : "";
            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("text/plain");
            share.putExtra(Intent.EXTRA_TEXT, title + "\n\n" + desc);
            startActivity(Intent.createChooser(share, "Share report"));
        });

        binding.btnEdit.setOnClickListener(v -> Toast.makeText(this, "Edit (coming soon)", Toast.LENGTH_SHORT).show());

        binding.btnDelete.setOnClickListener(v -> new AlertDialog.Builder(this)
                .setTitle("Delete report?")
                .setMessage("This report will be removed. This cannot be undone.")
                .setPositiveButton(android.R.string.ok, (d, w) -> {
                    setResult(RESULT_OK);
                    finish();
                })
                .setNegativeButton(android.R.string.cancel, null)
                .show());

        binding.mapCard.setOnClickListener(v -> startActivity(new Intent(this, MapActivity.class)));
    }

    private void bindFromIntent() {
        String title = getIntent().getStringExtra(EXTRA_TITLE);
        String category = getIntent().getStringExtra(EXTRA_CATEGORY);
        String status = getIntent().getStringExtra(EXTRA_STATUS);
        String date = getIntent().getStringExtra(EXTRA_DATE);

        if (title == null) title = "Large pothole near Mexico Square";
        if (category == null) category = "Roads";
        if (status == null) status = "In Progress";
        if (date == null) date = "2 Mar 2025, 10:15 AM";

        binding.tvTopTitle.setText(title);
        binding.tvTitle.setText(title);
        binding.tvCategoryPill.setText(category);
        binding.tvStatus.setText(status);
        binding.tvDatetime.setText(date);

        // Static sample content to match the Figma screen
        binding.tvDescription.setText("There’s a deep pothole on the right lane near Mexico Square that’s causing heavy traffic and posing a safety risk for drivers and cyclists.");
        binding.tvUpdate1.setText("3 Mar 2025 — Assigned to Road Maintenance Department");
        binding.tvUpdate2.setText("3 Mar 2025 — Repair team dispatched to location");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
