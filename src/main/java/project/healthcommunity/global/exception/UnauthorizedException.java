package project.healthcommunity.global.exception;

import static project.healthcommunity.global.error.ErrorStaticField.UNAUTHORIZED;

public class UnauthorizedException extends RuntimeException{
    private static final int STATUS_CODE = UNAUTHORIZED;
    private String message;

    public UnauthorizedException(String message) {
        this.message = message;
    }

}
