package ru.nsu.ccfit.kafka_midpoint.processing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.nsu.ccfit.kafka_midpoint.midpoint.MidpointCreator;
import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.MidpointDTO;
import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.factory.DTOFactory;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
            DTOFactory factory = DTOFactory.instance();
            factory.load("dtos.conf");
            MidpointDTO dto = factory.getDto(what, mapper.writeValueAsString(params.get("params")));

            String operation = (String) params.get("operation");
            logger.info(() -> "requested to perform " + operation + " on " + what);
            if (operation.equals("create")) {
                MidpointCreator creator = CreatorsFactory.instance().getCreator(dto.getClass().getName());
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
        catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException | IOException e) {
            throw new RuntimeException(e);
        }




    }
}
