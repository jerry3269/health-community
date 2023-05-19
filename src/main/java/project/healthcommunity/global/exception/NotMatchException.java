package project.healthcommunity.global.exception;

import static project.healthcommunity.global.error.ErrorStaticField.NOT_MATCH;

public abstract class NotMatchException extends RuntimeException {
    private static final int  STATUS_CODE = NOT_MATCH;

    private String message;

    public NotMatchException(String message) {
        this.message = message;
    }
}