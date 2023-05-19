package project.healthcommunity.trainer.exception;

import project.healthcommunity.global.exception.UnauthorizedException;

import static project.healthcommunity.global.error.ErrorStaticField.TRAINER_UNAUTHORIZED;

public class TrainerUnauthorizedException extends UnauthorizedException {
    private static final String MESSAGE = TRAINER_UNAUTHORIZED;

    public TrainerUnauthorizedException() {
        super(MESSAGE);
    }
}
