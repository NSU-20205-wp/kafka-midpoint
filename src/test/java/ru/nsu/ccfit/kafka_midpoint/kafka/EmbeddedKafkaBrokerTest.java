package ru.nsu.ccfit.kafka_midpoint.kafka;

import org.junit.jupiter.api.Test;
import org.springframework.kafka.test.EmbeddedKafkaBroker;

import static org.assertj.core.api.Assertions.assertThat;

class EmbeddedKafkaBrokerTest {

    @Test
    void testUpDown() {
        EmbeddedKafkaBroker kafkaBroker = new EmbeddedKafkaBroker(1);
        kafkaBroker.afterPropertiesSet();
        assertThat(kafkaBroker.getZookeeperConnectionString().startsWith("127"));
        kafkaBroker.destroy();
        assertThat(kafkaBroker.getZookeeperConnectionString() == null);
    }
}
