package project.healthcommunity.comment.exception;

import project.healthcommunity.global.exception.NotFoundException;

import static project.healthcommunity.global.error.ErrorStaticField.COMMENT_NOT_FOUND;

public class CommentNotFoundException extends NotFoundException {
    public static final String MESSAGE = COMMENT_NOT_FOUND;

    public CommentNotFoundException() {
        super(MESSAGE);
    }
}
