package project.healthcommunity.member.exception;

import project.healthcommunity.global.exception.NotMatchException;

import static project.healthcommunity.global.error.ErrorStaticField.INVALID_PASSWORD;

public class MemberPasswordNotMatchException extends NotMatchException {
    private static final String MESSAGE = INVALID_PASSWORD;

    public MemberPasswordNotMatchException() {
        super(MESSAGE);
    }
}
