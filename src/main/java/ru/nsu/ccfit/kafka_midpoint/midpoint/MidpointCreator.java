package ru.nsu.ccfit.kafka_midpoint.midpoint;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MidpointCreator extends BaseMidpointCommunicator {

    public MidpointCreator(String typeObject) throws IOException {
        this.typeObject = typeObject;
        operationType = "POST";
        endpoint = baseUrl + '/' + typeObject + 's';
        openConnection();
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
    }

    public int sendRequest(Object dto) throws IOException {
        connection.connect();
        byte[] jsonBytes = JSONUtils.wrapper(new JSONObject(dto), typeObject).
                toString().getBytes(StandardCharsets.UTF_8);
        connection.getOutputStream().write(jsonBytes);

        return connection.getResponseCode();
    }
}
