package ru.nsu.ccfit.kafka_midpoint.midpoint.creators;

import ru.nsu.ccfit.kafka_midpoint.midpoint.MidpointCreator;

import java.io.IOException;

public class ResourceCreator extends MidpointCreator {

    public ResourceCreator() throws IOException {
        super("resource");
    }
}
