package project.healthcommunity.comment.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import project.healthcommunity.comment.domain.Comment;
import project.healthcommunity.comment.exception.CommentNotFoundException;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepositoryCustom{

    private final CommentJpaRepository commentJpaRepository;

    @Override
    public Comment save(Comment comment) {
        return commentJpaRepository.save(comment);
    }

    @Override
    public Comment getById(Long commentId) {
        return commentJpaRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
    }

    @Override
    public List<Comment> findAll() {
        return commentJpaRepository.findAll();
    }

    @Override
    public Optional<Comment> findById(Long commentId) {
        return commentJpaRepository.findById(commentId);
    }
}
