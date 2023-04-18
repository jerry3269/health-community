package project.healthcommunity.member.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.healthcommunity.member.dto.MemberResponse;
import project.healthcommunity.member.dto.MemberSearchCond;

public interface MemberRepositoryCustom {

    Page<MemberResponse> search(MemberSearchCond condition, Pageable pageable);
}
