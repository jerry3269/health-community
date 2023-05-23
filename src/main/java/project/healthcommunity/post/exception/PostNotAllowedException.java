package project.healthcommunity.post.exception;

import project.healthcommunity.global.exception.UnauthorizedException;

import static project.healthcommunity.global.error.ErrorStaticField.NOT_ALLOW_POST;

public class PostNotAllowedException extends UnauthorizedException {
    private static final String MESSAGE = NOT_ALLOW_POST;
    public PostNotAllowedException() {
        super(MESSAGE);
    }
}
