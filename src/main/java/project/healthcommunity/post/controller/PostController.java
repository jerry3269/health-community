package project.healthcommunity.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.healthcommunity.category.domain.Category;
import project.healthcommunity.category.service.CategoryService;
import project.healthcommunity.categorypost.domain.CategoryPost;
import project.healthcommunity.categorypost.dto.CategoryPostDto;
import project.healthcommunity.member.domain.Member;
import project.healthcommunity.member.dto.CreateMemberResponse;
import project.healthcommunity.member.service.MemberService;
import project.healthcommunity.post.domain.Post;
import project.healthcommunity.post.dto.CreateMemberPostRequest;
import project.healthcommunity.post.dto.MemberPostDto;
import project.healthcommunity.post.service.PostService;
import project.healthcommunity.trainer.service.TrainerService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final MemberService memberService;
    private final TrainerService trainerService;
    private final CategoryService categoryService;

    /**
     * {
     *     "memberId": 1,
     *     "username": "m1",
     *     "title": "안녕하세요",
     *     "content": "양지웅입니다",
     *     "categoryNameList": [
     *         "팔굽","윗몸"
     *     ]
     * }
     */

    @PostMapping("/api/save/post/member")
    public MemberPostDto saveMemberPost(@RequestBody CreateMemberPostRequest request) {
        Member member = memberService.findOne(request.getMemberId());
        List<Category> categoryList = new ArrayList<>();

        request.getCategoryNameList().stream().forEach(name ->
            categoryList.add(categoryService.categoryListByName(name).get(0))
        );

        Post post = new Post(request.getTitle(), request.getContent(), categoryList, member);
        postService.post(post);
        return new MemberPostDto(post);
    }
}
