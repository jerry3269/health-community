package project.healthcommunity.comment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.healthcommunity.comment.domain.Comment;
import project.healthcommunity.comment.dto.CreateMemberCommentRequest;
import project.healthcommunity.comment.dto.CreateTrainerCommentRequest;
import project.healthcommunity.comment.dto.MemberCommentResponse;
import project.healthcommunity.comment.dto.TrainerCommentResponse;
import project.healthcommunity.comment.service.CommentService;
import project.healthcommunity.member.domain.Member;
import project.healthcommunity.member.service.MemberService;
import project.healthcommunity.post.domain.Post;
import project.healthcommunity.post.service.PostService;
import project.healthcommunity.trainer.domain.Trainer;
import project.healthcommunity.trainer.service.TrainerService;

@RestController
@RequiredArgsConstructor
public class CommentApiController {

    private final CommentService commentService;
    private final PostService postService;
    private final MemberService memberService;
    private final TrainerService trainerService;

    @PostMapping("/api/comment/member")
    public MemberCommentResponse saveMemberComment(@RequestBody CreateMemberCommentRequest request) {
        Post post = postService.findOne(request.getPostId());
        Member member = memberService.findOne(request.getMemberId());
        Comment comment = new Comment(post, request.getContent(), member);
        commentService.write(comment);
        return new MemberCommentResponse(comment);
    }

    @PostMapping("/api/comment/trainer")
    public TrainerCommentResponse saveTrainerComment(@RequestBody CreateTrainerCommentRequest request) {
        Post post = postService.findOne(request.getPostId());
        Trainer trainer = trainerService.findOne(request.getTrainerId());
        Comment comment = new Comment(post, request.getContent(), trainer);
        commentService.write(comment);
        return new TrainerCommentResponse(comment);
    }




}
