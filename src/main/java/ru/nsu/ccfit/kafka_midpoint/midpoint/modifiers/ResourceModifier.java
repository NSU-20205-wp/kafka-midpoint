package ru.nsu.ccfit.kafka_midpoint.midpoint.modifiers;

import ru.nsu.ccfit.kafka_midpoint.midpoint.MidpointModifier;
import ru.nsu.ccfit.kafka_midpoint.midpoint.OidFinder;
import ru.nsu.ccfit.kafka_midpoint.midpoint.exceptions.ObjectNotFoundException;

import java.io.IOException;

public class ResourceModifier extends MidpointModifier {

    public ResourceModifier(String name) throws IOException, ObjectNotFoundException {
        super("resource", OidFinder.findResourceOid("name", name));
    }
}