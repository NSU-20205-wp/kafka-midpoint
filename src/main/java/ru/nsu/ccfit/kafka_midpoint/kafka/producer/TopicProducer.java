package ru.nsu.ccfit.kafka_midpoint.kafka.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TopicProducer {
    @Value("${topic.name.producer}")
    private String topicName;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String message) {
        log.info("New message: {}", message);
        kafkaTemplate.send(topicName, message);
    }
}