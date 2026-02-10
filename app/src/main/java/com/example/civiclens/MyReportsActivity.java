package com.example.civiclens;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.civiclens.databinding.ActivityMyReportsBinding;

import java.util.ArrayList;
import java.util.List;

/** Exp 4: Buttons (filter apply) and gestures (SwipeRefresh, swipe-to-delete). */
public class MyReportsActivity extends AppCompatActivity {

    private ActivityMyReportsBinding binding;
    private List<MyReportItem> allItems;
    private List<MyReportItem> displayedItems;
    private MyReportAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyReportsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavHelper.setup(this, binding.bottomNav, R.id.nav_my_report);
        allItems = createDummyItems();
        displayedItems = new ArrayList<>(allItems);
        setupFilters();
        setupList();
        setupSwipeRefresh();
        setupSwipeToDelete();

        binding.btnFilter.setOnClickListener(v -> applyFilters());
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

    private void applyFilters() {
        String category = binding.actvFilterCategory.getText() != null ? binding.actvFilterCategory.getText().toString().trim() : "";
        String status = binding.actvFilterStatus.getText() != null ? binding.actvFilterStatus.getText().toString().trim() : "";
        if (category.isEmpty()) category = "All";
        if (status.isEmpty()) status = "All";

        displayedItems.clear();
        for (MyReportItem item : allItems) {
            boolean matchCat = "All".equals(category) || category.equals(item.getCategory());
            boolean matchStatus = "All".equals(status) || status.equals(item.getStatus());
            if (matchCat && matchStatus) displayedItems.add(item);
        }
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "Filter applied", Toast.LENGTH_SHORT).show();
    }

    private void setupList() {
        binding.recyclerMyReports.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyReportAdapter(displayedItems);
        binding.recyclerMyReports.setAdapter(adapter);
    }

    private void setupSwipeRefresh() {
        binding.swipeRefreshMyReports.setOnRefreshListener(() -> {
            binding.swipeRefreshMyReports.setRefreshing(true);
            binding.recyclerMyReports.postDelayed(() -> {
                displayedItems.clear();
                displayedItems.addAll(allItems);
                adapter.notifyDataSetChanged();
                binding.swipeRefreshMyReports.setRefreshing(false);
                Toast.makeText(this, "Report status refreshed", Toast.LENGTH_SHORT).show();
            }, 700);
        });
    }

    private void setupSwipeToDelete() {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView rv, RecyclerView.ViewHolder vh, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int pos = viewHolder.getBindingAdapterPosition();
                adapter.removeItem(pos);
                Toast.makeText(MyReportsActivity.this, "Report removed", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(binding.recyclerMyReports);
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

