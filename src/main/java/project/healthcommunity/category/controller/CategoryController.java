package project.healthcommunity.category.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import project.healthcommunity.category.domain.Category;
import project.healthcommunity.category.dto.CategoryDto;
import project.healthcommunity.category.repository.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryRepository categoryRepository;

    @GetMapping("/categories")
    public List<CategoryDto> categories(){
        List<Category> list = categoryRepository.findAll();
        List<CategoryDto> categoryDtos = list.stream().map(CategoryDto::new).collect(toList());
        return categoryDtos;
    }
}
