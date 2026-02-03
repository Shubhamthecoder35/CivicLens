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

        BottomNavHelper.setup(this, binding.bottomNav, R.id.nav_report);
        setupDropdowns();
        setupClickListeners();
    }

    private void setupDropdowns() {
        // Categories for civic issues (Figma dropdown)
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
        binding.actvCategory.setOnClickListener(v -> binding.actvCategory.showDropDown());

        String[] locations = new String[]{
                "Current Location",
                "Home",
                "Work",
                "Other"
        };
        ArrayAdapter<String> locationAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                locations
        );
        binding.actvLocation.setAdapter(locationAdapter);
        binding.actvLocation.setOnClickListener(v -> binding.actvLocation.showDropDown());
    }

    private void setupClickListeners() {
        binding.btnHelp.setOnClickListener(v ->
                Toast.makeText(this, "Help (coming soon)", Toast.LENGTH_SHORT).show()
        );

        binding.cardAddPhoto.setOnClickListener(v ->
                Toast.makeText(this, "Add Photo (coming soon)", Toast.LENGTH_SHORT).show()
        );

        binding.btnSubmit.setOnClickListener(v -> {
            // TODO: Validate and submit (Experiment 10/11)
            Toast.makeText(this, "Submitted (UI demo)", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
