package ru.nsu.ccfit.kafka_midpoint.midpoint.deliters;

import ru.nsu.ccfit.kafka_midpoint.MidpointDeliter;
import ru.nsu.ccfit.kafka_midpoint.midpoint.OidFinder;
import ru.nsu.ccfit.kafka_midpoint.midpoint.exceptions.ObjectNotFoundException;

import java.io.IOException;

public class RoleDeliter extends MidpointDeliter {
    public RoleDeliter(String name) throws IOException, ObjectNotFoundException {
        super("role", OidFinder.findRoleOid("name", name));
    }
}
