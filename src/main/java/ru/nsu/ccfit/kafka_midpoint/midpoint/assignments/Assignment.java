package ru.nsu.ccfit.kafka_midpoint.midpoint.assignments;

import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;

public interface Assignment {
    ObjectNode buildValue(String name) throws IOException;
}
