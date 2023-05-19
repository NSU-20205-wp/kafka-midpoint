package ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.factory;

public class DTOFactoryLoaderException extends Exception {
    public DTOFactoryLoaderException() {
        super();
    }

    public DTOFactoryLoaderException(String message) {
        super(message);
    }

    public DTOFactoryLoaderException(Throwable cause) {
        super(cause);
    }
}
