package com.example.flink;

import org.apache.flink.api.common.functions.MapFunction;
import java.time.Instant;
import com.example.flink.models.Transaction;
import com.example.flink.models.CustomerPoints;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoyaltyPointsCalculator implements MapFunction<Transaction, CustomerPoints> {
    private static final Logger logger = LoggerFactory.getLogger(TransactionParser.class);
    @Override
    public CustomerPoints map(Transaction transaction) {
        logger.info("Transaction inside customer points: {}", transaction);
        int points = (int) transaction.getAmount();  
        long lastUpdated = Instant.now().toEpochMilli(); 
        CustomerPoints customerpoints=new CustomerPoints(transaction.getCustomerId(), points, lastUpdated);
        logger.info("Successfully produce customerPoints: {}", customerpoints);
        return customerpoints;
    }
}
