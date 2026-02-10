package com.example.civiclens;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;

public class PostItem {
    private final @DrawableRes int avatarRes;
    private final String author;
    private final String timeAgo;
    private final String category;
    private final String body;
    private final @Nullable Integer imageRes;
    private final int likeCount;
    private final int commentCount;

    public PostItem(
            @DrawableRes int avatarRes,
            String author,
            String timeAgo,
            String category,
            String body,
            @Nullable Integer imageRes,
            int likeCount,
            int commentCount
    ) {
        this.avatarRes = avatarRes;
        this.author = author;
        this.timeAgo = timeAgo;
        this.category = category;
        this.body = body;
        this.imageRes = imageRes;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
    }

    public int getAvatarRes() {
        return avatarRes;
    }

    public String getAuthor() {
        return author;
    }

    public String getTimeAgo() {
        return timeAgo;
    }

    public String getCategory() {
        return category;
    }

    public String getBody() {
        return body;
    }

    @Nullable
    public Integer getImageRes() {
        return imageRes;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    /** Copy with updated like count (Exp 4: event-driven like button). */
    public PostItem withLikeCount(int newLikeCount) {
        return new PostItem(avatarRes, author, timeAgo, category, body, imageRes, newLikeCount, commentCount);
    }
}

