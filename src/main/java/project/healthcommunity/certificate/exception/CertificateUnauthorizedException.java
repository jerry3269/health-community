package project.healthcommunity.certificate.exception;

import project.healthcommunity.global.exception.UnauthorizedException;

import static project.healthcommunity.global.error.ErrorStaticField.CERTIFICATE_UNAUTHORIZED;

public class CertificateUnauthorizedException extends UnauthorizedException {
    private static final String MESSAGE = CERTIFICATE_UNAUTHORIZED;

    public CertificateUnauthorizedException() {
        super(MESSAGE);
    }
}
