package com.example.civiclens;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
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
                    statusColor = ContextCompat.getColor(itemView.getContext(), R.color.status_pending);
                    break;
                case "IN PROGRESS":
                    statusColor = ContextCompat.getColor(itemView.getContext(), R.color.status_in_progress);
                    break;
                case "RESOLVED":
                    statusColor = ContextCompat.getColor(itemView.getContext(), R.color.status_resolved);
                    break;
                case "REJECTED":
                    statusColor = ContextCompat.getColor(itemView.getContext(), R.color.status_rejected);
                    break;
                default:
                    statusColor = ContextCompat.getColor(itemView.getContext(), R.color.text_secondary);
            }
            Drawable bg = tvReportStatus.getBackground();
            if (bg instanceof GradientDrawable) {
                ((GradientDrawable) bg.mutate()).setColor(statusColor);
            } else {
                tvReportStatus.setBackgroundColor(statusColor);
            }

            // Set sync status visibility
            ivSyncStatus.setVisibility(report.isSynced() ? View.VISIBLE : View.GONE);
        }
    }

    public void updateReports(List<Report> newReports) {
        this.reports = newReports;
        notifyDataSetChanged();
    }
}
