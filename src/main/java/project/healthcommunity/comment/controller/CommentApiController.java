package project.healthcommunity.comment.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.healthcommunity.comment.dto.*;
import project.healthcommunity.comment.dto.member.CreateChildMemberCommentRequest;
import project.healthcommunity.comment.dto.member.CreateMemberCommentRequest;
import project.healthcommunity.comment.dto.member.MemberCommentResponse;
import project.healthcommunity.comment.dto.trainer.CreateChildTrainerCommentRequest;
import project.healthcommunity.comment.dto.trainer.CreateTrainerCommentRequest;
import project.healthcommunity.comment.dto.trainer.TrainerCommentResponse;
import project.healthcommunity.comment.exception.RequestBodyNotFoundException;
import project.healthcommunity.comment.service.CommentService;
import project.healthcommunity.global.controller.LoginForMember;
import project.healthcommunity.global.controller.LoginForTrainer;
import project.healthcommunity.global.exception.BindingException;
import project.healthcommunity.member.domain.MemberSession;
import project.healthcommunity.member.service.MemberService;
import project.healthcommunity.post.service.PostService;
import project.healthcommunity.trainer.domain.TrainerSession;
import project.healthcommunity.trainer.service.TrainerService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentApiController {

    private final CommentService commentService;
    private final PostService postService;
    private final MemberService memberService;
    private final TrainerService trainerService;

    @PostMapping("/member")
    public ResponseEntity saveMemberComment(@RequestBody(required = false) @Valid CreateMemberCommentRequest createMemberCommentRequest,
                                            @RequestBody(required = false) @Valid CreateChildMemberCommentRequest createChildMemberCommentRequest) {

        if (createMemberCommentRequest != null) {
            MemberCommentResponse memberCommentResponse = commentService.write(createMemberCommentRequest);
            return ResponseEntity.ok().body(memberCommentResponse);

        } else if (createChildMemberCommentRequest != null) {
            MemberCommentResponse memberCommentResponse = commentService.write(createChildMemberCommentRequest);
            return ResponseEntity.ok().body(memberCommentResponse);
        }
        throw new RequestBodyNotFoundException();
    }

    @PostMapping("/trainer")
    public ResponseEntity saveTrainerComment(@RequestBody(required = false) @Valid CreateTrainerCommentRequest createTrainerCommentRequest,
                                             @RequestBody(required = false) @Valid CreateChildTrainerCommentRequest createChildTrainerCommentRequest) {

        if (createTrainerCommentRequest != null) {
            TrainerCommentResponse trainerCommentResponse = commentService.write(createTrainerCommentRequest);
            return ResponseEntity.ok().body(trainerCommentResponse);

        } else if (createChildTrainerCommentRequest != null) {
            TrainerCommentResponse trainerCommentResponse = commentService.write(createChildTrainerCommentRequest);
            return ResponseEntity.ok().body(createChildTrainerCommentRequest);
        }
        throw new RequestBodyNotFoundException();
    }

    @PatchMapping("/member")
    public ResponseEntity updateMemberComment(@LoginForMember MemberSession memberSession,
                                              @RequestBody @Valid UpdateCommentRequest updateCommentRequest) {

        MemberCommentResponse memberCommentResponse = commentService.updateMemberComment(memberSession, updateCommentRequest);
        return ResponseEntity.ok().body(memberCommentResponse);
    }

    @PatchMapping("/trainer")
    public ResponseEntity updateTrainerComment(@LoginForTrainer TrainerSession trainerSession,
                                               @RequestBody @Valid UpdateCommentRequest updateCommentRequest) {

        TrainerCommentResponse trainerCommentResponse = commentService.updateTrainerComment(trainerSession, updateCommentRequest);
        return ResponseEntity.ok().body(trainerCommentResponse);
    }

    @DeleteMapping("/member")
    public ResponseEntity deleteMemberComment(@LoginForMember MemberSession memberSession,
                                              @ModelAttribute @Valid DeleteCommentRequest deleteCommentRequest,
                                              BindingResult bindingResult) {
        BindingException.validate(bindingResult);
        MemberCommentResponse memberCommentResponse = commentService.deleteMemberComment(memberSession, deleteCommentRequest);
        return ResponseEntity.ok().body(memberCommentResponse);
    }

    @DeleteMapping("/trainer")
    public ResponseEntity deleteTrainerComment(@LoginForTrainer TrainerSession trainerSession,
                                               @ModelAttribute @Valid DeleteCommentRequest deleteCommentRequest,
                                               BindingResult bindingResult) {

        BindingException.validate(bindingResult);
        TrainerCommentResponse trainerCommentResponse = commentService.deleteTrainerComment(trainerSession, deleteCommentRequest);
        return ResponseEntity.ok().body(trainerCommentResponse);
    }

}
