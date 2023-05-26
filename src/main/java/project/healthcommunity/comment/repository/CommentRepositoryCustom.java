package project.healthcommunity.comment.repository;


import project.healthcommunity.comment.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepositoryCustom {
    Comment save(Comment comment);

    Comment getById(Long commentId);

    List<Comment> findAll();

    Optional<Comment> findById(Long commentId);
}
