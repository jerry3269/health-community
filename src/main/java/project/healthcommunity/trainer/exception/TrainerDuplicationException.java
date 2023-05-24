package project.healthcommunity.trainer.exception;

import project.healthcommunity.global.exception.DuplicationException;

import static project.healthcommunity.global.error.ErrorStaticField.DUP_LOGIN_ID;

public class TrainerDuplicationException extends DuplicationException {
    private static final String MESSAGE = DUP_LOGIN_ID;

    public TrainerDuplicationException() {
        super(MESSAGE);
    }
}
