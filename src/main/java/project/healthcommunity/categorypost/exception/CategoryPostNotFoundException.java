package project.healthcommunity.categorypost.exception;

import project.healthcommunity.global.exception.NotFoundException;

import static project.healthcommunity.global.error.ErrorStaticField.CATEGORY_POST_NOT_FOUND;

public class CategoryPostNotFoundException extends NotFoundException {

    private static final String MESSAGE = CATEGORY_POST_NOT_FOUND;
    public CategoryPostNotFoundException() {
        super(MESSAGE);
    }
}
