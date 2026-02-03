package com.example.civiclens;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.civiclens.databinding.ActivityMyReportsBinding;

import java.util.ArrayList;
import java.util.List;

public class MyReportsActivity extends AppCompatActivity {

    private ActivityMyReportsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyReportsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavHelper.setup(this, binding.bottomNav, R.id.nav_my_report);
        setupFilters();
        setupList();

        binding.btnFilter.setOnClickListener(v ->
                Toast.makeText(this, "Filter (UI demo)", Toast.LENGTH_SHORT).show()
        );
    }

    private void setupFilters() {
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                new String[]{"All", "Roads", "Lighting", "Waste", "Transport", "Parks"}
        );
        binding.actvFilterCategory.setAdapter(categoryAdapter);
        binding.actvFilterCategory.setOnClickListener(v -> binding.actvFilterCategory.showDropDown());

        ArrayAdapter<String> statusAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                new String[]{"All", "Pending", "In Progress", "Resolved"}
        );
        binding.actvFilterStatus.setAdapter(statusAdapter);
        binding.actvFilterStatus.setOnClickListener(v -> binding.actvFilterStatus.showDropDown());

        ArrayAdapter<String> dateAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                new String[]{"Newest", "Oldest"}
        );
        binding.actvFilterDate.setAdapter(dateAdapter);
        binding.actvFilterDate.setOnClickListener(v -> binding.actvFilterDate.showDropDown());
    }

    private void setupList() {
        binding.recyclerMyReports.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerMyReports.setAdapter(new MyReportAdapter(createDummyItems()));
    }

    private List<MyReportItem> createDummyItems() {
        List<MyReportItem> items = new ArrayList<>();
        items.add(new MyReportItem("Large pothole near Mexico Square", "Roads", "In Progress", "2 Mar", R.drawable.ic_category_road));
        items.add(new MyReportItem("Streetlight not working at Bole Road", "Lighting", "Pending", "4 Mar", R.drawable.ic_category_light));
        items.add(new MyReportItem("Garbage overflow at Meskel Square", "Waste", "Resolved", "28 Feb", R.drawable.ic_category_waste));
        items.add(new MyReportItem("Bus delays on route 15", "Transport", "In Progress", "5 Mar", R.drawable.ic_category_bus));
        items.add(new MyReportItem("Damaged swing in Jan Meda park", "Parks", "Pending", "3 Mar", R.drawable.ic_category_park));
        return items;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}

