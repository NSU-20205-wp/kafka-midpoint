package ru.nsu.ccfit.kafka_midpoint.kafka.consumer;

import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@KafkaListener(id = "multiId", topics = "multiTopic")
public class MyKafkaListener {
    private static final Logger logger = Logger.getLogger(MyKafkaListener.class.getName());

    @KafkaHandler(isDefault = true)
    public void messageHandler(Object object) {
        logger.info(() -> "\nReceived: " + object + " object type: " + object.getClass() + "\n");
    }

}
