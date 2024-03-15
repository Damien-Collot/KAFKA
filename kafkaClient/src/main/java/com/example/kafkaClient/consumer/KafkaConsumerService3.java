package com.example.kafkaClient.consumer;

import com.example.kafkaClient.model.DataSynchronizer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class KafkaConsumerService3 {

    @KafkaListener(topics = "topic3", groupId = "${spring.kafka.consumer.group-id}")
    public void receiveMessage(String message) {
        if(message == null) return;

        String[] messageParts = message.split("&");
        if(messageParts.length != 2) return;

        String requestKey = messageParts[0];
        String body = messageParts[1];

        DataSynchronizer.addData(requestKey, body);

        System.out.println("Message received: " + body);

    }

}
