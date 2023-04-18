package project.healthcommunity.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.healthcommunity.comment.domain.Comment;
import project.healthcommunity.comment.repository.CommentRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;


    @Transactional
    public void write(Comment comment) {
        commentRepository.save(comment);
    }


    @Transactional
    public void update(Long id, String content) {
        Comment comment = findOne(id);
        comment.update(content);
    }



    public Comment findOne(Long id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (!optionalComment.isPresent()) {
            throw new IllegalStateException("존재하지 않는 댓글입니다.");
        }
        return optionalComment.get();
    }


    public List<Comment> comments(){
        return commentRepository.findAll();
    }

    public void delete(Long id){
        commentRepository.deleteById(id);
    }
}
