package ru.nsu.ccfit.kafka_midpoint.midpoint.deleters;

import ru.nsu.ccfit.kafka_midpoint.midpoint.MidpointDeleter;
import ru.nsu.ccfit.kafka_midpoint.midpoint.OidFinder;
import ru.nsu.ccfit.kafka_midpoint.midpoint.exceptions.ObjectNotFoundException;
import ru.nsu.ccfit.kafka_midpoint.processing.factory.creator.ProductCreatorException;

import java.io.IOException;
import java.util.Map;

public class RoleDeleter extends MidpointDeleter {
    public RoleDeleter() throws IOException, ObjectNotFoundException {
        super("role");
    }
}
