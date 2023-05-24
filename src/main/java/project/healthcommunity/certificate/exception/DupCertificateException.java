package project.healthcommunity.certificate.exception;

import project.healthcommunity.global.exception.DuplicationException;

import static project.healthcommunity.global.error.ErrorStaticField.DUP_CERTIFICATE;

public class DupCertificateException extends DuplicationException {
    private static final String MESSAGE = DUP_CERTIFICATE;

    public DupCertificateException() {
        super(MESSAGE);
    }
}
