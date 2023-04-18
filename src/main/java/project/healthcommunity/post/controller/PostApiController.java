package project.healthcommunity.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import project.healthcommunity.post.domain.Post;
import project.healthcommunity.post.dto.*;
import project.healthcommunity.post.repository.PostRepository;
import project.healthcommunity.post.service.PostService;

import java.util.List;

import static java.util.stream.Collectors.*;

@RestController
@RequiredArgsConstructor
public class PostApiController {

    private final PostService postService;
    private final PostRepository postRepository;




    @GetMapping("/api/posts")
    public List<PostResponse> posts() {
        return postService.posts().stream().map(PostResponse::new).collect(toList());
    }

    @GetMapping("/api/post/trainer/{id}")
    public TrainerPostResponse findTrainerPostByPostId(
            @PathVariable("id") Long id) {

        Post findPost = postService.findOne(id);
        return new TrainerPostResponse(findPost);
    }

    @GetMapping("/api/posts/trainer/{trainerId}")
    public List<PostResponse> postsByTrainer(@PathVariable("trainerId") Long trainerId) {
        List<Post> postList = postService.findByTrainer(trainerId);
        return postList.stream().map(PostResponse::new).collect(toList());
    }

    @GetMapping("/api/post/search")
    public Page<PostResponse> searchPost(
            @RequestBody PostSearchCond condition,
            @PageableDefault(page = 0, size = 10, sort = "likes", direction = Sort.Direction.DESC) Pageable pageable) {
        return postRepository.search(condition, pageable);
    }
}
