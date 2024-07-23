package com.techblog.blogtech.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = NoDataFound.class)
    public @ResponseBody ErrorResponse handleNoDataFound(NoDataFound ex) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), ex.getTime());
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public @ResponseBody ErrorResponse handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), LocalDateTime.now());
    }
}
