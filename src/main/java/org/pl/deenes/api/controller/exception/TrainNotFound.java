package org.pl.deenes.api.controller.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TrainNotFound extends RuntimeException {
    public TrainNotFound(String message) {
        super(message);
    }
}
