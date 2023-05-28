package ru.nsu.ccfit.kafka_midpoint.midpoint.assigners;

import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;

public interface Assignable {
    ObjectNode buildValue(String name) throws IOException;
}
