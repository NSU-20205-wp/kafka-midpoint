package ru.nsu.ccfit.kafka_midpoint.kafka.listener;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.test.context.EmbeddedKafka;
import ru.nsu.ccfit.kafka_midpoint.kafka.consumer.MyKafkaListener;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


@SpringBootTest(classes = MyKafkaListener.class)
@EmbeddedKafka(
        partitions = 1,
        brokerProperties = {
                "listeners=PLAINTEXT://localhost:29101",
                "port=29101",
        })
@Import(value = KafkaListenerTestConfig.class)
class MyKafkaListenerTest {
    private static final String TOPIC = "topic.midpointRequest";

    @Autowired
    private MyKafkaListener myKafkaListener;

    @Autowired
    private KafkaProducer<String, String> producer;


    @Test
    void testMessageConsuming() {
        LinkedBlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>();
        myKafkaListener.setBlockingQueue(blockingQueue);

        try {
            Thread.sleep(10000);
            String message = "hello for MyKafkaListener";
            producer.send(new ProducerRecord<>(TOPIC, message));

            String element = blockingQueue.poll(10, TimeUnit.SECONDS);
            assertEquals(message, element);

        }
        catch (InterruptedException e) {
            e.printStackTrace();
            fail();
        }
    }
}
