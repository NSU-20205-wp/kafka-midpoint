package ru.nsu.ccfit.kafka_midpoint.midpoint.creators;

import ru.nsu.ccfit.kafka_midpoint.midpoint.MidpointCreator;

import java.io.IOException;

public class UserCreator extends MidpointCreator {
    public UserCreator() throws IOException {
        super("user");
    }
}
