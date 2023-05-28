package ru.nsu.ccfit.kafka_midpoint.midpoint.exceptions;

public class CategoryNotFoundException extends MidpointException {
    public CategoryNotFoundException(String message) {
        super(message);
    }

    public CategoryNotFoundException(Throwable cause) {
        super(cause);
    }
}
