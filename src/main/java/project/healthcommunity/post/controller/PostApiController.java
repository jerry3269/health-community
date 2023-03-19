package project.healthcommunity.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import project.healthcommunity.category.domain.Category;
import project.healthcommunity.category.service.CategoryService;
import project.healthcommunity.member.domain.Member;
import project.healthcommunity.member.service.MemberService;
import project.healthcommunity.post.domain.Post;
import project.healthcommunity.post.dto.*;
import project.healthcommunity.post.repository.PostRepository;
import project.healthcommunity.post.repository.PostRepositoryCustom;
import project.healthcommunity.post.service.PostService;
import project.healthcommunity.trainer.domain.Trainer;
import project.healthcommunity.trainer.repository.TrainerRepositoryCustom;
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
    private final TrainerRepositoryCustom trainerRepository;
    private final PostRepository postRepository;




    @GetMapping("/api/posts")
    public List<PostDto> posts() {
        return postService.posts().stream().map(PostDto::new).collect(toList());
    }

    @GetMapping("/api/post/trainer/{id}")
    public TrainerPostDto findTrainerPostByPostId(
            @PathVariable("id") Long id) {

        Post findPost = postService.findOne(id);
        return new TrainerPostDto(findPost);
    }

    @GetMapping("/api/posts/trainer/{trainerId}")
    public List<PostDto> postsByTrainer(@PathVariable("trainerId") Long trainerId) {
        List<Post> postList = postService.findByTrainer(trainerId);
        return postList.stream().map(PostDto::new).collect(toList());
    }

    @GetMapping("/api/post/search")
    public List<PostResult> searchPostV1(@RequestBody PostSearchCond condition) {
        return postRepository.search(condition);
    }

    @GetMapping("/api/post/search/page")
    public Page<PostResult> searchPostV2_page_optimize(
            @RequestBody PostSearchCond condition,
            @PageableDefault(page = 0, size = 10, sort = "likes", direction = Sort.Direction.DESC) Pageable pageable) {
        return postRepository.search_page_optimization(condition, pageable);
    }
}
