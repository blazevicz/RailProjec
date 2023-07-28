package org.pl.deenes.api.controller.exception;

public class OperationNotSupported extends RuntimeException {

    public OperationNotSupported(String message) {
        super(message);
    }
}
