package com.example.civiclens;

/**
 * Report model class
 */
public class Report {
    private String title;
    private String description;
    private String location;
    private String timestamp;
    private String status;
    private boolean isSynced;

    public Report(String title, String description, String location, String timestamp, String status, boolean isSynced) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.timestamp = timestamp;
        this.status = status;
        this.isSynced = isSynced;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getStatus() {
        return status;
    }

    public boolean isSynced() {
        return isSynced;
    }
}
