package com.example.civiclens;

import androidx.annotation.DrawableRes;

public class MyReportItem {
    private final String title;
    private final String category;
    private final String status;
    private final String date;
    private final @DrawableRes int iconRes;

    public MyReportItem(String title, String category, String status, String date, @DrawableRes int iconRes) {
        this.title = title;
        this.category = category;
        this.status = status;
        this.date = date;
        this.iconRes = iconRes;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }

    public int getIconRes() {
        return iconRes;
    }
}

