package com.example.civiclens;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.List;

/** Exp 4: Button handling - like, comment, share clicks. */
public class PostAdapter extends RecyclerView.Adapter<PostAdapter.VH> {
    private final List<PostItem> items;
    private OnPostActionListener actionListener;

    public interface OnPostActionListener {
        void onLikeClick(int position);
        void onCommentClick(int position);
        void onShareClick(int position);
    }

    public PostAdapter(List<PostItem> items) {
        this.items = items;
    }

    public void setOnPostActionListener(OnPostActionListener listener) {
        this.actionListener = listener;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
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
        private final ImageView ivAvatar;
        private final TextView tvAuthor;
        private final TextView tvTime;
        private final TextView tvCategory;
        private final TextView tvBody;
        private final MaterialCardView cardImage;
        private final ImageView ivPostImage;
        private final TextView tvLikeCount;
        private final TextView tvCommentCount;

        VH(@NonNull View itemView) {
            super(itemView);
            ivAvatar = itemView.findViewById(R.id.ivAvatar);
            tvAuthor = itemView.findViewById(R.id.tvAuthor);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvCategory = itemView.findViewById(R.id.tvCategory);
            tvBody = itemView.findViewById(R.id.tvBody);
            cardImage = itemView.findViewById(R.id.cardImage);
            ivPostImage = itemView.findViewById(R.id.ivPostImage);
            tvLikeCount = itemView.findViewById(R.id.tvLikeCount);
            tvCommentCount = itemView.findViewById(R.id.tvCommentCount);
        }

        void bind(PostItem item) {
            ivAvatar.setImageResource(item.getAvatarRes());
            tvAuthor.setText(item.getAuthor());
            tvTime.setText(item.getTimeAgo());
            tvCategory.setText(item.getCategory());
            tvBody.setText(item.getBody());

            Integer imageRes = item.getImageRes();
            if (imageRes == null) {
                cardImage.setVisibility(View.GONE);
            } else {
                cardImage.setVisibility(View.VISIBLE);
                ivPostImage.setImageResource(imageRes);
            }

            tvLikeCount.setText(String.valueOf(item.getLikeCount()));
            tvCommentCount.setText(String.valueOf(item.getCommentCount()));

            // Exp 4: button event handling
            View ivLike = itemView.findViewById(R.id.ivLike);
            View ivComment = itemView.findViewById(R.id.ivComment);
            View ivShare = itemView.findViewById(R.id.ivShare);
            PostAdapter adapter = (PostAdapter) getBindingAdapter();
            if (adapter != null && adapter.actionListener != null) {
                ivLike.setOnClickListener(v -> adapter.actionListener.onLikeClick(getBindingAdapterPosition()));
                ivComment.setOnClickListener(v -> adapter.actionListener.onCommentClick(getBindingAdapterPosition()));
                ivShare.setOnClickListener(v -> adapter.actionListener.onShareClick(getBindingAdapterPosition()));
            }
        }
    }
}

