package ru.nsu.ccfit.kafka_midpoint.midpoint;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MidpointConfiguration {

    private final Integer midpointPort;
    private final String midpointHost;

    public MidpointConfiguration() {
        Properties properties = new Properties();
        try (InputStream inp = new FileInputStream("src/main/resources/application.properties")) {
            properties.load(inp);
            midpointHost = properties.getProperty("midpoint.host", "localhost");
            midpointPort = Integer.valueOf(properties.getProperty("midpoint.port"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer getMidpointPort() {
        return midpointPort;
    }

    public String getMidpointHost() {
        return midpointHost;
    }
}
