package com.clothify.exception;

public class CannotGetLock extends RuntimeException{
    public CannotGetLock(String message ) {
        super(message);
    }
}
