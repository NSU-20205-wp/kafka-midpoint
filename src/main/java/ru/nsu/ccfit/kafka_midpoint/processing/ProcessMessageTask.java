package ru.nsu.ccfit.kafka_midpoint.processing;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.nsu.ccfit.kafka_midpoint.midpoint.BaseMidpointCommunicator;
import ru.nsu.ccfit.kafka_midpoint.midpoint.factory.AbstractFactory;
import ru.nsu.ccfit.kafka_midpoint.midpoint.factory.creator.ProductCreatorException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.logging.Logger;

public class ProcessMessageTask implements Callable<String> {
    private static final Logger logger = Logger.getLogger(ProcessMessageTask.class.getName());

    private final String message;
    private final ObjectMapper mapper;

    public ProcessMessageTask(String msg) {
        message = msg;
        mapper = new ObjectMapper();
    }

    // TODO: custom exceptions

    @Override
    public String call() {
        try {
            Map<String, Object> params = mapper.readValue(message, new TypeReference<>(){});
            String what = (String) params.get("what");
            AbstractFactory abstractFactory = AbstractFactory.instance();

            String operation = (String) params.get("operation");
            logger.info(() -> "requested to perform " + operation + " on " + what);
            HashMap<String, Object> res = new HashMap<>();
            BaseMidpointCommunicator communicator =
                    (BaseMidpointCommunicator) abstractFactory.getFactory(operation).createProduct(what, null);
            Object operationRes = communicator.doOperation((Map<String, Object>) params.get("params"));
            int responseCode = communicator.getResponseCode();
            if (responseCode / 100 == 2) {
                res.put("info", operationRes);
            }
            res.putIfAbsent("info", null);
            logger.info(() -> "Response code: " + responseCode);
            res.put("responseCode", responseCode);
            res.put("requestId", params.get("requestId"));
            return mapper.writeValueAsString(res);
        }
        catch (ProductCreatorException | IOException e) {
            logger.warning(e::getMessage);
            throw new RuntimeException(e);
        }




    }
}
