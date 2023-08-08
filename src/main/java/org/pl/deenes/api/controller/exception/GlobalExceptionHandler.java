package org.pl.deenes.api.controller.exception;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {



    private static final String VIEW_NAME_ERROR = "error";
    private static final String ATTRIBUTE_NAME = "errorA";
    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleNoResourceFound(ChangeSetPersister.@NonNull NotFoundException ex) {
        String message = String.format("Could not find a resource: [%s]", ex.getMessage());
        log.error(message, ex);
        ModelAndView modelView = new ModelAndView(VIEW_NAME_ERROR);
        modelView.addObject(ATTRIBUTE_NAME, message);
        return modelView;
    }

    @ExceptionHandler(NotFound.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleException(@NonNull NotFound ex) {
        String message = String.format("Processing exception occurred: [%s]", ex.getMessage());
        log.error(message, ex);
        ModelAndView modelView = new ModelAndView(VIEW_NAME_ERROR);
        modelView.addObject(ATTRIBUTE_NAME, message);
        return modelView;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(@NonNull Exception ex) {
        String message = String.format("Other exception occurred: [%s]", ex.getMessage());
        log.error(message, ex);
        ModelAndView modelView = new ModelAndView(VIEW_NAME_ERROR);
        modelView.addObject(ATTRIBUTE_NAME, message);
        return modelView;
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView handleException(@NonNull BindException ex) {
        String message = String.format("Bad request for field: [%s], wrong value: [%s]",
                Optional.ofNullable(ex.getFieldError()).map(FieldError::getField).orElse(null),
                Optional.ofNullable(ex.getFieldError()).map(FieldError::getRejectedValue).orElse(null));
        log.error(message, ex);
        ModelAndView modelView = new ModelAndView(VIEW_NAME_ERROR);
        modelView.addObject(ATTRIBUTE_NAME, message);
        return modelView;
    }


}
