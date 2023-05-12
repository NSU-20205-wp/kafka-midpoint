package ru.nsu.ccfit.kafka_midpoint.kafka.consumer;

import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@KafkaListener(topics = "${topic.name.consumer}", groupId = "${spring.kafka.consumer.group-id}")
public class MyKafkaListener {
    private static final Logger logger = Logger.getLogger(MyKafkaListener.class.getName());

    @KafkaHandler(isDefault = true)
    public void messageHandler(String msg) {
        logger.info(() -> "Received: " + msg + "\n");
    }

}
