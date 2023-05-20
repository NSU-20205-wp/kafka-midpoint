package ru.nsu.ccfit.kafka_midpoint.processing.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class AbstractFactoryLoader implements AutoCloseable {
    private static final Logger logger = Logger.getLogger(AbstractFactoryLoader.class.getCanonicalName());
    private final InputStream resource;
    private final String resourceName;

    public AbstractFactoryLoader(String abstractFactoryConfigFileName) {
        resource = FactoryLoader.class.getClassLoader().getResourceAsStream(abstractFactoryConfigFileName);
        resourceName = abstractFactoryConfigFileName;
    }

    private String readLine() throws IOException {
        StringBuilder builder = new StringBuilder();
        for(;;) {
            int currentChar = resource.read();
            if(currentChar <= 0 || currentChar == '\n') {
                break;
            }
            builder.append((char) currentChar);
        }
        if(resource.available() == 0 && builder.isEmpty()) {
            return null;
        }
        return builder.toString();
    }

    public Map<String, String> readConfig() throws IOException {
        Map<String, String> factoryFiles = new HashMap<>();
        int lineNumber = 1;
        for(String line = readLine(); line != null; lineNumber++, line = readLine()) {
            if(line.length() == 0) {
                continue;
            }
            String[] values = line.split("\\s");
            if(values.length != 2) {
                int currentLineNumber = lineNumber;
                logger.severe(() -> String.format("resource '%s': line %d: invalid parameter count", resourceName, currentLineNumber));
            }
            else {
                factoryFiles.put(values[0], values[1]);
            }
        }

        return factoryFiles;
    }

    @Override
    public void close() throws IOException {
        resource.close();
    }

}
