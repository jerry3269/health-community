package project.healthcommunity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.healthcommunity.domain.Comment;
import project.healthcommunity.repository.CommentRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    /**
     * 댓글 작성
     */
    @Transactional
    public void join(Comment comment) {
        commentRepository.save(comment);
    }

    /**
     * 수정
     */
    @Transactional
    public void update(Long id, String content) {
        Comment comment = findOne(id);
        comment.update(content);
    }


    /**
     * 조회
     */
    public Comment findOne(Long id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (!optionalComment.isPresent()) {
            throw new IllegalStateException("존재하지 않는 댓글입니다.");
        }
        return optionalComment.get();
    }

    /**
     * 전체 조회
     */
    public List<Comment> comments(){
        return commentRepository.findAll();
    }
}
