package com.example.flink.models;

import java.io.Serializable;

public class Transaction implements Serializable {
    private String customerId;
    private double amount;
    private String orderId;
    private long transactionTime;

    // Constructor
    public Transaction(String customerId, double amount, String orderId, long transactionTime) {
        this.customerId = customerId;
        this.amount = amount;
        this.orderId = orderId;
        this.transactionTime = transactionTime;
    }

    // Getters
    public String getCustomerId() {
        return customerId;
    }

    public double getAmount() {
        return amount;
    }

    public String getOrderId() {
        return orderId;
    }

    public long getTransactionTime() {
        return transactionTime;
    }

    // toString() for logging/debugging
    @Override
    public String toString() {
        return "Transaction{" +
                "customerId='" + customerId + '\'' +
                ", amount=" + amount +
                ", orderId='" + orderId + '\'' +
                ", transactionTime=" + transactionTime +
                '}';
    }
}
 