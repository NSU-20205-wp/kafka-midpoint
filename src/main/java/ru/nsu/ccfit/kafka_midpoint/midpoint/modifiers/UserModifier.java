package ru.nsu.ccfit.kafka_midpoint.midpoint.modifiers;

import ru.nsu.ccfit.kafka_midpoint.midpoint.MidpointModifier;
import ru.nsu.ccfit.kafka_midpoint.midpoint.OidFinder;
import ru.nsu.ccfit.kafka_midpoint.midpoint.exceptions.ObjectNotFoundException;

import java.io.IOException;

public class UserModifier extends MidpointModifier {

    public UserModifier(String nameUser) throws IOException, ObjectNotFoundException {
        super("user");
        String oid = OidFinder.findOid("user", "name", nameUser);
        if (oid == null) {
            throw new ObjectNotFoundException("user with name: " + nameUser + " not found");
        }
        endpoint = endpoint.concat(oid);
        openConnection();
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
    }

    public UserModifier() {
        super("user");
    }
}
