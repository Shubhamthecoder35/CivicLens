package com.example.civiclens;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * RecyclerView Adapter for displaying civic reports in cards
 */
public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ReportViewHolder> {

    private List<Report> reports;

    public ReportAdapter(List<Report> reports) {
        this.reports = reports;
    }

    @NonNull
    @Override
    public ReportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_report, parent, false);
        return new ReportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportViewHolder holder, int position) {
        Report report = reports.get(position);
        holder.bind(report);
    }

    @Override
    public int getItemCount() {
        return reports.size();
    }

    /**
     * ViewHolder for report items
     */
    static class ReportViewHolder extends RecyclerView.ViewHolder {
        private TextView tvReportTitle;
        private TextView tvReportLocation;
        private TextView tvReportDescription;
        private TextView tvReportTimestamp;
        private TextView tvReportStatus;
        private ImageView ivSyncStatus;

        public ReportViewHolder(@NonNull View itemView) {
            super(itemView);
            tvReportTitle = itemView.findViewById(R.id.tvReportTitle);
            tvReportLocation = itemView.findViewById(R.id.tvReportLocation);
            tvReportDescription = itemView.findViewById(R.id.tvReportDescription);
            tvReportTimestamp = itemView.findViewById(R.id.tvReportTimestamp);
            tvReportStatus = itemView.findViewById(R.id.tvReportStatus);
            ivSyncStatus = itemView.findViewById(R.id.ivSyncStatus);
        }

        public void bind(Report report) {
            tvReportTitle.setText(report.getTitle());
            tvReportLocation.setText(report.getLocation());
            tvReportDescription.setText(report.getDescription());
            tvReportTimestamp.setText(report.getTimestamp());
            tvReportStatus.setText(report.getStatus());

            // Set status color
            int statusColor;
            switch (report.getStatus()) {
                case "PENDING":
                    statusColor = Color.parseColor("#FF9800");
                    break;
                case "IN PROGRESS":
                    statusColor = Color.parseColor("#2196F3");
                    break;
                case "RESOLVED":
                    statusColor = Color.parseColor("#4CAF50");
                    break;
                case "REJECTED":
                    statusColor = Color.parseColor("#F44336");
                    break;
                default:
                    statusColor = Color.parseColor("#757575");
            }
            tvReportStatus.setBackgroundColor(statusColor);

            // Set sync status visibility
            ivSyncStatus.setVisibility(report.isSynced() ? View.VISIBLE : View.GONE);
        }
    }

    public void updateReports(List<Report> newReports) {
        this.reports = newReports;
        notifyDataSetChanged();
    }
}
