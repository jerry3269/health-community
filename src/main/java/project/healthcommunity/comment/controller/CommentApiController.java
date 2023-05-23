package project.healthcommunity.comment.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
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

@Slf4j
@RestController
@RequiredArgsConstructor
public class CommentApiController {

    private final CommentService commentService;
    private final PostService postService;
    private final MemberService memberService;
    private final TrainerService trainerService;

    @PostMapping("/api/comment/member")
    public Object saveMemberComment(@RequestBody @Valid CreateMemberCommentRequest request,
                                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("검증 오류 발생 errors={}", bindingResult);
            return bindingResult.getAllErrors();
        }

        Post post = postService.getById(request.getPostId());
        Member member = memberService.findOne(request.getMemberId());
        Comment comment = new Comment(post, request.getContent(), member);
        commentService.write(comment);
        return new MemberCommentResponse(comment);
    }

    @PostMapping("/api/comment/trainer")
    public Object saveTrainerComment(@RequestBody @Valid CreateTrainerCommentRequest request,
                                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("검증 오류 발생 errors={}", bindingResult);
            return bindingResult.getAllErrors();
        }

        Post post = postService.getById(request.getPostId());
        Trainer trainer = trainerService.findOne(request.getTrainerId());
        Comment comment = new Comment(post, request.getContent(), trainer);
        commentService.write(comment);
        return new TrainerCommentResponse(comment);
    }




}
