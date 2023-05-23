package project.healthcommunity.global.exception;

import org.springframework.validation.BindingResult;

import static project.healthcommunity.global.error.ErrorStaticField.BAD_REQUEST;
import static project.healthcommunity.global.error.ErrorStaticField.BINDING_ERROR;


public class BindingException extends RuntimeException{
    private static final int STATUS_CODE = BAD_REQUEST;

    private static final String MESSAGE = BINDING_ERROR;
    private BindingResult bindingResult;


    public BindingException(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }

    public static void validate(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BindingException(bindingResult);
        }
    }
}
