package com.sdh2.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoUsersFoundException extends RuntimeException {
    public NoUsersFoundException(String message) {
        super(message);
    }
    public NoUsersFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public NoUsersFoundException(Throwable cause) {
        super(cause);
    }

}