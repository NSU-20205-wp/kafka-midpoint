package ru.nsu.ccfit.kafka_midpoint.midpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.nsu.ccfit.kafka_midpoint.midpoint.assignments.Assignment;
import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.ItemDeltaDTO;
import ru.nsu.ccfit.kafka_midpoint.midpoint.factory.AbstractFactory;
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

    private int updateField(Object assignments,
                           ModificationType modificationType) throws IOException {
        ItemDeltaDTO itemDeltaDTO = new ItemDeltaDTO(modificationType, "assignment", assignments);
        ObjectMapper mapper = new ObjectMapper();
        String jsonRequest = JSONUtils.wrapper("objectModification",
                JSONUtils.wrapper("itemDelta", mapper.writeValueAsString(mapper.valueToTree(itemDeltaDTO))));
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

        logger.info(() -> "assigner typeObject: " + typeObject);
        String oid = OidFinder.findOid(typeObject, "name", objectName);
        logger.info(() -> "found oid: " + oid);
        if (oid == null) {
            return null;
        }
        endpoint = endpoint.concat(oid);
        openConnection();
        connection.setRequestProperty("Content-Type", "application/json; utf-8");

        try {
            Assignment builder = (Assignment) AbstractFactory.instance().getFactory("build_assignment")
                        .createProduct(targetType, null);
            return updateField(builder.buildValue(targetName), modificationType);
        }
        catch(ProductCreatorException e) {
            logger.warning(e.getMessage());
            return null;
        }
    }
}
