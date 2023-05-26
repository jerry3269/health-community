package project.healthcommunity.post.exception;

import project.healthcommunity.global.exception.NotFoundException;

import static project.healthcommunity.global.error.ErrorStaticField.TRAINER_POST_NOT_FOUND;

public class TrainerPostNotFoundException extends NotFoundException {

    private static final String MESSAGE = TRAINER_POST_NOT_FOUND;
    public TrainerPostNotFoundException() {
        super(MESSAGE);
    }
}
