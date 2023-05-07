package project.healthcommunity.member.exception;

import project.healthcommunity.global.exception.UnauthorizedException;

import static project.healthcommunity.global.error.ErrorStaticField.MEMBER_UNAUTHORIZED;

public class MemberUnauthorizedException extends UnauthorizedException {
    private static final String MESSAGE = MEMBER_UNAUTHORIZED;

    public MemberUnauthorizedException() {
        super(MESSAGE);
    }
}
