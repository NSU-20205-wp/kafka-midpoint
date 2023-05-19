package ru.nsu.ccfit.kafka_midpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import ru.nsu.ccfit.kafka_midpoint.kafka.consumer.KafkaConsumerConfig;
import ru.nsu.ccfit.kafka_midpoint.kafka.consumer.MyKafkaListener;
import ru.nsu.ccfit.kafka_midpoint.processing.MessageProcessExecutor;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.concurrent.LinkedBlockingQueue;


@SpringBootApplication
@Import(value = KafkaConsumerConfig.class)
public class KafkaMidpointApplication {
	@Autowired
	private MessageProcessExecutor executor;

	@Autowired
	private MyKafkaListener listener;

	public static void main(String[] args) {
		SpringApplication.run(KafkaMidpointApplication.class, args);
	}

	@Bean
	public void createAssociated() throws IOException  {
		LinkedBlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>();
		listener.setBlockingQueue(blockingQueue);
		executor.setBlockingQueue(blockingQueue);
	}

}
