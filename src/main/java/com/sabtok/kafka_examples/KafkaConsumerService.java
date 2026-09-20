package com.sabtok.kafka_examples;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "kk-with-3-part", groupId = "my-first-consumer-group")
    public void listen(String message) {
        System.out.println("<-- Received message in Consumer: " + message);
    }

    @KafkaListener(topics = "kk-with-3-part", groupId = "my-first-consumer-group")
    public void listen1(String message) {
        System.out.println("<--Hi %%%%%%%%%%%%%%%%%%55 Received message in Consumer: " + message);
    }
}
