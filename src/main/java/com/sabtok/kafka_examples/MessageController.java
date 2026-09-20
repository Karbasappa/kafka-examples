package com.sabtok.kafka_examples;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private final KafkaProducerService producerService;

    public MessageController(KafkaProducerService producerService) {
        this.producerService = producerService;
    }

    // Example URL: http://localhost:8080/send?msg=HelloKafka
    @GetMapping("/send")
    public String publishMessage(@RequestParam("msg") String message) {
        producerService.sendMessage(message);
        return "Message sent to Kafka successfully!";
    }

    @GetMapping("/send1")
    public String publishMessage1(@RequestParam("msg") String message) throws InterruptedException {
        for (int i = 0; i < 10; i++) {

            System.out.println("Publishing message "+ message + " connect " + i);
            producerService.sendMessage(message + " connect " + i);
            System.out.println("Published message "+ message + " connect " + i);
            Thread.sleep(3000);
        }

        return "Message sent to Kafka successfully!";
    }

}
