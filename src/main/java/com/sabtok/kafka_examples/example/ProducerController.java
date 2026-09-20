package com.sabtok.kafka_examples.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/send")
public class ProducerController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/msg")
    public String sendMessage() {
        // send() returns a CompletableFuture<SendResult<String, String>>
        String message = "Hello karbasapp sunil ";

        /*
        kafkaTemplate.send("kk-with-3-part", message)
                .thenAccept(result -> {
                    // This block runs ONLY after an ACK is received from the broker
                    System.out.println("Successfully sent message "+message+" to partition  "+ result.getRecordMetadata().partition()+
                            " at offset "+ result.getRecordMetadata().offset());
                })
                .exceptionally(ex -> {
                    System.out.println("Failed to send message due to: " + ex);
                    return null;
                });

*/

       // Fire a burst of 6 messages with unique keys to distribute across partitions
        for (int i = 0; i < 6; i++) {
            String uniqueKey = "key-" + i;
            String fullMessage = message + " #" + i;

            kafkaTemplate.send("kk-with-3-part", uniqueKey, fullMessage)
                    .thenAccept(result -> {
                        System.out.println("Successfully sent message [" + result.getProducerRecord().value() +
                                "] to partition " + result.getRecordMetadata().partition() +
                                " at offset " + result.getRecordMetadata().offset());
                    })
                    .exceptionally(ex -> {
                        System.out.println("Failed to send message due to: " + ex);
                        return null;
                    });
        }

        return "Request submitted successfully";
    }


}
