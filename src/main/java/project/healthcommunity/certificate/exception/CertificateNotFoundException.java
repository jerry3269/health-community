package project.healthcommunity.certificate.exception;

import project.healthcommunity.global.exception.NotFoundException;

import static project.healthcommunity.global.error.ErrorStaticField.CERTIFICATE_NOT_FOUND;

public class CertificateNotFoundException extends NotFoundException {
    private static final String MESSAGE = CERTIFICATE_NOT_FOUND;

    public CertificateNotFoundException() {
        super(MESSAGE);
    }
}
