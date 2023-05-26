package project.healthcommunity.category.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import project.healthcommunity.category.domain.Category;
import project.healthcommunity.category.exception.CategoryNotFoundException;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class CategoryRepositoryImpl implements  CategoryRepositoryCustom{
    private final CategoryJpaRepository categoryJpaRepository;

    @Override
    public Category getById(Long categoryId) {
        return categoryJpaRepository.findById(categoryId).orElseThrow(CategoryNotFoundException::new);
    }

    @Override
    public Category save(Category category) {
        return categoryJpaRepository.save(category);
    }

    @Override
    public Optional<Category> findByCategoryName(String categoryName) {
        return categoryJpaRepository.findByCategoryName(categoryName);
    }

    @Override
    public Optional<Category> findById(Long categoryId) {
        return categoryJpaRepository.findById(categoryId);
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryJpaRepository.findAll(pageable);
    }

    @Override
    public Category getByCategoryName(String categoryName) {
        return categoryJpaRepository.findByCategoryName(categoryName).orElseThrow(CategoryNotFoundException::new);
    }

    @Override
    public void deleteById(Long categoryId) {
        categoryJpaRepository.deleteById(categoryId);
    }
}
