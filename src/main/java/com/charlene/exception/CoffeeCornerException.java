package com.charlene.exception;

public class CoffeeCornerException extends RuntimeException{
    public CoffeeCornerException(String message) {
        super(message);
    }

    public CoffeeCornerException(Throwable cause) {
        super(cause);
    }
}
