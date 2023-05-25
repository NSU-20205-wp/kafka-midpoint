package ru.nsu.ccfit.kafka_midpoint.midpoint;

import ru.nsu.ccfit.kafka_midpoint.processing.factory.creator.ProductCreatorException;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

public class MidpointDeleter extends BaseMidpointCommunicator {

    private static final Logger logger = Logger.getLogger(MidpointDeleter.class.getName());

    private int responseCode;

    public MidpointDeleter(String typeObject) throws IOException {
        super();
        this.typeObject = typeObject;
        operationType = "DELETE";
        endpoint = baseUrl + '/' + typeObject + "s/";
        logger.info(() -> typeObject + ":\n base url: " + baseUrl + "\n endpoint: " + endpoint);
        //openConnection();

    }

    private int delete() throws IOException {
        connection.connect();
        responseCode = connection.getResponseCode();
        return responseCode;
    }

    public int getResponseCode() {
        return responseCode;
    }

    // TODO: do something with switch

    @Override
    public Object doOperation(Map<String, Object> params) throws IOException, ProductCreatorException {
        String oid;
        logger.info(() -> "deleter typeObject: " + typeObject);
        switch (typeObject) {
            case("role") -> oid = OidFinder.findRoleOid("name", (String) params.get("name"));
            case("user") -> oid = OidFinder.findUserOid("name", (String) params.get("name"));
            case("resource") -> oid = OidFinder.findResourceOid("name", (String) params.get("name"));
            default -> oid = null;
        }
        logger.info(() -> "found oid: " + oid);
        if (oid == null) {
            return null;
        }
        endpoint = endpoint.concat(oid);
        openConnection();
        return delete();
    }
}
