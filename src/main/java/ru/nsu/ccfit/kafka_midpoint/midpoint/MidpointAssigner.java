package ru.nsu.ccfit.kafka_midpoint.midpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.AssignmentRequestDTO;
import ru.nsu.ccfit.kafka_midpoint.midpoint.exceptions.MidpointException;
import ru.nsu.ccfit.kafka_midpoint.midpoint.exceptions.ObjectNotFoundException;
import ru.nsu.ccfit.kafka_midpoint.midpoint.factory.creator.ProductCreatorException;

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

    private int assign(String modType, String targetType,
                       String targetOid) throws IOException {
        AssignmentRequestDTO.AssignmentContentDTO contentDTO =
                new AssignmentRequestDTO.AssignmentContentDTO(targetOid, "RoleType");
        AssignmentRequestDTO requestDTO = new AssignmentRequestDTO();
        requestDTO.setValue(contentDTO);
        requestDTO.setModificationType(modType);

        ObjectMapper mapper = new ObjectMapper();

        String jsonRequest = JSONUtils.wrapper("objectModification",
                JSONUtils.wrapper("itemDelta",
                        mapper.writeValueAsString(mapper.valueToTree(requestDTO))));
        return sendJsonRequest(jsonRequest);
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
        openConnection();

        try {
            user = new User(objectName);
        }
        catch(ObjectNotFoundException e) {
            return null;
        }
        try {
            return user.modifyAssignment(targetType, targetName, modificationType);
        }
        catch(Exception e) {
            logger.warning(e.getMessage());
            return null;
        }
    }
}
