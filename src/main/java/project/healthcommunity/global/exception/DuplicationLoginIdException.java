package project.healthcommunity.global.exception;

import static project.healthcommunity.global.error.ErrorStaticField.BAD_REQUEST;

public abstract class DuplicationLoginIdException extends RuntimeException {
    private static final int STATUS_CODE = BAD_REQUEST;
    private String message;
    public DuplicationLoginIdException(String message) {
        this.message = message;
    }
}
