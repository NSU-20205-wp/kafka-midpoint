package ru.nsu.ccfit.kafka_midpoint.processing.factory.creator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class JSONProductCreator implements ProductCreator {
    @Override
    public Object createProduct(Map<String, Class<?>> productList, String productName,
                                String[] args) throws JsonProcessingException {
        if(!productList.containsKey(productName)) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(args[0], productList.get(productName));
    }
}
