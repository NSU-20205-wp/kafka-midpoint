package ru.nsu.ccfit.kafka_midpoint.midpoint.deliters;

import ru.nsu.ccfit.kafka_midpoint.MidpointDeliter;
import ru.nsu.ccfit.kafka_midpoint.midpoint.OidFinder;
import ru.nsu.ccfit.kafka_midpoint.midpoint.exceptions.ObjectNotFoundException;

import java.io.IOException;

public class UserDeliter extends MidpointDeliter {
    public UserDeliter(String name) throws IOException, ObjectNotFoundException {
        super("user", OidFinder.findUserOid("name", name));
    }
}
