package ru.nsu.ccfit.kafka_midpoint.kafka.producer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.DefaultErrorHandler;
import ru.nsu.ccfit.kafka_midpoint.kafka.BaseKafkaTestConfig;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

@EnableKafka
@Configuration
@Import(BaseKafkaTestConfig.class)
public class TopicProducerTestConfig extends BaseKafkaTestConfig {
    private static final Logger logger = Logger.getLogger(TopicProducerTestConfig.class.getName());


    private final String BOOTSTRAP_SERVERS = "localhost:29102";

    @Override
    protected HashMap<String, Object> getConsumerProperties() {
        HashMap<String, Object> props = super.getConsumerProperties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        return props;
    }

    @Override
    protected HashMap<String, Object> getProducerProperties() {
        HashMap<String, Object> props = super.getProducerProperties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        return props;
    }

    @Bean
    public ConsumerFactory<String, Object> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(getConsumerProperties());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setCommonErrorHandler(errorHandler());
        return factory;
    }

    @Bean
    public DefaultErrorHandler errorHandler() {
        return new DefaultErrorHandler((consumerRecord, e) -> logger.log(Level.SEVERE, () -> ("consumed record" +
                consumerRecord.toString() + "because this exception was thrown")));
    }

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(getProducerProperties());
    }

    @Bean
    public KafkaConsumer<String, String> kafkaConsumer() {
        HashMap<String, Object> props = getConsumerProperties();
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "midpointResponseIdTest");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        return new KafkaConsumer<>(props);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
