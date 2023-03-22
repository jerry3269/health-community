package project.healthcommunity.categorypost.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.healthcommunity.categorypost.domain.CategoryPost;
import project.healthcommunity.categorypost.dto.CategoryPostDto;
import project.healthcommunity.categorypost.repository.CategoryPostRepository;

import java.util.List;

import static java.util.stream.Collectors.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categorypost")
public class CategoryPostController {

    private final CategoryPostRepository categoryPostRepository;

    @GetMapping("/search/category/{id}")
    public Page<CategoryPostDto> search_categoryId(
            @PathVariable("id") Long id,
            @PageableDefault(page = 0, size = 10, sort = "categoryId", direction = Sort.Direction.ASC) Pageable pageable){

        List<CategoryPost> list = categoryPostRepository.findByCategory_id(id, pageable);
        List<CategoryPostDto> categoryPostDtos = list.stream().map(CategoryPostDto::new).collect(toList());
        Long count = categoryPostRepository.countQueryByCategoryId(id);
        return new PageImpl<>(categoryPostDtos, pageable, count);
    }

    @GetMapping("/search/post/{id}")
    public Page<CategoryPostDto> search_postId(
            @PathVariable("id") Long id,
            @PageableDefault(page = 0, size = 10, sort = "postId", direction = Sort.Direction.ASC) Pageable pageable){

        List<CategoryPost> list = categoryPostRepository.findByCategory_id(id, pageable);
        List<CategoryPostDto> categoryPostDtos = list.stream().map(CategoryPostDto::new).collect(toList());
        Long count = categoryPostRepository.countQueryByPostId(id);

        return new PageImpl<>(categoryPostDtos, pageable, count);
    }
}
