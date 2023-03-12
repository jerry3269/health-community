package project.healthcommunity.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.healthcommunity.post.domain.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByTitle(String title);

    List<Post> findByMember_Id(Long memberId);

    List<Post> findByTrainer_Id(Long trainerId);
}
