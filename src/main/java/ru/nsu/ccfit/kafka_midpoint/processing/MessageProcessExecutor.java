package ru.nsu.ccfit.kafka_midpoint.processing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.nsu.ccfit.kafka_midpoint.kafka.producer.controller.TopicProducer;

import java.io.IOException;
import java.util.concurrent.*;
import java.util.logging.Logger;


@Component
public class MessageProcessExecutor {
    private static final Logger logger = Logger.getLogger(MessageProcessExecutor.class.getName());

    private ExecutorService service = null;
    private final TopicProducer producer;
    private BlockingQueue<String> blockingQueue;

    @Autowired
    public MessageProcessExecutor(TopicProducer producer) {
        this.producer = producer;
    }

    public void setBlockingQueue(BlockingQueue<String> blockingQueue) throws IOException {
        logger.info(() -> "initialization of objectInputStream");
        this.blockingQueue = blockingQueue;
        start();
    }

    private void start() {
        logger.info(() -> "preparing executor thread...");
        Thread thread = new Thread(() -> {
            while(true) {
                try {
                    String msg = blockingQueue.take();
                    if (service == null) {
                        service = Executors.newSingleThreadExecutor();
                    }
                    Callable<String> task = new ProcessMessageTask(msg);
                    CompletableFuture.supplyAsync(() -> {
                        try {
                            return task.call();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }, service).thenAcceptAsync(producer::send);
                }
                catch (Exception e) {
                    logger.warning(e::getMessage);
                }
            }
        });
        logger.info(() -> "starting executor thread...");
        thread.start();
    }


}
