package project.healthcommunity.certificate.exception;

import project.healthcommunity.global.exception.UnauthorizedException;

import static project.healthcommunity.global.error.ErrorStaticField.NOT_ALLOW_CERTIFICATE;

public class CertificateNotAllowedException extends UnauthorizedException {
    private static final String MESSAGE = NOT_ALLOW_CERTIFICATE;

    public CertificateNotAllowedException() {
        super(MESSAGE);
    }
}
