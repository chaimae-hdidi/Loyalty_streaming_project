
package com.example.flink;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




public class myKafkaSource {
    private static final Logger logger = LoggerFactory.getLogger(myKafkaSource.class);

    public static KafkaSource<String> createKafkaSource(String topic, String brokers) 
    {
        logger.info("Creating KafkaSource for topic: {} with brokers: {}", topic, brokers); 
        KafkaSource<String> kafkaSource =null;
        try{
             kafkaSource = KafkaSource.<String>builder()
                .setBootstrapServers(brokers)
                .setTopics(topic)
                .setGroupId("flink-consumer-group")
                .setStartingOffsets(OffsetsInitializer.latest())
                .setValueOnlyDeserializer(new SimpleStringSchema())
                .build();
       
            logger.info("KafkaSource created successfully for topic: {}", topic);
        }
        catch(Exception e){
            logger.info("Error while creating kafka source for topic {}",topic, e);
        }
        if (kafkaSource==null)
        {
            logger.warn("Kafka Source is nullfor topic {}",topic);
        }
        return kafkaSource;

    }

}
