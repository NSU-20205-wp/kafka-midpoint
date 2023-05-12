package ru.nsu.ccfit.kafka_midpoint.midpoint;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

public class MidpointCreator extends BaseMidpointCommunicator {
    private static final Logger logger = Logger.getLogger(MidpointCreator.class.getName());

    public MidpointCreator(String typeObject) throws IOException {
        super();
        this.typeObject = typeObject;
        operationType = "POST";
        endpoint = baseUrl + '/' + typeObject + 's';
        logger.info(() -> typeObject + ":\n base url: " + baseUrl + "\n endpoint: " + endpoint);
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
