package project.healthcommunity.member.exception;

import project.healthcommunity.global.exception.DuplicationException;

import static project.healthcommunity.global.error.ErrorStaticField.DUP_LOGIN_ID;

public class MemberDuplicationException extends DuplicationException {
    private static final String MESSAGE = DUP_LOGIN_ID;

    public MemberDuplicationException() {
        super(MESSAGE);
    }
}
