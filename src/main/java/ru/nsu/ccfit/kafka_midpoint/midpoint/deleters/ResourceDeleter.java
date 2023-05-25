package ru.nsu.ccfit.kafka_midpoint.midpoint.deleters;

import ru.nsu.ccfit.kafka_midpoint.midpoint.MidpointDeleter;
import ru.nsu.ccfit.kafka_midpoint.midpoint.OidFinder;
import ru.nsu.ccfit.kafka_midpoint.midpoint.exceptions.ObjectNotFoundException;

import java.io.IOException;

public class ResourceDeleter extends MidpointDeleter {
    public ResourceDeleter() throws IOException, ObjectNotFoundException {
        super("resource");
    }
}
