package ru.nsu.ccfit.kafka_midpoint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import ru.nsu.ccfit.kafka_midpoint.kafka_consumer.KafkaConsumerConfig;


@SpringBootApplication
@Import(value = KafkaConsumerConfig.class)
public class KafkaMidpointApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaMidpointApplication.class, args);
	}

}
