package project.healthcommunity.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.healthcommunity.comment.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
