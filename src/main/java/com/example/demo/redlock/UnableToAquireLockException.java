package com.example.demo.redlock;

/**
 * @author 860120014
 * @date 2021-07-16
 */
public class UnableToAquireLockException extends RuntimeException{
    public UnableToAquireLockException() {
    }

    public UnableToAquireLockException(String message) {
        super(message);
    }

    public UnableToAquireLockException(String message, Throwable cause) {
        super(message, cause);
    }
}
