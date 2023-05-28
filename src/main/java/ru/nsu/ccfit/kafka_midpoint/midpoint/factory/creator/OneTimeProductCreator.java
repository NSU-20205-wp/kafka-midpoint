package ru.nsu.ccfit.kafka_midpoint.midpoint.factory.creator;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.logging.Logger;

public class OneTimeProductCreator implements ProductCreator {
    private static final Logger logger = Logger.getLogger(OneTimeProductCreator.class.getCanonicalName());
    private Map<String, Object> products;


    @Override
    public Object createProduct(String productName, String[] args) {
        return products.get(productName);
    }

    @Override
    public void setProductList(Map<String, Class<?>> productList) {
        for(Map.Entry<String, Class<?>> entry: productList.entrySet()) {
            try {
                products.put(entry.getKey(), entry.getValue().getDeclaredConstructor().newInstance());
            }
            catch(NoSuchMethodException | InstantiationException | IllegalAccessException |
                  InvocationTargetException e) {
                logger.severe(() -> String.format("couldn't create an instantiation of class '%s': %s",
                        entry.getValue().getCanonicalName(), e.getMessage()));
            }
        }
    }
}
