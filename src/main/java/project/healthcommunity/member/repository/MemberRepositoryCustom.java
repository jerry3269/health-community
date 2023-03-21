package project.healthcommunity.member.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.healthcommunity.member.dto.MemberSearchCond;
import project.healthcommunity.member.dto.MemberResult;

import java.util.List;

public interface MemberRepositoryCustom {

    Page<MemberResult> search(MemberSearchCond condition, Pageable pageable);
}
