package project.healthcommunity.comment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import project.healthcommunity.comment.service.CommentService;
import project.healthcommunity.member.service.MemberService;
import project.healthcommunity.post.service.PostService;
import project.healthcommunity.trainer.service.TrainerService;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final PostService postService;
    private final MemberService memberService;
    private final TrainerService trainerService;
}
