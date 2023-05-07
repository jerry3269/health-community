package project.healthcommunity.member.exception;

import project.healthcommunity.global.exception.NotFoundException;

import static project.healthcommunity.global.error.ErrorStaticField.MEMBER_NOT_FOUND;

public class MemberNotFoundException extends NotFoundException {
    private static final String MESSAGE = MEMBER_NOT_FOUND;

    public MemberNotFoundException() {
        super(MESSAGE);
    }
}
