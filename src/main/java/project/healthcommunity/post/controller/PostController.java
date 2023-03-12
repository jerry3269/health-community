package project.healthcommunity.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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

import static java.util.stream.Collectors.toList;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final MemberService memberService;
    private final TrainerService trainerService;
    private final CategoryService categoryService;

    /**
     * @param request
     * {
     *     "memberId": 1,
     *     "username": "m1",
     *     "title": "안녕하세요",
     *     "content": "양지웅입니다",
     *     "categoryNameList": [
     *         "팔굽","윗몸"
     *     ]
     * }
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/post/member")
    public String saveMemberPost(
            @RequestBody CreateMemberPostRequest request,
            RedirectAttributes redirectAttributes) {

        Member member = memberService.findOne(request.getMemberId());

        List<Category> categoryList = request.getCategoryNameList().stream()
                .map(name -> categoryService.categoryListByName(name).get(0))
                .collect(toList());

        Post post = new Post(request.getTitle(), request.getContent(), categoryList, member);
        Long postId = postService.post(post);

        redirectAttributes.addAttribute("id", postId);

        return "redirect:/api/post/member/{id}";
    }

    /**
     *
     * @param id
     * @param request
     * {
     *     "title": "수정",
     *     "content": "수정된 글입니다."
     * }
     *
     * @return
     */
    @PutMapping("/post/member/{id}")
    public String updateMemberPost(
            @PathVariable("id") Long id,
            @RequestBody UpdatePostRequest request) {

        postService.update(id, request.getTitle(), request.getContent());
        return "redirect:/api/post/member/{id}";
    }

    /**
     *
     * @param request
     * {
     *     "trainerId": 1,
     *     "trainerName": "t1",
     *     "title": "안녕하세요",
     *     "content": "양지웅입니다",
     *     "categoryNameList": [
     *         "팔굽","윗몸"
     *     ]
     * }
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/post/trainer")
    public String saveTrainerPost(
            @RequestBody CreateTrainerPostRequest request,
            RedirectAttributes redirectAttributes) {

        Trainer trainer = trainerService.findOne(request.getTrainerId());

        List<Category> categoryList = request.getCategoryNameList().stream()
                .map(name -> categoryService.categoryListByName(name).get(0))
                .collect(toList());

        Post post = new Post(request.getTitle(), request.getContent(), categoryList, trainer);
        Long postId = postService.post(post);

        redirectAttributes.addAttribute("id", postId);
        return "redirect:/api/post/trainer/{id}";
    }

    /**
     *
     * @param id
     * @param request
     * {
     *     "title": "수정",
     *     "content": "수정된 글입니다."
     * }
     * @return
     */
    @PutMapping("/post/trainer/{id}")
    public String updateTrainerPost(
            @PathVariable("id") Long id,
            @RequestBody UpdatePostRequest request) {

        postService.update(id, request.getTitle(), request.getContent());
        return "redirect:/api/post/trainer/{id}";
    }


}
