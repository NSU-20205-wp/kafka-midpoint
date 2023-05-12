package ru.nsu.ccfit.kafka_midpoint.kafka.consumer;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.nsu.ccfit.kafka_midpoint.kafka.producer.controller.TopicProducer;
import ru.nsu.ccfit.kafka_midpoint.processing.ProcessMessageTask;

import java.util.concurrent.*;
import java.util.logging.Logger;

@RequiredArgsConstructor
@Component
@KafkaListener(topics = "${topic.name.consumer}", groupId = "${spring.kafka.consumer.group-id}")
public class MyKafkaListener {
    private static final Logger logger = Logger.getLogger(MyKafkaListener.class.getName());
    private ExecutorService service = null;
    private final TopicProducer producer;

    @KafkaHandler(isDefault = true)
    public void messageHandler(String msg) {
        logger.info(() -> "Received: " + msg + "\n");
        if (service == null) {
            service = Executors.newSingleThreadExecutor();
        }
        Callable<String> task = new ProcessMessageTask(msg);
        try {
            CompletableFuture.supplyAsync(() -> {
                try {
                    return task.call();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }, service).thenAcceptAsync(producer::send);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
