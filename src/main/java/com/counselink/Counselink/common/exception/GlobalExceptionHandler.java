package com.counselink.Counselink.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.text.ParseException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ParseException.class)
    public ResponseEntity<String> handleParseException(ParseException e) {
        log.error(e.toString());
        return ResponseEntity.badRequest().body("parse exception");
    }

    @ExceptionHandler(InvalidLoginException.class)
    public ResponseEntity<ErrorResponse> handleInvalidLoginException(InvalidLoginException e) {
        log.error(e.toString());
        ErrorResponse response = new ErrorResponse(ErrorCode.USER_NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
