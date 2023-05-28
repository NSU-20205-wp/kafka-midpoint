package ru.nsu.ccfit.kafka_midpoint.midpoint;

import ru.nsu.ccfit.kafka_midpoint.midpoint.exceptions.MidpointException;
import ru.nsu.ccfit.kafka_midpoint.midpoint.exceptions.ObjectNotFoundException;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

public class MidpointAssigner extends BaseMidpointCommunicator {
    private static final Logger logger = Logger.getLogger(MidpointAssigner.class.getCanonicalName());

    public MidpointAssigner(String typeObject) {
        super();
        this.typeObject = typeObject;
        operationType = "POST";
        endpoint = baseUrl + '/' + typeObject + "s/";
        logger.info(() -> typeObject + ":\n base url: " + baseUrl + "\n endpoint: " + endpoint);
    }

    @Override
    public final Object doOperation(Map<String, Object> params) throws IOException {
        String objectName = (String) params.get("name");
        String targetType = (String) params.get("targetType");
        String targetName = (String) params.get("targetName");
        String modType = (String) params.get("modType");
        ModificationType modificationType;
        try {
            modificationType = ModificationType.valueOf(modType.toUpperCase());
        }
        catch(IllegalArgumentException e) {
            logger.warning(() -> String.format("option '%s' not found", modType));
            return null;
        }

        User user;
        try {
            user = new User(objectName);
        }
        catch(ObjectNotFoundException e) {
            return null;
        }
        try {
            return user.modifyAssignment(targetType, targetName, modificationType);
        }
        catch(MidpointException e) {
            logger.warning(e.getMessage());
            return null;
        }
    }
}
