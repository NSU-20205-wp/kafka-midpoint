package ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.MidpointDTO;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class DTOFactory {
    private static final Logger factoryLogger = Logger.getLogger(DTOFactory.class.getCanonicalName());
    private static DTOFactory instance;

    private final Map<String, Class<? extends MidpointDTO>> dtos = new HashMap<>();

    private final ObjectMapper mapper = new ObjectMapper();

    private DTOFactory() { }

    public static DTOFactory instance() throws IOException {
        if(instance == null) {
            instance = new DTOFactory();
            instance.load("dtos.conf");
        }
        return instance;
    }

    public void load(String configFileName) throws IOException {
        try(DTOFactoryLoader parser = new DTOFactoryLoader(configFileName)) {
            Pair<String, Class<?>> pair;
            while(true) {
                try {
                    pair = parser.nextEntry();
                    if(pair == null) {
                        break;
                    }
                    String name = pair.first;
                    Class<?> metaclass = pair.second;
                    if(!MidpointDTO.class.isAssignableFrom(metaclass)) {
                        throw new ClassCastException(
                                String.format("%s is not a subclass of %s", metaclass.getCanonicalName(),
                                        MidpointDTO.class.getCanonicalName())
                        );
                    }
                    dtos.put(name, (Class<? extends MidpointDTO>) metaclass);
                }
                catch(Exception e) {
                    factoryLogger.severe(() ->
                            String.format("%s: %s", e.getClass().getCanonicalName(), e.getMessage()));
                }

            }
        }
    }

    public MidpointDTO getDto(String dtoName, String params) throws
            JsonProcessingException {
        if(!dtos.containsKey(dtoName)) {
            return null;
        }
        return mapper.readValue(params, dtos.get(dtoName));
    }
}
