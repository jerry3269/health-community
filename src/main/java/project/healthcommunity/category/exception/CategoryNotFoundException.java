package project.healthcommunity.category.exception;

import project.healthcommunity.global.exception.NotFoundException;

import static project.healthcommunity.global.error.ErrorStaticField.CATEGORY_NOT_FOUND;

public class CategoryNotFoundException extends NotFoundException {
    private static final String MESSAGE = CATEGORY_NOT_FOUND;

    public CategoryNotFoundException() {
        super(MESSAGE);
    }
}
