package com.example.civiclens;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/** Exp 4: List with click (navigate) and swipe-to-delete via ItemTouchHelper. */
public class MyReportAdapter extends RecyclerView.Adapter<MyReportAdapter.VH> {
    private List<MyReportItem> items;

    public MyReportAdapter(List<MyReportItem> items) {
        this.items = items;
    }

    public void setItems(List<MyReportItem> newItems) {
        this.items = newItems;
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        if (position >= 0 && position < items.size()) {
            items.remove(position);
            notifyItemRemoved(position);
        }
    }

    public MyReportItem getItem(int position) {
        if (position >= 0 && position < items.size()) return items.get(position);
        return null;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_report, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        private final ImageView ivCategoryIcon;
        private final TextView tvTitle;
        private final TextView tvCategory;
        private final TextView tvStatus;
        private final TextView tvDate;

        VH(@NonNull View itemView) {
            super(itemView);
            ivCategoryIcon = itemView.findViewById(R.id.ivCategoryIcon);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvCategory = itemView.findViewById(R.id.tvCategory);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvDate = itemView.findViewById(R.id.tvDate);
        }

        void bind(MyReportItem item) {
            ivCategoryIcon.setImageResource(item.getIconRes());
            tvTitle.setText(item.getTitle());
            tvCategory.setText(item.getCategory());
            tvStatus.setText(item.getStatus());
            tvDate.setText(item.getDate());

            int colorRes;
            switch (item.getStatus()) {
                case "Pending":
                    colorRes = R.color.status_pending;
                    break;
                case "In Progress":
                    colorRes = R.color.status_in_progress;
                    break;
                case "Resolved":
                    colorRes = R.color.status_resolved;
                    break;
                default:
                    colorRes = R.color.text_secondary;
            }
            tvStatus.setTextColor(ContextCompat.getColor(itemView.getContext(), colorRes));

            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), ReportDetailsActivity.class);
                intent.putExtra(ReportDetailsActivity.EXTRA_TITLE, item.getTitle());
                intent.putExtra(ReportDetailsActivity.EXTRA_CATEGORY, item.getCategory());
                intent.putExtra(ReportDetailsActivity.EXTRA_STATUS, item.getStatus());
                // Reuse "date" slot for the details page datetime row
                intent.putExtra(ReportDetailsActivity.EXTRA_DATE, item.getDate());
                itemView.getContext().startActivity(intent);
            });
        }
    }
}

