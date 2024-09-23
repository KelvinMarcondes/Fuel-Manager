package com.marcondes.FuelManager.exceptions;

public class UserNotAlreadyExistsException extends RuntimeException {

    public UserNotAlreadyExistsException(String message) {
        super(message);
    }
}
