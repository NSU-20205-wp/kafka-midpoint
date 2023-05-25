package ru.nsu.ccfit.kafka_midpoint.midpoint.factory.creator;

import java.util.Map;

public interface ProductCreator {
    Object createProduct(String productName, String[] args)
            throws ProductCreatorException;
    
    void setProductList(Map<String, Class<?>> productList);
}
