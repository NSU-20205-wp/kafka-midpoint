package ru.nsu.ccfit.kafka_midpoint.kafka.producer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = TopicProducer.class)
@EmbeddedKafka(
        partitions = 1,
        brokerProperties = {
                "listeners=PLAINTEXT://localhost:29102",
                "port=29102",
        })
@Import(value = KafkaTestConfig.class)
class TopicProducerTest {
    private static final String TOPIC = "topic.midpointResponse";

    @Autowired
    private TopicProducer topicProducer;

    @Autowired
    private KafkaConsumer<String, String> kafkaConsumer;

    @Test
    void publishMessageTest() {
        String message = "hello from TopicProducer";
        topicProducer.send(message);
        kafkaConsumer.subscribe(Pattern.compile(TOPIC));
        ConsumerRecords<String, String> records = KafkaTestUtils.getRecords(kafkaConsumer);
        ConsumerRecord<String, String> receivedRecord = records.iterator().next();

        assertEquals(message, receivedRecord.value());
    }
}
