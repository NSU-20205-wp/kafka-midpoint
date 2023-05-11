package ru.nsu.ccfit.kafka_midpoint.kafka.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class KafkaProducerApp {
    public static void main(String[] args) {
        SpringApplication.run(KafkaProducerApp.class, args);
    }
}

