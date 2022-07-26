package com.counselink.Counselink.common.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InvalidLoginException extends RuntimeException {
    private ErrorCode errorCode;

    public InvalidLoginException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
