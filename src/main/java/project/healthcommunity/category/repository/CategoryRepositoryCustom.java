package project.healthcommunity.category.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.healthcommunity.category.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepositoryCustom {

    Category getById(Long categoryId);

    Category save(Category category);

    Optional<Category> findByCategoryName(String categoryName);

    Optional<Category> findById(Long categoryId);

    Page<Category> findAll(Pageable pageable);

    Category getByCategoryName(String categoryName);

    void deleteById(Long categoryId);
}
