package project.healthcommunity.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.healthcommunity.post.domain.Post;

import java.util.List;
import java.util.Optional;

public interface PostJpaRepository extends JpaRepository<Post, Long>{
    Optional<List<Post>> findByTrainer_Id(Long trainerId);
}
