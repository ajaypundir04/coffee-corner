package com.charlene.exception;

/**
 * @author Ajay Singh Pundir
 * Use to handle the business exception.
 */
public class CoffeeCornerException extends RuntimeException {
    public CoffeeCornerException(String message) {
        super(message);
    }

    public CoffeeCornerException(Throwable cause) {
        super(cause);
    }
}
