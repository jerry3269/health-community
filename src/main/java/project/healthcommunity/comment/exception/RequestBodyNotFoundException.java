package project.healthcommunity.comment.exception;

import project.healthcommunity.global.exception.NotFoundException;

import static project.healthcommunity.global.error.ErrorStaticField.REQUEST_BODY_NOT_FOUND;

public class RequestBodyNotFoundException extends NotFoundException {
    private static final String MESSAGE = REQUEST_BODY_NOT_FOUND;

    public RequestBodyNotFoundException() {
        super(MESSAGE);
    }
}
