package project.healthcommunity.post.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.healthcommunity.post.dto.PostResponse;
import project.healthcommunity.post.dto.PostSearchCond;

public interface PostRepositoryCustom {

    Page<PostResponse> search(PostSearchCond condition, Pageable pageable);
}
