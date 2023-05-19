package project.healthcommunity.trainer.exception;

import project.healthcommunity.global.exception.NotMatchException;

import static project.healthcommunity.global.error.ErrorStaticField.INVALID_PASSWORD;

public class TrainerNotMatchException extends NotMatchException {
    private static final String MESSAGE = INVALID_PASSWORD;

    public TrainerNotMatchException() {
        super(MESSAGE);
    }
}
