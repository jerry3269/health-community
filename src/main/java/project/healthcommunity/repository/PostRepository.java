package project.healthcommunity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.healthcommunity.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
