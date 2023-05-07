package project.healthcommunity.member.exception;

import project.healthcommunity.global.exception.NotFoundException;

import static project.healthcommunity.global.error.ErrorStaticField.Member_Not_Found;

public class MemberNotFoundException extends NotFoundException {
    private static final String message = Member_Not_Found;

    public MemberNotFoundException() {
        super(message);
    }
}
