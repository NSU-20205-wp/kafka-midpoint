package ru.nsu.ccfit.kafka_midpoint.processing.factory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class AbstractFactory {
    private static final Logger logger = Logger.getLogger(AbstractFactory.class.getCanonicalName());
    private static AbstractFactory instance;
    private final Map<String, Factory> factories = new HashMap<>();

    private AbstractFactory() throws IOException {
        String path = "factory/";
        Map<String, String> factoryList;
        try(AbstractFactoryLoader abstractFactoryLoader = new AbstractFactoryLoader(path + "factory.conf")) {
            factoryList = abstractFactoryLoader.load();
        }
        for(Map.Entry<String, String> entry: factoryList.entrySet()) {
            Factory factory;
            String fullPath = path + entry.getValue();
            try(FactoryLoader factoryLoader = new FactoryLoader(fullPath)) {
                factory = factoryLoader.load();
            }
            catch(FactoryLoaderException e) {
                logger.severe(() ->
                        String.format("couldn't load factory by its config '%s': %s",
                                fullPath, e.getMessage()));
                continue;
            }
            factories.put(entry.getKey(), factory);
        }
    }

    public static AbstractFactory instance() throws IOException {
        if(instance == null) {
            instance = new AbstractFactory();
        }
        return instance;
    }

    public Factory getFactory(String factoryName) {
        return factories.get(factoryName);
    }
}
