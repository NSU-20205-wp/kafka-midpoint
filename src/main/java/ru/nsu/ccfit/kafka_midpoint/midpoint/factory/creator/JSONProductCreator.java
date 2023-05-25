package ru.nsu.ccfit.kafka_midpoint.midpoint.factory.creator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class JSONProductCreator implements ProductCreator {
    private Map<String, Class<?>> productList;

    @Override
    public Object createProduct(String productName,
                                String[] args) throws ProductCreatorException {
        if(!productList.containsKey(productName)) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(args[0], productList.get(productName));
        }
        catch(JsonProcessingException e) {
            throw new ProductCreatorException(e);
        }
    }

    @Override
    public void setProductList(Map<String, Class<?>> productList) {
        this.productList = productList;
    }
}
