package project.healthcommunity.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.healthcommunity.comment.domain.Comment;
import project.healthcommunity.comment.dto.*;
import project.healthcommunity.comment.dto.member.CreateChildMemberCommentRequest;
import project.healthcommunity.comment.dto.member.CreateMemberCommentRequest;
import project.healthcommunity.comment.dto.member.MemberCommentResponse;
import project.healthcommunity.comment.dto.trainer.CreateChildTrainerCommentRequest;
import project.healthcommunity.comment.dto.trainer.CreateTrainerCommentRequest;
import project.healthcommunity.comment.dto.trainer.TrainerCommentResponse;
import project.healthcommunity.comment.exception.CommentUnauthorizedException;
import project.healthcommunity.comment.repository.CommentRepositoryCustom;
import project.healthcommunity.member.domain.Member;
import project.healthcommunity.member.domain.MemberSession;
import project.healthcommunity.member.repository.MemberRepositoryCustom;
import project.healthcommunity.post.domain.Post;
import project.healthcommunity.post.repository.PostRepositoryCustom;
import project.healthcommunity.trainer.domain.Trainer;
import project.healthcommunity.trainer.domain.TrainerSession;
import project.healthcommunity.trainer.repository.TrainerRepositoryCustom;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepositoryCustom commentRepositoryCustom;
    private final PostRepositoryCustom postRepositoryCustom;
    private final MemberRepositoryCustom memberRepositoryCustom;
    private final TrainerRepositoryCustom trainerRepositoryCustom;


    @Transactional
    public MemberCommentResponse write(CreateMemberCommentRequest createMemberCommentRequest) {
        Post post = postRepositoryCustom.getById(createMemberCommentRequest.getPostId());
        Member member = memberRepositoryCustom.getById(createMemberCommentRequest.getMemberId());
        Comment comment = new Comment(post, createMemberCommentRequest.getContent(), member);
        Comment saveComment = commentRepositoryCustom.save(comment);
        return new MemberCommentResponse(saveComment);
    }

    @Transactional
    public MemberCommentResponse write(CreateChildMemberCommentRequest createChildMemberCommentRequest) {
        Post post = postRepositoryCustom.getById(createChildMemberCommentRequest.getPostId());
        Member member = memberRepositoryCustom.getById(createChildMemberCommentRequest.getMemberId());
        Comment parent = findOne(createChildMemberCommentRequest.getParentId());
        Comment comment = new Comment(post, createChildMemberCommentRequest.getContent(), member, parent);
        Comment saveComment = commentRepositoryCustom.save(comment);
        return new MemberCommentResponse(saveComment);
    }

    @Transactional
    public TrainerCommentResponse write(CreateTrainerCommentRequest createTrainerCommentRequest) {
        Post post = postRepositoryCustom.getById(createTrainerCommentRequest.getPostId());
        Trainer trainer = trainerRepositoryCustom.getById(createTrainerCommentRequest.getTrainerId());
        Comment comment = new Comment(post, createTrainerCommentRequest.getContent(), trainer);
        Comment saveComment = commentRepositoryCustom.save(comment);
        return new TrainerCommentResponse(saveComment);
    }

    @Transactional
    public TrainerCommentResponse write(CreateChildTrainerCommentRequest createChildTrainerCommentRequest) {
        Post post = postRepositoryCustom.getById(createChildTrainerCommentRequest.getPostId());
        Trainer trainer = trainerRepositoryCustom.getById(createChildTrainerCommentRequest.getTrainerId());
        Comment parent = findOne(createChildTrainerCommentRequest.getParentId());
        Comment comment = new Comment(post, createChildTrainerCommentRequest.getContent(), trainer, parent);
        Comment saveComment = commentRepositoryCustom.save(comment);
        return new TrainerCommentResponse(saveComment);
    }

    @Transactional
    public MemberCommentResponse updateMemberComment(MemberSession memberSession, UpdateCommentRequest updateCommentRequest) {
        Comment comment = findOne(updateCommentRequest.getCommentId());
        if (isValidMember(memberSession, comment)) {
            comment.update(updateCommentRequest);
            return new MemberCommentResponse(comment);
        }
        throw new CommentUnauthorizedException();
    }

    private boolean isValidMember(MemberSession memberSession, Comment comment) {
        return (memberSession.getId() == comment.getMember().getId()) ? true : false;
    }

    @Transactional
    public TrainerCommentResponse updateTrainerComment(TrainerSession trainerSession, UpdateCommentRequest updateCommentRequest) {
        Comment comment = findOne(updateCommentRequest.getCommentId());
        if (isValidTrainer(trainerSession, comment)) {
            comment.update(updateCommentRequest);
            return new TrainerCommentResponse(comment);
        }
        throw new CommentUnauthorizedException();
    }

    private boolean isValidTrainer(TrainerSession trainerSession, Comment comment) {
        return (trainerSession.getId() == comment.getTrainer().getId()) ? true : false;
    }

    @Transactional
    public MemberCommentResponse deleteMemberComment(MemberSession memberSession, Long commentId) {
        Comment comment = findOne(commentId);
        if (isValidMember(memberSession, comment)) {
            comment.delete();
            return new MemberCommentResponse(comment);
        }
        throw new CommentUnauthorizedException();
    }

    @Transactional
    public TrainerCommentResponse deleteTrainerComment(TrainerSession trainerSession, Long commentId) {
        Comment comment = findOne(commentId);
        if (isValidTrainer(trainerSession, comment)) {
            comment.delete();
            return new TrainerCommentResponse(comment);
        }
        throw new CommentUnauthorizedException();
    }


    private Comment findOne(Long id) {
        return commentRepositoryCustom.getById(id);
    }


    public List<Comment> comments() {
        return commentRepositoryCustom.findAll();
    }


}
