package project.healthcommunity.categorypost.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import project.healthcommunity.categorypost.domain.CategoryPost;
import project.healthcommunity.categorypost.dto.CategoryPostDto;
import project.healthcommunity.categorypost.repository.CategoryPostRepository;

import java.util.List;

import static java.util.stream.Collectors.*;

@RestController
@RequiredArgsConstructor
public class CategoryPostController {

    private final CategoryPostRepository categoryPostRepository;

    @GetMapping("/search/{categoryId}")
    public Page<CategoryPostDto> search_categoryId(
            @PathVariable("categoryId") Long categoryId,
            @PageableDefault(page = 0, size = 10, sort = "categoryId", direction = Sort.Direction.ASC) Pageable pageable){

        List<CategoryPost> list = categoryPostRepository.findByCategory_id(categoryId, pageable);
        List<CategoryPostDto> categoryPostDtos = list.stream().map(CategoryPostDto::new).collect(toList());
        Long count = categoryPostRepository.countQueryByCategoryId(categoryId);
        return new PageImpl<>(categoryPostDtos, pageable, count);
    }

    @GetMapping("/search/{postId}")
    public Page<CategoryPostDto> search_postId(
            @PathVariable("postId") Long postId,
            @PageableDefault(page = 0, size = 10, sort = "postId", direction = Sort.Direction.ASC) Pageable pageable){

        List<CategoryPost> list = categoryPostRepository.findByCategory_id(postId, pageable);
        List<CategoryPostDto> categoryPostDtos = list.stream().map(CategoryPostDto::new).collect(toList());
        Long count = categoryPostRepository.countQueryByPostId(postId);

        return new PageImpl<>(categoryPostDtos, pageable, count);
    }
}
