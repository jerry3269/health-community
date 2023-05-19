package project.healthcommunity.trainer.exception;

import project.healthcommunity.global.exception.NotFoundException;

import static project.healthcommunity.global.error.ErrorStaticField.TRAINER_NOT_FOUND;

public class TrainerNotFoundException extends NotFoundException {
    private static final String MESSAGE = TRAINER_NOT_FOUND;

    public TrainerNotFoundException() {
        super(MESSAGE);
    }
}
