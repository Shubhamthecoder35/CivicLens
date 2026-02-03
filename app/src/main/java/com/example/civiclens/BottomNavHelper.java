package com.example.civiclens;

import android.app.Activity;
import android.content.Intent;

import androidx.annotation.IdRes;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public final class BottomNavHelper {
    private BottomNavHelper() {}

    public static void setup(Activity activity, BottomNavigationView bottomNav, @IdRes int selectedItemId) {
        bottomNav.setItemIconTintList(ContextCompat.getColorStateList(activity, R.color.bottom_nav_item_color));
        bottomNav.setItemTextColor(ContextCompat.getColorStateList(activity, R.color.bottom_nav_item_color));
        bottomNav.setSelectedItemId(selectedItemId);

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == selectedItemId) return true;

            Intent intent;
            if (id == R.id.nav_home) {
                intent = new Intent(activity, MainActivity.class);
            } else if (id == R.id.nav_report) {
                intent = new Intent(activity, CreateReportActivity.class);
            } else if (id == R.id.nav_my_report) {
                intent = new Intent(activity, MyReportsActivity.class);
            } else if (id == R.id.nav_profile) {
                intent = new Intent(activity, ProfileActivity.class);
            } else {
                return false;
            }

            activity.startActivity(intent);
            activity.overridePendingTransition(0, 0);
            activity.finish();
            return true;
        });
    }
}

