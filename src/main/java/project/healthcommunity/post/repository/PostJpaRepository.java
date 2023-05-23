package project.healthcommunity.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.healthcommunity.post.domain.Post;

import java.util.List;

public interface PostJpaRepository extends JpaRepository<Post, Long>{
    List<Post> findByTrainer_Id(Long trainerId);
}
