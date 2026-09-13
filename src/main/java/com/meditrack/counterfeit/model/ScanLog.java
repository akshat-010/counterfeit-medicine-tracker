package com.meditrack.counterfeit.model;

import java.time.LocalDateTime;

public class ScanLog {
    private String userId;
    private String userRole;
    private String generatedHash;
    private double longitude;
    private double latitude;
    private LocalDateTime timestamp;

    public ScanLog(String userId, String userRole, String generatedHash, double longitude, double latitude) {
        this.userId = userId;
        this.userRole = userRole;
        this.generatedHash = generatedHash;
        this.longitude = longitude;
        this.latitude = latitude;
        this.timestamp = LocalDateTime.now();
    }

    public String getUserId() {
        return userId;
    }

    public String getUserRole() {
        return userRole;
    }

    public String getGeneratedHash() {
        return generatedHash;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String toString() {
        return "ScanLog{" +
                "userId='" + userId + '\'' +
                ", userRole='" + userRole + '\'' +
                ", generatedHash='" + generatedHash + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
