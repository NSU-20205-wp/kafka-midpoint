package ru.nsu.ccfit.kafka_midpoint.kafka.consumer;

import lombok.NoArgsConstructor;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;

@NoArgsConstructor
@Component
@KafkaListener(topics = "${topic.name.consumer}", groupId = "${spring.kafka.consumer.group-id}")
public class MyKafkaListener {
    private static final Logger logger = Logger.getLogger(MyKafkaListener.class.getName());

    private BlockingQueue<String> blockingQueue;

    public void setBlockingQueue(BlockingQueue<String> blockingQueue) {
        logger.info(() -> "initialization of blockingQueue");
        this.blockingQueue = blockingQueue;
        if (blockingQueue == null) {
            throw new IllegalStateException("blockingQueue must not be null");
        }
    }

    @KafkaHandler(isDefault = true)
    public void messageHandler(String msg) {
        logger.info(() -> "Received: " + msg + "\n");
        try {
            blockingQueue.put(msg);
        } catch (InterruptedException e) {
            logger.info(e::getMessage);
        }
    }

}
