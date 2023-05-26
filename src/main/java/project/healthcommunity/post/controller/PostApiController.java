package project.healthcommunity.post.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.healthcommunity.global.controller.LoginForTrainer;
import project.healthcommunity.global.exception.BindingException;
import project.healthcommunity.post.dto.*;
import project.healthcommunity.post.repository.PostJpaRepository;
import project.healthcommunity.post.repository.PostRepositoryCustom;
import project.healthcommunity.post.service.PostService;
import project.healthcommunity.trainer.domain.TrainerSession;

import java.util.List;

import static java.util.stream.Collectors.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostApiController {

    private final PostService postService;
    private final PostRepositoryCustom postRepositoryCustom;

    @GetMapping("/list")
    public ResponseEntity<List<PostResponse>> posts() {
        List<PostResponse> postResponseList = postService.posts().stream().map(PostResponse::new).collect(toList());
        return ResponseEntity.ok().body(postResponseList);
    }

    @GetMapping("/list/{trainerId}")
    public ResponseEntity<List<PostResponse>> postsByTrainer(
            @PathVariable("trainerId") Long trainerId,
            BindingResult bindingResult) {

        BindingException.validate(bindingResult);
        List<PostResponse> postResponseList = postService
                .getByTrainer(trainerId)
                .stream()
                .map(PostResponse::new)
                .collect(toList());
        return ResponseEntity.ok().body(postResponseList);
    }

    @GetMapping("/list/search")
    public ResponseEntity search(
            @ModelAttribute @Valid PostSearchCond condition,
            BindingResult bindingResult,
            @PageableDefault(page = 0, size = 10, sort = "likes", direction = Sort.Direction.DESC) Pageable pageable) {
        BindingException.validate(bindingResult);
        Page<PostResponse> postResponsePage = postRepositoryCustom.search(condition, pageable);
        return ResponseEntity.ok().body(postResponsePage);
    }


    @PatchMapping("/update")
    public ResponseEntity update(
            @LoginForTrainer TrainerSession trainerSession,
            @RequestBody @Valid UpdatePostRequest updatePostRequest) {

        PostResponse postResponse = postService.update(trainerSession, updatePostRequest);
        return ResponseEntity.ok().body(postResponse);
    }

    @PostMapping("/upload")
    public ResponseEntity save(
            @LoginForTrainer TrainerSession trainerSession,
            @RequestBody @Valid CreatePostRequest createPostRequest) {

        PostResponse postResponse = postService.post(trainerSession, createPostRequest);
        return ResponseEntity.ok().body(postResponse);
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity delete(
            @LoginForTrainer TrainerSession trainerSession,
            @PathVariable Long postId,
            BindingResult bindingResult) {

        BindingException.validate(bindingResult);
        postService.delete(trainerSession, postId);
        return ResponseEntity.ok().build();
    }
}
