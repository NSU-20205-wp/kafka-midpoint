package ru.nsu.ccfit.kafka_midpoint.midpoint;

public enum ModificationType {
    ADD("add"),
    DELETE("delete"),
    REPLACE("replace");

    final String name;

    ModificationType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

