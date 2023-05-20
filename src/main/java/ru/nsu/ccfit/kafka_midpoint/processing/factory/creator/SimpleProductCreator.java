package ru.nsu.ccfit.kafka_midpoint.processing.factory.creator;

import ru.nsu.ccfit.kafka_midpoint.midpoint.MidpointCreator;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class SimpleProductCreator implements ProductCreator {
    @Override
    public Object createProduct(Map<String, Class<?>> productList, String productName, String[] args)
            throws ProductCreatorException {
        if (!productList.containsKey(productName)) {
            return null;
        }
        try {
            return productList.get(productName).getDeclaredConstructor().newInstance();
        }
        catch(NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new ProductCreatorException(e);
        }
    }
}
