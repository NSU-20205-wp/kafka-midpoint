package ru.nsu.ccfit.kafka_midpoint.processing.factory;

import ru.nsu.ccfit.kafka_midpoint.processing.factory.creator.ProductCreator;

import java.util.HashMap;
import java.util.Map;

public class Factory {
    private final Class<?> basicClass;
    private final Map<String, Class<?>> producers = new HashMap<>();
    private final ProductCreator creator;

    public Factory(Class<?> basicClass, Map<String, Class<?>> producers, ProductCreator creator) {
        this.basicClass = basicClass;
        this.creator = creator;
        for(Map.Entry<String, Class<?>> entry: producers.entrySet()) {
            if(!basicClass.isAssignableFrom(entry.getValue())) {
                //todo add to log a class cast error
            }
            else {
                this.producers.put(entry.getKey(), entry.getValue());
            }
        }
    }

    public Object createProduct(String name, String[] args) throws Exception {
        return creator.createProduct(producers, name, args);
    }
}
