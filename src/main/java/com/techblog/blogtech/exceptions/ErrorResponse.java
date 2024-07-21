package com.techblog.blogtech.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ErrorResponse {

    private int statusCode;
    private String message;
    private LocalDateTime time;
}
