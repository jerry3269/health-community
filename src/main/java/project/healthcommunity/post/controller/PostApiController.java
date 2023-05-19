package project.healthcommunity.post.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.healthcommunity.category.domain.Category;
import project.healthcommunity.category.service.CategoryService;
import project.healthcommunity.post.domain.Post;
import project.healthcommunity.post.dto.*;
import project.healthcommunity.post.repository.PostRepository;
import project.healthcommunity.post.service.PostService;
import project.healthcommunity.trainer.domain.Trainer;
import project.healthcommunity.trainer.service.TrainerService;

import java.util.List;

import static java.util.stream.Collectors.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostApiController {

    private final PostService postService;
    private final PostRepository postRepository;
    private final TrainerService trainerService;
    private final CategoryService categoryService;


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
    public Object searchPost(
            @RequestBody @Valid PostSearchCond condition,
            BindingResult bindingResult,
            @PageableDefault(page = 0, size = 10, sort = "likes", direction = Sort.Direction.DESC) Pageable pageable) {

        if (bindingResult.hasErrors()) {
            log.info("검증 오류 발생 errors={}", bindingResult);
            return bindingResult.getAllErrors();
        }

        return postRepository.search(condition, pageable);
    }

    /**
     * @param id
     * @param request {
     *                "title": "수정",
     *                "content": "수정된 글입니다."
     *                }
     * @return
     */
    @PutMapping("/post/trainer/{id}")
    public Object updateTrainerPost(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdatePostRequest request,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.info("검증 오류 발생 errors={}", bindingResult);
            return bindingResult.getAllErrors();
        }

        postService.update(id, request.getTitle(), request.getContent());
        Post post = postService.findOne(id);
        return new TrainerPostResponse(post);
    }

    /**
     * @param request            {
     *                           "trainerId": 1,
     *                           "trainerName": "t1",
     *                           "title": "안녕하세요",
     *                           "content": "양지웅입니다",
     *                           "categoryNameList": [
     *                           "팔굽","윗몸"
     *                           ]
     *                           }
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/post/trainer")
    public Object saveTrainerPost(
            @RequestBody CreatePostRequest request,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.info("검증 오류 발생 errors={}", bindingResult);
            return bindingResult.getAllErrors();
        }

        Trainer trainer = trainerService.findOne(request.getTrainerId());

        List<Category> categoryList = request.getCategoryNameList().stream()
                .map(name -> categoryService.categoryListByName(name).get(0))
                .collect(toList());

        Post post = new Post(request.getTitle(), request.getContent(), categoryList, trainer);
        Long postId = postService.post(post);

        Post findPost = postService.findOne(postId);
        return new TrainerPostResponse(findPost);
    }
}
