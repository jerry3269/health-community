package project.healthcommunity.category.exception;

import project.healthcommunity.global.exception.DuplicationException;

import static project.healthcommunity.global.error.ErrorStaticField.DUP_CATEGORY;

public class CategoryDupException extends DuplicationException {
    private static final String MESSAGE = DUP_CATEGORY;
    public CategoryDupException() {
        super(MESSAGE);
    }
}
