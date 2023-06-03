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
            String preHost = properties.getProperty("midpoint.host", null);
            midpointHost = extract(preHost);
            String prePort = properties.getProperty("midpoint.port", null);
            midpointPort = Integer.valueOf(extract(prePort));
        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String extract(String raw) {
        raw = raw.replaceAll("[{}$\\s]", "");
        String[] vars = raw.split(":");
        String def = System.getenv(vars[0]);
        if(def == null) {
            return vars[1];
        }
        else {
            return def;
        }
    }

    public Integer getMidpointPort() {
        return midpointPort;
    }

    public String getMidpointHost() {
        return midpointHost;
    }
}
