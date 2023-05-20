package ru.nsu.ccfit.kafka_midpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import ru.nsu.ccfit.kafka_midpoint.kafka.consumer.KafkaConsumerConfig;
import ru.nsu.ccfit.kafka_midpoint.kafka.consumer.MyKafkaListener;
import ru.nsu.ccfit.kafka_midpoint.kafka.producer.KafkaProducerConfig;
import ru.nsu.ccfit.kafka_midpoint.processing.MessageProcessExecutor;

import java.util.concurrent.LinkedBlockingQueue;


@SpringBootApplication
@Import(value = {KafkaConsumerConfig.class, KafkaProducerConfig.class})
public class KafkaMidpointApplication {
	@Autowired
	private MessageProcessExecutor messageProcessExecutor;

	@Autowired
	private MyKafkaListener myKafkaListener;

	public static void main(String[] args) {
		SpringApplication.run(KafkaMidpointApplication.class, args);
	}

	@Bean
	public void createAssociated() {
		LinkedBlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>();
		myKafkaListener.setBlockingQueue(blockingQueue);
		messageProcessExecutor.setBlockingQueue(blockingQueue);
	}

}
