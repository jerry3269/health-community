package project.healthcommunity.global.exception;

import lombok.Getter;

import static project.healthcommunity.global.error.ErrorStaticField.BAD_REQUEST;
import static project.healthcommunity.global.error.ErrorStaticField.CONVERSION_ERROR;

@Getter
public class MethodArgumentNotConversionException extends RuntimeException {
    public static final int STATUS_CODE = BAD_REQUEST;
    public static final String MESSAGE = CONVERSION_ERROR;
}
