package ru.nsu.ccfit.kafka_midpoint.processing;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.nsu.ccfit.kafka_midpoint.midpoint.MidpointCreator;
import ru.nsu.ccfit.kafka_midpoint.midpoint.MidpointDeleter;
import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.MidpointDTO;
import ru.nsu.ccfit.kafka_midpoint.processing.factory.AbstractFactory;
import ru.nsu.ccfit.kafka_midpoint.processing.factory.creator.ProductCreatorException;

import java.io.IOException;
import java.util.HashMap;
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
            int responseCode;
            HashMap<String, Object> res = new HashMap<>();
            if (operation.equals("create")) {
                MidpointDTO dto =
                        (MidpointDTO) abstractFactory.getFactory("dto").createProduct(
                                what, new String[]{mapper.writeValueAsString(params.get("params"))});
                MidpointCreator creator =
                        (MidpointCreator) abstractFactory.getFactory(operation).createProduct(what, null);
                responseCode = creator.sendRequest(dto);
                if (responseCode / 100 == 2) {
                    res.put("info", what + " created");
                }
                res.putIfAbsent("info", null);
            }
            else if (operation.equals("delete")) {
                MidpointDTO dto =
                        (MidpointDTO) abstractFactory.getFactory("dto").createProduct(
                                what, new String[]{mapper.writeValueAsString(params.get("params"))});
                MidpointDeleter deleter =
                        (MidpointDeleter) abstractFactory.getFactory(operation).createProduct(
                                what, new String[]{dto.getName()});
                responseCode = deleter.delete();
                if (responseCode / 100 == 2) {
                    res.put("info", what + " deleted");
                }
                res.putIfAbsent("info", null);
            }
            else {
                responseCode = 400;
                res.put("info", "operation <" + operation + "> not supported");
            }
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
