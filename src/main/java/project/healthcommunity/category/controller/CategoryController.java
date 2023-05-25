package project.healthcommunity.category.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import project.healthcommunity.category.domain.Category;
import project.healthcommunity.category.dto.CategoryResponse;
import project.healthcommunity.category.repository.CategoryJpaRepository;

import java.util.List;

import static java.util.stream.Collectors.*;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryJpaRepository categoryJpaRepository;

    @GetMapping("/categories")
    public List<CategoryResponse> categories(){
        List<Category> list = categoryJpaRepository.findAll();
        List<CategoryResponse> categoryResponses = list.stream().map(CategoryResponse::new).collect(toList());
        return categoryResponses;
    }
}
