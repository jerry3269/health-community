package project.healthcommunity.trainer.exception;

import project.healthcommunity.global.exception.DuplicationLoginIdException;

import static project.healthcommunity.global.error.ErrorStaticField.DUP_LOGIN_ID;

public class TrainerDuplicationLoginIdException extends DuplicationLoginIdException {
    private static final String MESSAGE = DUP_LOGIN_ID;

    public TrainerDuplicationLoginIdException() {
        super(MESSAGE);
    }
}
