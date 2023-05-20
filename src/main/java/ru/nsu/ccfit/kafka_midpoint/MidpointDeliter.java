package ru.nsu.ccfit.kafka_midpoint;

import ru.nsu.ccfit.kafka_midpoint.midpoint.BaseMidpointCommunicator;

import java.io.IOException;
import java.util.logging.Logger;

public class MidpointDeliter extends BaseMidpointCommunicator {

    private static final Logger logger = Logger.getLogger(MidpointDeliter.class.getName());

    int responseCode;

    public MidpointDeliter(String typeObject, String oid) throws IOException {
        super();
        this.typeObject = typeObject;
        operationType = "DELETE";
        endpoint = baseUrl + '/' + typeObject + "s/" + oid;
        logger.info(() -> typeObject + ":\n base url: " + baseUrl + "\n endpoint: " + endpoint);
        openConnection();

    }

    public int delete() throws IOException {
        connection.connect();
        responseCode = connection.getResponseCode();
        return responseCode;
    }

    public int getResponseCode() {
        return responseCode;
    }
}
