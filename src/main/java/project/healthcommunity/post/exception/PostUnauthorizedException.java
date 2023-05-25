package project.healthcommunity.post.exception;

import project.healthcommunity.global.exception.UnauthorizedException;

import static project.healthcommunity.global.error.ErrorStaticField.POST_UNAUTHORIZED;

public class PostUnauthorizedException extends UnauthorizedException {
    private static final String MESSAGE = POST_UNAUTHORIZED;
    public PostUnauthorizedException() {
        super(MESSAGE);
    }
}
