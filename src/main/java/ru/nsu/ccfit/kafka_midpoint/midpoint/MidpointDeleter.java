package ru.nsu.ccfit.kafka_midpoint.midpoint;

import ru.nsu.ccfit.kafka_midpoint.midpoint.factory.creator.ProductCreatorException;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

public class MidpointDeleter extends BaseMidpointCommunicator {

    private static final Logger logger = Logger.getLogger(MidpointDeleter.class.getName());

    public MidpointDeleter(String typeObject) {
        super();
        this.typeObject = typeObject;
        operationType = "DELETE";
        endpoint = baseUrl + '/' + typeObject + "s/";
        logger.info(() -> typeObject + ":\n base url: " + baseUrl + "\n endpoint: " + endpoint);

    }

    private int delete() throws IOException {
        connection.connect();
        return connection.getResponseCode();
    }

    @Override
    public Object doOperation(Map<String, Object> params) throws IOException, ProductCreatorException {
        logger.info(() -> "deleter typeObject: " + typeObject);
        String oid = OidFinder.findOid(typeObject, "name", (String) params.get("name"));
        logger.info(() -> "found oid: " + oid);
        if (oid == null) {
            return null;
        }
        endpoint = endpoint.concat(oid);
        openConnection();
        return delete();
    }
}
