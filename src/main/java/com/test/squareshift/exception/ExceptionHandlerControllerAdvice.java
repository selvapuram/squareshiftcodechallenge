package com.test.squareshift.exception;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

//@RestControllerAdvice
public class ExceptionHandlerControllerAdvice {
    private static final String ERR_GENERIC_EXCEPTION = "9999";
    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(SeatCreationFailedException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ErrorModel handleResourceNotFound(final SeatCreationFailedException exception,
      final HttpServletRequest request) {

      ErrorModel error = ErrorModel.of(exception.getCode(),
        messageSource.getMessage(exception.getCode(), null, exception.getMessage(), Locale.ENGLISH));
      return error;
    }
    
    @ExceptionHandler(NoSuchStrategyException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public @ResponseBody ErrorModel handleResourceNotFound(final NoSuchStrategyException exception,
      final HttpServletRequest request) {

      ErrorModel error = ErrorModel.of(exception.getCode(),
        messageSource.getMessage(exception.getCode(), null, exception.getMessage(), Locale.ENGLISH));
      return error;
    }

    
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ErrorModel handleException(final Exception exception,
      final HttpServletRequest request) {
      ErrorModel error = ErrorModel.of(ERR_GENERIC_EXCEPTION, exception.toString());
      return error;
    }
}
