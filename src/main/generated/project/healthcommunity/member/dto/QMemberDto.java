package project.healthcommunity.member.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * project.healthcommunity.member.dto.QMemberDto is a Querydsl Projection type for MemberDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QMemberDto extends ConstructorExpression<MemberDto> {

    private static final long serialVersionUID = 870803224L;

    public QMemberDto(com.querydsl.core.types.Expression<? extends project.healthcommunity.member.domain.Member> member) {
        super(MemberDto.class, new Class<?>[]{project.healthcommunity.member.domain.Member.class}, member);
    }

}

