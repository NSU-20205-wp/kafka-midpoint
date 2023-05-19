package ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.factory;

import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.MidpointDTO;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class DTOFactoryLoader implements AutoCloseable {
    private final InputStream resource;

    public DTOFactoryLoader(String resourceName) {
        resource = DTOFactory.class.getClassLoader().getResourceAsStream(resourceName);
    }

    private StringBuilder nextLine() throws IOException {
        StringBuilder builder = new StringBuilder();
        int currentChar = resource.read();
        if(currentChar < 0) {
            return null;
        }
        while(currentChar != '\n' && currentChar > 0) {
            builder.append((char) currentChar);
            currentChar = resource.read();
        }
        if(currentChar > 0) {
            builder.append((char) currentChar);
        }
        return builder;
    }

    public Pair<String, Class<?>> nextEntry()
            throws IOException, DTOFactoryLoaderException, ClassNotFoundException {
        StringBuilder line = nextLine();
        if(line == null)
            return null;
        String[] split = line.toString().split("\\s");
        if(split.length < 2) {
            throw new DTOFactoryLoaderException(
                    String.format("too few arguments for DTO: %s", split.length));
        }
        if(split.length > 2) {
            throw new DTOFactoryLoaderException(
                    String.format("too many arguments for DTO: %s", split.length));
        }
        Class<?> metaclass = Class.forName(split[1]);

        return new Pair<>(split[0], metaclass);
    }

    @Override
    public void close() throws IOException {
        resource.close();
    }
}
