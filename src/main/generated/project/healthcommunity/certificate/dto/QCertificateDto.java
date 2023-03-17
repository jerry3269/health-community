package project.healthcommunity.certificate.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * project.healthcommunity.certificate.dto.QCertificateDto is a Querydsl Projection type for CertificateDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QCertificateDto extends ConstructorExpression<CertificateDto> {

    private static final long serialVersionUID = -461144316L;

    public QCertificateDto(com.querydsl.core.types.Expression<? extends project.healthcommunity.certificate.domain.Certificate> certificate) {
        super(CertificateDto.class, new Class<?>[]{project.healthcommunity.certificate.domain.Certificate.class}, certificate);
    }

}

