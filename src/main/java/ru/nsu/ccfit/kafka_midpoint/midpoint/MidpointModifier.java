package ru.nsu.ccfit.kafka_midpoint.midpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.ItemDeltaDTO;
import ru.nsu.ccfit.kafka_midpoint.processing.factory.creator.ProductCreatorException;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

public class MidpointModifier extends BaseMidpointCommunicator {
    private static final Logger logger = Logger.getLogger(MidpointModifier.class.getName());

    public MidpointModifier(String typeObject, String oid) throws IOException {
        super();
        this.typeObject = typeObject;
        operationType = "POST";
        endpoint = baseUrl + '/' + typeObject + "s/" + oid;
        logger.info(() -> typeObject + ":\n base url: " + baseUrl + "\n endpoint: " + endpoint);
        openConnection();
        connection.setRequestProperty("Content-Type", "application/json; utf-8");

    }

    public int modifyField(String nameField, Object newValue) throws IOException {
        // ЗАМЕНА сторого значения на новое
        ItemDeltaDTO itemDeltaDTO = new ItemDeltaDTO(ModificationType.REPLACE, nameField, newValue);
        ObjectMapper mapper = new ObjectMapper();
        String jsonRequest = JSONUtils.wrapper("objectModification",
                JSONUtils.wrapper("itemDelta", mapper.writeValueAsString(mapper.valueToTree(itemDeltaDTO))));
        return sendJsonRequest(jsonRequest);
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
        return null;
    }
}
