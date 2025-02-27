package com.example.flink;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;


import com.example.flink.TransactionParser;
import com.example.flink.LoyaltyPointsCalculator;
import com.example.flink.models.Transaction;
import com.example.flink.models.CustomerPoints;
import com.example.flink.myKafkaSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class TransactionProcessingApp {

	private static final Logger logger = LoggerFactory.getLogger(TransactionProcessingApp.class);


    public static void main(String[] args) throws Exception {
		

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

   
        String topic = "loyalty_points_.public.transactions";
        String brokers = "kafka:9093";
		KafkaSource<String> kafkaConsumer = myKafkaSource.createKafkaSource(topic, brokers);


		DataStream<String> rawTransactions = env.fromSource(kafkaConsumer, WatermarkStrategy.noWatermarks(), "Kafka Source");
        DataStream<Transaction> transactions = rawTransactions.flatMap(new TransactionParser());  
        DataStream<CustomerPoints> customerPoints = transactions.map(new LoyaltyPointsCalculator());
		


        env.execute("Transaction Processing App");
    }
}
