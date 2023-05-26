package ru.nsu.ccfit.kafka_midpoint.midpoint.deleters;

import ru.nsu.ccfit.kafka_midpoint.midpoint.MidpointDeleter;
import ru.nsu.ccfit.kafka_midpoint.midpoint.OidFinder;
import ru.nsu.ccfit.kafka_midpoint.midpoint.exceptions.ObjectNotFoundException;

import java.io.IOException;

public class RoleDeleter extends MidpointDeleter {
    public RoleDeleter(String name) throws IOException, ObjectNotFoundException {
        super("role", OidFinder.findRoleOid("name", name));
    }
}
