package project.healthcommunity.post.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.healthcommunity.post.domain.Post;
import project.healthcommunity.post.dto.PostResponse;
import project.healthcommunity.post.dto.PostSearchCond;

import java.util.List;
import java.util.Optional;

public interface PostRepositoryCustom {

    Page<PostResponse> search(PostSearchCond condition, Pageable pageable);

    void save(Post post);

    Optional<Post> findById(Long postId);

    List<Post> findAll();

    void deleteById(Long postId);

    List<Post> findByTrainer_Id(Long trainerId);

    Post getById(Long postId);
}
