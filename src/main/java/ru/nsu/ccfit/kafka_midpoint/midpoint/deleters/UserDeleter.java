package ru.nsu.ccfit.kafka_midpoint.midpoint.deleters;

import ru.nsu.ccfit.kafka_midpoint.midpoint.MidpointDeleter;

public class UserDeleter extends MidpointDeleter {
    public UserDeleter() {
        super("user");
    }
}
