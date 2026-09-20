package com.sabtok.kafka_examples;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    // Spring autowires this template automatically based on application.yml settings
    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        for (int i = 0; i < 10; i++) {
            kafkaTemplate.send("kk-with-3-part", "id-" + i, "Hello Sunil karbasappa " + i);
        }
    }

}
