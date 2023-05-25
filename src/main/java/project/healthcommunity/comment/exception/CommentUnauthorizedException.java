package project.healthcommunity.comment.exception;

import project.healthcommunity.global.exception.UnauthorizedException;

import static project.healthcommunity.global.error.ErrorStaticField.COMMENT_UNAUTHORIZED;

public class CommentUnauthorizedException extends UnauthorizedException {
    private static final String MESSAGE = COMMENT_UNAUTHORIZED;

    public CommentUnauthorizedException() {
        super(MESSAGE);
    }
}
