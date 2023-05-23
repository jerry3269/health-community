package project.healthcommunity.comment.exception;

import project.healthcommunity.global.exception.UnauthorizedException;

import static project.healthcommunity.global.error.ErrorStaticField.NOT_ALLOW_COMMENT;

public class CommentNotAllowedException extends UnauthorizedException {
    private static final String MESSAGE = NOT_ALLOW_COMMENT;

    public CommentNotAllowedException() {
        super(MESSAGE);
    }
}
