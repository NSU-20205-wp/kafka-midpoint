package ru.nsu.ccfit.kafka_midpoint.processing.factory;

import ru.nsu.ccfit.kafka_midpoint.processing.factory.creator.ProductCreator;
import ru.nsu.ccfit.kafka_midpoint.processing.factory.creator.ProductCreatorException;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Factory {
    private static final Logger logger = Logger.getLogger(Factory.class.getCanonicalName());
    private final Class<?> basicClass;
    private final Map<String, Class<?>> producers = new HashMap<>();
    private final ProductCreator creator;

    public Factory(Class<?> basicClass, Map<String, Class<?>> producers, ProductCreator creator) {
        this.basicClass = basicClass;
        this.creator = creator;
        for(Map.Entry<String, Class<?>> entry: producers.entrySet()) {
            if(!basicClass.isAssignableFrom(entry.getValue())) {
                logger.severe(() -> String.format("'%s' is not a subclass of '%s'",
                        entry.getValue(), basicClass.getCanonicalName()));
            }
            else {
                this.producers.put(entry.getKey(), entry.getValue());
            }
        }
    }

    public Object createProduct(String name, String[] args) throws ProductCreatorException {
        return creator.createProduct(producers, name, args);
    }
}
