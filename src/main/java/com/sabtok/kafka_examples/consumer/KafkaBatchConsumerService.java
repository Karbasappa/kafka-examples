package com.sabtok.kafka_examples.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KafkaBatchConsumerService {

    @KafkaListener(topics = "kk-with-4-part", groupId = "my-batch-consumer-group")
    public void receiveBatch(List<ConsumerRecord<String, String>> records, Acknowledgment ack) {

        System.out.println("--- START PROCESSING NEW BATCH ---");
        System.out.println("Pulled "+ records.size()+" records from Kafka partitions.");

        try {
            // Process all records in the batch together (ideal for bulk database inserts)
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("Processing from Partition: "+record.partition()+" , Offset: "+ record.offset()
                        +" , Key: "+record.key()+", Value: "+record.value());

                // Your business logic goes here...
            }

            // Acknowledge the ENTIRE batch at once after successful processing
            ack.acknowledge();
            System.out.println("--- BATCH SUCCESS: Offsets committed safely ---");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
