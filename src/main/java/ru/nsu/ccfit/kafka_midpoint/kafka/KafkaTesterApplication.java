package ru.nsu.ccfit.kafka_midpoint.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.nsu.ccfit.kafka_midpoint.kafka.config.KafkaProducerConfig;

@SpringBootApplication
@EnableKafka
@EnableScheduling
@PropertySource({
        "classpath:kafka.properties"
})
//@Import(value = KafkaProducerConfig.class)
public class KafkaTesterApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaTesterApplication.class, args);
    }

}

