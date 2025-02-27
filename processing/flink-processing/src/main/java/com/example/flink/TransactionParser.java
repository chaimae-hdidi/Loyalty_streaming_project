package com.example.flink;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.util.Collector;
import com.example.flink.models.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Base64;

public class TransactionParser implements FlatMapFunction<String, Transaction> {
    private static final Logger logger = LoggerFactory.getLogger(TransactionParser.class);
    
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void flatMap(String json, Collector<Transaction> out) throws Exception {
        if (json == null) {
            logger.warn("Received null message from Kafka");
            return;
        }
        logger.info("Attempting to parse transaction JSON...");

        try {
            JsonNode node = objectMapper.readTree(json);
            JsonNode afterNode = node.path("payload").path("after");
            logger.info("this is the Payload {}", afterNode);
         
            if (!afterNode.has("customer_id") || !afterNode.has("amount") || !afterNode.has("order_id") || !afterNode.has("transaction_time")) {
                logger.warn("Invalid JSON structure: {}", json);
                return;
            }

            String customerId = afterNode.get("customer_id").asText();
            String amountBase64 = afterNode.get("amount").asText();
            String orderId = afterNode.get("order_id").asText();
            long transactionTime = afterNode.get("transaction_time").asLong();

            // Decode Base64-encoded amount
            byte[] decodedBytes = Base64.getDecoder().decode(amountBase64);
            BigDecimal amountBigDecimal = new BigDecimal(new BigInteger(decodedBytes), 2);
            double amount = amountBigDecimal.doubleValue(); // Convert to double

            // Create Transaction object
            Transaction transaction = new Transaction(customerId, amount, orderId, transactionTime);
            
            logger.info("Successfully parsed transaction: {}", transaction);
            out.collect(transaction);
            
        } catch (Exception e) {
            logger.error("Error parsing transaction JSON: {} - Exception: {}", json, e.getMessage(), e);
        }
    }
}
