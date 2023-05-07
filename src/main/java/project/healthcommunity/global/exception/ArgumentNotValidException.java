package project.healthcommunity.global.exception;

import org.springframework.core.MethodParameter;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static project.healthcommunity.global.error.ErrorStaticField.BAD_REQUEST;

public abstract class ArgumentNotValidException extends RuntimeException {
    private static final int STATUS_CODE = BAD_REQUEST;
    private String message;

    public ArgumentNotValidException(String message) {
        this.message = message;
    }
}
