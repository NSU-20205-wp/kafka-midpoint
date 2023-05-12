package ru.nsu.ccfit.kafka_midpoint.processing;

import ru.nsu.ccfit.kafka_midpoint.midpoint.MidpointCreator;
import ru.nsu.ccfit.kafka_midpoint.midpoint.creators.RoleCreator;
import ru.nsu.ccfit.kafka_midpoint.midpoint.creators.UserCreator;
import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.RoleDTO;
import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.UserDTO;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class CreatorsFactory {
    private final HashMap<String, String> creatorsHashMap;

    private static CreatorsFactory creatorsFactory = null;

    private CreatorsFactory() {
        creatorsHashMap = new HashMap<>();

        // TODO: normal factory
        creatorsHashMap.put(UserDTO.class.getName(), UserCreator.class.getName());
        creatorsHashMap.put(RoleDTO.class.getName(), RoleCreator.class.getName());
    }

    public static CreatorsFactory instance() {
        if (creatorsFactory == null) {
            creatorsFactory = new CreatorsFactory();
        }
        return creatorsFactory;
    }

    public MidpointCreator getCreator(String dtoName) throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {
        if (!creatorsHashMap.containsKey(dtoName)) {
            return null;
        }
        return (MidpointCreator) Class.forName(creatorsHashMap.get(dtoName)).getDeclaredConstructor().newInstance();
    }
}
