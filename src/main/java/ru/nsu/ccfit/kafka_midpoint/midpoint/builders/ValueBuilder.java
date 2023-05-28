package ru.nsu.ccfit.kafka_midpoint.midpoint.builders;

import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;

public interface ValueBuilder {
    ObjectNode buildValue(String name) throws IOException;
}
