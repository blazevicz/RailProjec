package org.pl.deenes.api.controller.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotFound extends RuntimeException {
    public NotFound(String message) {
        super(message);
    }
}
