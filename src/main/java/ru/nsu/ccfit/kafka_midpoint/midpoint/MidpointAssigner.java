package ru.nsu.ccfit.kafka_midpoint.midpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.AssignmentRequestDTO;
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
    public final Object doOperation(Map<String, Object> params) throws IOException, ProductCreatorException {
        String subjectName = (String) params.get("name");
        String targetType = (String) params.get("targetType");
        String targetName = (String) params.get("targetName");
        String modType = (String) params.get("modType");

        logger.info(() -> "assignment typeObject: " + typeObject);
        String objectOid = OidFinder.findOid(typeObject, "name", subjectName);
        logger.info(() -> "found oid: " + objectOid);
        if (objectOid == null) {
            return null;
        }
        logger.info(() -> "assignment targetObject: " + typeObject);
        String targetOid = OidFinder.findOid(targetType, "name", targetName);
        logger.info(() -> "found oid: " + targetOid);
        if (targetOid == null) {
            return null;
        }
        endpoint = endpoint.concat(objectOid);
        openConnection();
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        return assign(modType.toUpperCase(), targetType, targetOid);
    }
}
