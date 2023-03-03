package project.healthcommunity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.healthcommunity.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
