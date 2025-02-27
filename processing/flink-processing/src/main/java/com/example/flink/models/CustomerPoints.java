package com.example.flink.models;
import java.io.Serializable;


public class CustomerPoints implements  Serializable {
    private String customerId;
    private int points;
    private long lastUpdated;

    public CustomerPoints(String customerId, int points, long lastUpdated) {
        this.customerId = customerId;
        this.points = points;
        this.lastUpdated = lastUpdated;
    }

    // Getters & Setters
    public String getCustomerId() { return customerId; }
    public int getPoints() { return points; }
    public long getLastUpdated() { return lastUpdated; }

    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public void setPoints(int points) { this.points = points; }
    public void setLastUpdated(long lastUpdated) { this.lastUpdated = lastUpdated; }

    // toString method for better readability
    @Override
    public String toString() {
        return "CustomerPoints{" +
                "customerId='" + customerId + '\'' +
                ", points=" + points +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}
