package ru.nsu.ccfit.kafka_midpoint.processing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.MidpointDTO;
import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.RoleDTO;
import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.UserDTO;

import java.util.HashMap;

public class DTOFactory {

    private final HashMap<String, String> dtoHashMap;

    private static DTOFactory dtoFactory = null;
    private final ObjectMapper mapper;

    private DTOFactory() {
        mapper = new ObjectMapper();
        dtoHashMap = new HashMap<>();

        // TODO: normal factory
        dtoHashMap.put("user", UserDTO.class.getName());
        dtoHashMap.put("role", RoleDTO.class.getName());
    }

    public static DTOFactory instance() {
        if (dtoFactory == null) {
            dtoFactory = new DTOFactory();
        }
        return dtoFactory;
    }

    public MidpointDTO getDto(String name, String params) throws ClassNotFoundException, JsonProcessingException {
        if (!dtoHashMap.containsKey(name)) {
            return null;
        }
        return (MidpointDTO) mapper.readValue(params, Class.forName(dtoHashMap.get(name)));
    }
}
