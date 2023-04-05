package org.pl.deenes.configuration;


public class LoadingPdfException extends RuntimeException {


    public LoadingPdfException(String message) {
        super(message);
    }

    public LoadingPdfException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoadingPdfException(Throwable cause) {
        super(cause);
    }

}


