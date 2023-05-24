package project.healthcommunity.global.exception;

import static project.healthcommunity.global.error.ErrorStaticField.BAD_REQUEST;

public abstract class DuplicationException extends RuntimeException {
    private static final int STATUS_CODE = BAD_REQUEST;
    private String MESSAGE;
    public DuplicationException(String message) {
        this.MESSAGE = message;
    }
}
