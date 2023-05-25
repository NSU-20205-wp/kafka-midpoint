package ru.nsu.ccfit.kafka_midpoint.midpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.ItemDeltaDTO;
import ru.nsu.ccfit.kafka_midpoint.processing.factory.creator.ProductCreatorException;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

public class MidpointModifier extends BaseMidpointCommunicator {
    private static final Logger logger = Logger.getLogger(MidpointModifier.class.getName());

    public MidpointModifier(String typeObject) {
        super();
        this.typeObject = typeObject;
        operationType = "POST";
        endpoint = baseUrl + '/' + typeObject + "s/";
        logger.info(() -> typeObject + ":\n base url: " + baseUrl + "\n endpoint: " + endpoint);
    }

    public int updateField(String nameField, Object newValue, ModificationType modificationType) throws IOException {
        // add delete replace
        ItemDeltaDTO itemDeltaDTO = new ItemDeltaDTO(modificationType, nameField, newValue);
        ObjectMapper mapper = new ObjectMapper();
        String jsonRequest = JSONUtils.wrapper("objectModification",
                JSONUtils.wrapper("itemDelta", mapper.writeValueAsString(mapper.valueToTree(itemDeltaDTO))));
        return sendJsonRequest(jsonRequest);
    }

    @Override
    public Object doOperation(Map<String, Object> params) throws IOException, ProductCreatorException {
        String fieldName = (String) params.get("fieldName");
        Object value = params.get("value");
        String modType = (String) params.get("modType");
        ModificationType modificationType = null;
        for (ModificationType type : ModificationType.values()) {
            if (type.getName().equals(modType)) {
                modificationType = type;
                break;
            }
        }
        if (modificationType == null) {
            return null;
        }

        logger.info(() -> "modifier typeObject: " + typeObject);
        String oid = OidFinder.findOid(typeObject, "name", (String) params.get("name"));
        logger.info(() -> "found oid: " + oid);
        if (oid == null) {
            return null;
        }
        endpoint = endpoint.concat(oid);
        openConnection();
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        return updateField(fieldName, value, modificationType);
    }
}
