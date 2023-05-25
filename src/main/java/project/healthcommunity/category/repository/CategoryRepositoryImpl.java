package project.healthcommunity.category.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import project.healthcommunity.category.domain.Category;
import project.healthcommunity.category.exception.CategoryNotFoundException;

@RequiredArgsConstructor
@Repository
public class CategoryRepositoryImpl implements  CategoryRepositoryCustom{
    private final CategoryJpaRepository categoryJpaRepository;

    @Override
    public Category getById(Long categoryId) {
        return categoryJpaRepository.findById(categoryId).orElseThrow(CategoryNotFoundException::new);
    }
}
