package project.healthcommunity.category.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.healthcommunity.category.domain.Category;
import project.healthcommunity.category.dto.CategoryResponse;
import project.healthcommunity.category.dto.CreateCategoryRequest;
import project.healthcommunity.category.exception.CategoryDupException;
import project.healthcommunity.category.repository.CategoryJpaRepository;
import project.healthcommunity.category.repository.CategoryRepositoryCustom;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepositoryCustom categoryRepositoryCustom;

    @Transactional
    public CategoryResponse register(CreateCategoryRequest createCategoryRequest) {

        validDupCategory(createCategoryRequest.getCategoryName());
        Category category = CreateCategoryRequest.toCategory(createCategoryRequest);
        Category savedCategory = categoryRepositoryCustom.save(category);
        return CategoryResponse.createByCategory(savedCategory);
    }

    private void validDupCategory(String categoryName) {
        Optional<Category> optionalCategory = categoryRepositoryCustom.findByCategoryName(categoryName);
        if(!optionalCategory.isEmpty()){
            throw new CategoryDupException();
        }
    }

    @Transactional
    public void delete(Long categoryId) {
        categoryRepositoryCustom.deleteById(categoryId);
    }

    public Category getById(Long categoryId) {
        return categoryRepositoryCustom.getById(categoryId);
    }


    public Page<Category> findAll(Pageable pageable){
        return categoryRepositoryCustom.findAll(pageable);
    }


    public Category getByCategoryName(String categoryName){
        return categoryRepositoryCustom.getByCategoryName(categoryName);
    }
}
