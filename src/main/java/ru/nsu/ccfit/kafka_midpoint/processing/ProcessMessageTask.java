package ru.nsu.ccfit.kafka_midpoint.processing;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.nsu.ccfit.kafka_midpoint.midpoint.MidpointCreator;
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
            MidpointDTO dto =
                    (MidpointDTO) abstractFactory.getFactory("dto").createProduct(what, new String[]{mapper.writeValueAsString(params.get("params"))});

            String operation = (String) params.get("operation");
            logger.info(() -> "requested to perform " + operation + " on " + what);
            if (operation.equals("create")) {
                MidpointCreator creator = (MidpointCreator) abstractFactory.getFactory("create").createProduct(what, null);
                int responseCode = creator.sendRequest(dto);

                logger.info(() -> "Response code: " + responseCode);
                HashMap<String, Object> res = new HashMap<>();
                res.put("responseCode", responseCode);
                res.put("requestId", params.get("requestId"));
                return mapper.writeValueAsString(res);
            }
            else {
                throw new RuntimeException("operation <" + operation + "> not supported");
            }
        }
        catch (ProductCreatorException | IOException e) {
            throw new RuntimeException(e);
        }




    }
}
