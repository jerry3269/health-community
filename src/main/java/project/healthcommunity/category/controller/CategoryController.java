package project.healthcommunity.category.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.healthcommunity.category.domain.Category;
import project.healthcommunity.category.dto.CategoryResponse;
import project.healthcommunity.category.dto.CreateCategoryRequest;
import project.healthcommunity.category.repository.CategoryJpaRepository;
import project.healthcommunity.category.service.CategoryService;
import project.healthcommunity.global.exception.BindingException;

import java.util.List;

import static java.util.stream.Collectors.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/list")
    public ResponseEntity list(@PageableDefault(page = 0, size = 10, sort = "categoryId", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Category> categoryPage = categoryService.findAll(pageable);
        Page<CategoryResponse> categoryResponsePage =
                (Page<CategoryResponse>) categoryPage.stream()
                        .map(category -> CategoryResponse.createByCategory(category))
                        .collect(toList());
        return ResponseEntity.ok().body(categoryResponsePage);
    }

    @PostMapping("/add")
    public ResponseEntity save(@RequestBody @Valid CreateCategoryRequest createCategoryRequest) {
        CategoryResponse categoryResponse = categoryService.register(createCategoryRequest);
        return ResponseEntity.ok().body(categoryResponse);
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity delete(@PathVariable Long categoryId) {
        categoryService.delete(categoryId);
        return ResponseEntity.ok().build();
    }
}
