package ru.nsu.ccfit.kafka_midpoint.midpoint.modifiers;

import ru.nsu.ccfit.kafka_midpoint.midpoint.MidpointModifier;
import ru.nsu.ccfit.kafka_midpoint.midpoint.OidFinder;
import ru.nsu.ccfit.kafka_midpoint.midpoint.exceptions.ObjectNotFoundException;

import java.io.IOException;

public class RoleModifier extends MidpointModifier {

    public RoleModifier(String name) throws IOException, ObjectNotFoundException {
        super("role", OidFinder.findRoleOid("name", name));
    }
}