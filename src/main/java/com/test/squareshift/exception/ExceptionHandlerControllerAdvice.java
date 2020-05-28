package com.test.squareshift.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionHandlerControllerAdvice extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(SeatCreationFailedException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ErrorModel handleCreationFailed(final SeatCreationFailedException exception,
            final HttpServletRequest request) {

        return ErrorModel.builder().code(exception.getCode())
                .message(messageSource.getMessage(exception.getCode(), null, exception.getMessage(), Locale.ENGLISH))
                .build();
    }

    @ExceptionHandler(NoSuchStrategyException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public @ResponseBody ErrorModel handleNoStrategyFound(final NoSuchStrategyException exception,
            final HttpServletRequest request) {

        return ErrorModel.builder().code(exception.getCode())
                .message(messageSource.getMessage(exception.getCode(), null, exception.getMessage(), Locale.ENGLISH))
                .build();
    }

    @ExceptionHandler(SeatCapacityFullException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorModel handleBadArgumentException(final SeatCapacityFullException exception,
            final HttpServletRequest request) {
        return ErrorModel.builder().code(exception.getCode())
                .message(messageSource.getMessage(exception.getCode(), null, exception.getMessage(), Locale.ENGLISH))
                .build();
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> list = new ArrayList<>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            list.add(error.getDefaultMessage());
        }
        ErrorModel error = ErrorModel.builder().code(ErrorModel.ErrorCodes.ERR_INVALID_INPUT.code).messages(list).build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ErrorModel handleException(final Exception exception, final HttpServletRequest request) {
        return ErrorModel.builder().code(ErrorModel.ErrorCodes.ERR_GENERIC_EXCEPTION.code)
                .message(exception.toString())
                .build();
    }
}
