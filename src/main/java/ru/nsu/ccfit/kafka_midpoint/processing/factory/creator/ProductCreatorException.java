package ru.nsu.ccfit.kafka_midpoint.processing.factory.creator;

public class ProductCreatorException extends Exception {
    public ProductCreatorException() {
        super();
    }

    public ProductCreatorException(String message) {
        super(message);
    }

    public ProductCreatorException(Throwable cause) {
        super(cause);
    }

    public ProductCreatorException(String message, Throwable cause) {
        super(message, cause);
    }
}
