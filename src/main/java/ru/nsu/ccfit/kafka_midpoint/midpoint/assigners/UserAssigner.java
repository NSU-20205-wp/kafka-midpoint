package ru.nsu.ccfit.kafka_midpoint.midpoint.assigners;

import ru.nsu.ccfit.kafka_midpoint.midpoint.MidpointAssigner;
import ru.nsu.ccfit.kafka_midpoint.midpoint.OidFinder;
import ru.nsu.ccfit.kafka_midpoint.midpoint.exceptions.ObjectNotFoundException;

import java.io.IOException;
import java.util.logging.Logger;

public class UserAssigner extends MidpointAssigner {
    private static final Logger logger = Logger.getLogger(UserAssigner.class.getCanonicalName());
    public UserAssigner(String nameUser) throws IOException, ObjectNotFoundException {
        super("user");
        String oid = OidFinder.findOid("user", "name", nameUser);
        if (oid == null) {
            throw new ObjectNotFoundException("user with name: " + nameUser + " not found");
        }
        endpoint = endpoint.concat(oid);
        openConnection();
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
    }

    public UserAssigner() {
        super("user");
    }
}
