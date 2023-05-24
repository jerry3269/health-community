package project.healthcommunity.certificate.exception;

import project.healthcommunity.global.exception.NotFoundException;

import static project.healthcommunity.global.error.ErrorStaticField.CERTIFICATE_NOT_YET;

public class CertificateNotYetException extends NotFoundException {
    private static final String MESSAGE = CERTIFICATE_NOT_YET;

    public CertificateNotYetException() {
        super(MESSAGE);
    }
}
