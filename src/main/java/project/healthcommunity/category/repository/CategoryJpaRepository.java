package project.healthcommunity.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.healthcommunity.category.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryJpaRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByCategoryName(String categoryName);
}
