package ru.nsu.ccfit.kafka_midpoint.midpoint.creators;

import ru.nsu.ccfit.kafka_midpoint.midpoint.MidpointCreator;

import java.io.IOException;
public class RoleCreator extends MidpointCreator {

    public RoleCreator() throws IOException {
        super("role");
    }
}
