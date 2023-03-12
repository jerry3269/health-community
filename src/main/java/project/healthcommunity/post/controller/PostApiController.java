package project.healthcommunity.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.healthcommunity.category.domain.Category;
import project.healthcommunity.category.service.CategoryService;
import project.healthcommunity.member.domain.Member;
import project.healthcommunity.member.service.MemberService;
import project.healthcommunity.post.domain.Post;
import project.healthcommunity.post.dto.*;
import project.healthcommunity.post.service.PostService;
import project.healthcommunity.trainer.domain.Trainer;
import project.healthcommunity.trainer.service.TrainerService;

import java.util.List;

import static java.util.stream.Collectors.*;

@RestController
@RequiredArgsConstructor
public class PostApiController {

    private final PostService postService;
    private final MemberService memberService;
    private final TrainerService trainerService;
    private final CategoryService categoryService;

    @GetMapping("/api/post/member/{id}")
    public MemberPostDto findMemberPostById(
            @PathVariable("id") Long id) {

        Post findPost = postService.findOne(id);
        return new MemberPostDto(findPost);
    }

    @GetMapping("/api/posts/member/{memberId}")
    public List<PostDto> postsByMember(@PathVariable("memberId") Long memberId) {
        List<Post> postList = postService.findByMember(memberId);
        return postList.stream().map(PostDto::new).collect(toList());
    }

    @GetMapping("/api/posts")
    public List<PostDto> posts() {
        return postService.posts().stream().map(PostDto::new).collect(toList());
    }

    @GetMapping("/api/post/trainer/{id}")
    public TrainerPostDto findTrainerPostById(
            @PathVariable("id") Long id) {

        Post findPost = postService.findOne(id);
        return new TrainerPostDto(findPost);
    }

    @GetMapping("/api/posts/trainer/{trainerId}")
    public List<PostDto> postsByTrainer(@PathVariable("trainerId") Long trainerId) {
        List<Post> postList = postService.findByTrainer(trainerId);
        return postList.stream().map(PostDto::new).collect(toList());
    }


}
