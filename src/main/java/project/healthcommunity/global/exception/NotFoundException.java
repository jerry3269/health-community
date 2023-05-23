package project.healthcommunity.global.exception;

import static project.healthcommunity.global.error.ErrorStaticField.NOT_FOUND;

public abstract class NotFoundException extends RuntimeException{
    private static final int STATUS_CODE = NOT_FOUND;
    private String MESSAGE;
    public NotFoundException(String message) {
        this.MESSAGE = message;
    }
}
