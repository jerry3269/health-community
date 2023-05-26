package project.healthcommunity.categorypost.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.healthcommunity.categorypost.dto.CategoryPostResponse;
import project.healthcommunity.categorypost.dto.CreateCategoryPostRequest;
import project.healthcommunity.categorypost.repository.CategoryPostJpaRepository;
import project.healthcommunity.categorypost.repository.CategoryPostRepositoryCustom;
import project.healthcommunity.categorypost.service.CategoryPostService;
import project.healthcommunity.global.controller.LoginForTrainer;
import project.healthcommunity.trainer.domain.TrainerSession;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categorypost")
public class CategoryPostController {

    private final CategoryPostService categoryPostService;

    @GetMapping("/search/{categoryId}")
    public ResponseEntity search_categoryId(
            @PathVariable Long categoryId,
            BindingResult bindingResult,
            @PageableDefault(page = 0, size = 10, sort = "categoryId", direction = Sort.Direction.ASC) Pageable pageable) {
        List<CategoryPostResponse> categoryPostResponses = categoryPostService.getByCategoryId(categoryId, pageable);
        return ResponseEntity.ok().body(categoryPostResponses);
    }

    @PostMapping("/add")
    public ResponseEntity save(
            @LoginForTrainer TrainerSession trainerSession,
            @RequestBody @Valid CreateCategoryPostRequest createCategoryPostRequest) {
        CategoryPostResponse categoryPostResponse = categoryPostService.save(createCategoryPostRequest);
        return ResponseEntity.ok().body(categoryPostResponse);
    }

}
