package ru.nsu.ccfit.kafka_midpoint.processing.factory.creator;

import java.util.Map;

public interface ProductCreator {
    Object createProduct(Map<String, Class<?>> productList, String productName, String[] args)
            throws ProductCreatorException;
}
