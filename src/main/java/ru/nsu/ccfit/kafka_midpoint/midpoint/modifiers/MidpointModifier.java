package ru.nsu.ccfit.kafka_midpoint.midpoint.modifiers;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.nsu.ccfit.kafka_midpoint.midpoint.BaseMidpointCommunicator;
import ru.nsu.ccfit.kafka_midpoint.midpoint.JSONUtils;
import ru.nsu.ccfit.kafka_midpoint.midpoint.ModificationType;
import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.ItemDeltaDTO;

import java.io.IOException;

public class MidpointModifier extends BaseMidpointCommunicator {
    public MidpointModifier(String typeObject, String oid) throws IOException {
        super();
        this.typeObject = typeObject;
        operationType = "POST";
        endpoint = baseUrl + '/' + typeObject + "s/" + oid;
        openConnection();
        connection.setRequestProperty("Content-Type", "application/json; utf-8");

    }

    public int modifyField(String nameField, Object newValue) throws IOException {
        ItemDeltaDTO itemDeltaDTO = new ItemDeltaDTO(ModificationType.REPLACE, nameField, newValue);
        ObjectMapper mapper = new ObjectMapper();
        String jsonRequest = JSONUtils.wrapper("objectModification",
                JSONUtils.wrapper("itemDelta", mapper.writeValueAsString(mapper.valueToTree(itemDeltaDTO))));
        return sendJsonRequest(jsonRequest);
    }
}
