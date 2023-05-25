package ru.nsu.ccfit.kafka_midpoint.midpoint.factory;

import ru.nsu.ccfit.kafka_midpoint.midpoint.factory.creator.ProductCreator;
import ru.nsu.ccfit.kafka_midpoint.midpoint.factory.creator.ProductCreatorException;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Factory {
    private static final Logger logger = Logger.getLogger(Factory.class.getCanonicalName());
    private final ProductCreator creator;

    public Factory(Class<?> basicClass, Map<String, Class<?>> producers, ProductCreator creator) {
        this.creator = creator;
        Map<String, Class<?>> productList = new HashMap<>();
        for(Map.Entry<String, Class<?>> entry: producers.entrySet()) {
            if(!basicClass.isAssignableFrom(entry.getValue())) {
                logger.severe(() -> String.format("'%s' is not a subclass of '%s'",
                        entry.getValue(), basicClass.getCanonicalName()));
            }
            else {
                productList.put(entry.getKey(), entry.getValue());
            }
        }
        creator.setProductList(productList);
    }

    public Object createProduct(String name, String[] args) throws ProductCreatorException {
        return creator.createProduct(name, args);
    }
}
