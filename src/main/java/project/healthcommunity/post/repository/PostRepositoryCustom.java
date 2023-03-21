package project.healthcommunity.post.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.healthcommunity.post.dto.PostResult;
import project.healthcommunity.post.dto.PostSearchCond;

import java.util.List;

public interface PostRepositoryCustom {

    Page<PostResult> search(PostSearchCond condition, Pageable pageable);
}
