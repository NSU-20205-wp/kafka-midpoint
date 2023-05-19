package ru.nsu.ccfit.kafka_midpoint;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import ru.nsu.ccfit.kafka_midpoint.kafka.KafkaTestConfig;

@SpringBootTest
@Import(value = {KafkaTestConfig.class})
class KafkaMidpointApplicationTests {

	@Test
	void contextLoads() {
	}

}
