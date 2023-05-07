package project.healthcommunity.member.exception;

import project.healthcommunity.global.exception.DuplicationLoginIdException;

import static project.healthcommunity.global.error.ErrorStaticField.DUP_LOGIN_ID;

public class MemberDuplicationLoginIdException extends DuplicationLoginIdException {
    private static final String MESSAGE = DUP_LOGIN_ID;

    public MemberDuplicationLoginIdException() {
        super(MESSAGE);
    }
}
