package com.example.civiclens;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // UI Components
    private MaterialToolbar toolbar;
    private TextView tvTotalReports;
    private TextView tvPendingReports;
    private TextView tvResolvedReports;
    private RecyclerView recyclerViewReports;
    private SwipeRefreshLayout swipeRefreshLayout;
    private FloatingActionButton btnCreateReport;
    
    // Data
    private ReportAdapter reportAdapter;
    private List<Report> reportsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        initializeViews();

        // Setup UI components
        setupToolbar();
        setupRecyclerView();  // Must be called before setupStatistics
        setupStatistics();
        setupSwipeRefresh();
        setupFAB();
    }

    /**
     * Initialize all view components
     */
    private void initializeViews() {
        toolbar = findViewById(R.id.toolbar);
        tvTotalReports = findViewById(R.id.tvTotalReports);
        tvPendingReports = findViewById(R.id.tvPendingReports);
        tvResolvedReports = findViewById(R.id.tvResolvedReports);
        recyclerViewReports = findViewById(R.id.recyclerViewReports);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        btnCreateReport = findViewById(R.id.btnCreateReport);
    }

    /**
     * Setup toolbar with Material Design
     */
    private void setupToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("CivicLens");
        }
    }

    /**
     * Setup statistics cards with data from reports
     * Demonstrates responsive LinearLayout within ConstraintLayout
     */
    private void setupStatistics() {
        // Count statistics from reports
        int total = reportsList.size();
        int pending = 0;
        int resolved = 0;
        
        for (Report report : reportsList) {
            if (report.getStatus().equals("PENDING")) {
                pending++;
            } else if (report.getStatus().equals("RESOLVED")) {
                resolved++;
            }
        }
        
        tvTotalReports.setText(String.valueOf(total));
        tvPendingReports.setText(String.valueOf(pending));
        tvResolvedReports.setText(String.valueOf(resolved));
    }

    /**
     * Setup RecyclerView for reports list
     * Populates with dummy data to demonstrate card layouts
     */
    private void setupRecyclerView() {
        recyclerViewReports.setLayoutManager(new LinearLayoutManager(this));
        
        // Create dummy data
        reportsList = createDummyReports();
        
        // Setup adapter
        reportAdapter = new ReportAdapter(reportsList);
        recyclerViewReports.setAdapter(reportAdapter);
    }
    
    /**
     * Create dummy reports for demonstration
     */
    private List<Report> createDummyReports() {
        List<Report> reports = new ArrayList<>();
        
        reports.add(new Report(
                "Pothole on Main Street",
                "Large pothole causing traffic issues. Needs immediate attention.",
                "Lat: 12.9716, Lon: 77.5946",
                "2 hours ago",
                "PENDING",
                false
        ));
        
        reports.add(new Report(
                "Broken Street Light",
                "Street light not working for 3 days. Area is dark at night.",
                "Lat: 12.9345, Lon: 77.6123",
                "5 hours ago",
                "IN PROGRESS",
                true
        ));
        
        reports.add(new Report(
                "Overflowing Garbage Bin",
                "Garbage bin near park is overflowing. Health hazard.",
                "Lat: 12.9512, Lon: 77.5898",
                "1 day ago",
                "RESOLVED",
                true
        ));
        
        reports.add(new Report(
                "Damaged Sidewalk",
                "Cracked sidewalk tiles causing difficulty for pedestrians.",
                "Lat: 12.9654, Lon: 77.5734",
                "2 days ago",
                "PENDING",
                false
        ));
        
        reports.add(new Report(
                "Water Leakage",
                "Continuous water leakage from municipal pipe.",
                "Lat: 12.9421, Lon: 77.5987",
                "3 days ago",
                "IN PROGRESS",
                true
        ));
        
        reports.add(new Report(
                "Illegal Dumping",
                "Construction waste dumped on public property.",
                "Lat: 12.9789, Lon: 77.6012",
                "1 week ago",
                "RESOLVED",
                true
        ));
        
        return reports;
    }

    /**
     * Setup SwipeRefreshLayout for pull-to-refresh functionality
     * Demonstrates gesture handling (will be enhanced in Experiment 4)
     */
    private void setupSwipeRefresh() {
        swipeRefreshLayout.setColorSchemeResources(
                R.color.primary_color,
                R.color.secondary_color
        );

        swipeRefreshLayout.setOnRefreshListener(() -> {
            // Simulate refresh operation
            Toast.makeText(MainActivity.this, "Refreshing reports...", Toast.LENGTH_SHORT).show();

            // Stop refreshing animation and update data
            swipeRefreshLayout.postDelayed(() -> {
                // Refresh data (in real app, this would fetch from server/database)
                reportAdapter.updateReports(reportsList);
                setupStatistics();
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(MainActivity.this, "Reports refreshed", Toast.LENGTH_SHORT).show();
            }, 1500);
        });
    }

    /**
     * Setup Floating Action Button for creating new reports
     * Will navigate to camera screen in Experiment 7
     */
    private void setupFAB() {
        btnCreateReport.setOnClickListener(view -> {
            Toast.makeText(MainActivity.this,
                    "Create Report - Camera will be integrated in Experiment 7",
                    Toast.LENGTH_SHORT).show();
            // Navigation will be added in Experiment 3
        });
    }
}
