package ru.nsu.ccfit.kafka_midpoint.midpoint_DTO;

import java.io.IOException;

public class UserCreator extends MidpointCreator{
    public UserCreator() throws IOException {
        super("user");
    }
}
