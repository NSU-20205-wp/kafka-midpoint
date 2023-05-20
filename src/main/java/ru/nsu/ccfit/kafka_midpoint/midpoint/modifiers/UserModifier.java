package ru.nsu.ccfit.kafka_midpoint.midpoint.modifiers;

import ru.nsu.ccfit.kafka_midpoint.midpoint.OidFinder;
import ru.nsu.ccfit.kafka_midpoint.midpoint.exceptions.ObjectNotFoundException;

import java.io.IOException;

public class UserModifier extends MidpointModifier {

    public UserModifier(String name) throws IOException, ObjectNotFoundException {
        super("user", OidFinder.findUserOid("name", name));
    }
}
