package ru.nsu.ccfit.kafka_midpoint.processing.factory;

public class FactoryLoaderException extends Exception {
    public FactoryLoaderException() {
        super();
    }

    public FactoryLoaderException(String message) {
        super(message);
    }

    public FactoryLoaderException(Throwable cause) {
        super(cause);
    }

    public FactoryLoaderException(String message, Throwable cause) {
        super(message, cause);
    }
}
