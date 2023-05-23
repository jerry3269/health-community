package project.healthcommunity.post.exception;

import project.healthcommunity.global.exception.NotFoundException;

import static project.healthcommunity.global.error.ErrorStaticField.POST_NOT_FOUND;

public class PostNotFoundException extends NotFoundException {

    public static final String MESSAGE = POST_NOT_FOUND;

    public PostNotFoundException() {
        super(MESSAGE);
    }
}
