package ru.nsu.ccfit.kafka_midpoint.processing.factory.creator;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class SimpleProductCreator implements ProductCreator {
    private Map<String, Class<?>> productList;

    @Override
    public Object createProduct(String productName, String[] args)
            throws ProductCreatorException {
        if (!productList.containsKey(productName)) {
            return null;
        }
        try {
            return productList.get(productName).getDeclaredConstructor().newInstance((Object[]) args);
        }
        catch(NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new ProductCreatorException(e);
        }
    }

    @Override
    public void setProductList(Map<String, Class<?>> productList) {
        this.productList = productList;
    }
}
