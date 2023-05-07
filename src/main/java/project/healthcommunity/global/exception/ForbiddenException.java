package project.healthcommunity.global.exception;

import static project.healthcommunity.global.error.ErrorStaticField.FORBIDDEN;

public abstract class ForbiddenException extends RuntimeException{
    private static final int STATUS_CODE = FORBIDDEN;
    private String message;

    public ForbiddenException(String message) {
        this.message = message;
    }
}
