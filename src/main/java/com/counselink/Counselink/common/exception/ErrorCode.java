package com.counselink.Counselink.common.exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum ErrorCode {
    NOT_FOUND(404, HttpStatus.NOT_FOUND, "NOT FOUND"),
    INTER_SERVER_ERROR(500, HttpStatus.INTERNAL_SERVER_ERROR, "INTERNEL_SERVER_ERROR"),
    USER_NOT_FOUND(404, HttpStatus.BAD_REQUEST, "User not exist.");
    // Can add custom error code of Exceptions

    private int status;
    private HttpStatus errorCode;
    private String message;
}
