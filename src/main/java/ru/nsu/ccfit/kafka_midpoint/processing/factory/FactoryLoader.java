package ru.nsu.ccfit.kafka_midpoint.processing.factory;

import ru.nsu.ccfit.kafka_midpoint.processing.factory.creator.ProductCreator;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class FactoryLoader implements AutoCloseable {
    private static final Logger logger = Logger.getLogger(FactoryLoader.class.getCanonicalName());
    private final InputStream resource;

    public FactoryLoader(String resourceName) {
        resource = FactoryLoader.class.getClassLoader().getResourceAsStream(resourceName);
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

    public Factory load() throws IOException, FactoryLoaderException {
        String creatorClassName = readLine();
        ProductCreator creator;
        try {
            creator = (ProductCreator) Class.forName(creatorClassName).getDeclaredConstructor().newInstance();
        }
        catch (ClassNotFoundException e) {
            throw new FactoryLoaderException("invalid creator class name in factory config", e);
        }
        catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            throw new FactoryLoaderException("couldn't instantiate ProductCreator class", e);
        }
        catch (ClassCastException e) {
            throw new FactoryLoaderException(
                    String.format("class '%s' does not implement interface %s", creatorClassName, ProductCreator.class.getCanonicalName()), e);
        }

        String basicClassName = readLine();
        Class<?> basicClass;
        try {
            basicClass = Class.forName(basicClassName);
        }
        catch(ClassNotFoundException e) {
            throw new FactoryLoaderException("invalid basic class name in factory config", e);
        }

        Map<String, Class<?>> productions = new HashMap<>();
        int lineNumber = 3;
        for(String line = readLine(); line != null; line = readLine()) {
            String[] params = line.split("\\s");
            if(params.length != 2) {
                int currentLineNumber = lineNumber;
                logger.severe(() -> String.format("line %s: invalid params count", currentLineNumber));
            }
            else {
                try {
                    productions.put(params[0], Class.forName(params[1]));
                }
                catch(ClassNotFoundException e) {
                    int currentLineNumber = lineNumber;
                    logger.severe(() -> String.format("line %s: class '%s' not found", currentLineNumber, params[1]));
                }
            }
            lineNumber++;
        }

        if(productions.size() == 0) {
            throw new FactoryLoaderException("production list not set");
        }

        return new Factory(basicClass, productions, creator);
    }

    @Override
    public void close() throws IOException {
        resource.close();
    }
}
