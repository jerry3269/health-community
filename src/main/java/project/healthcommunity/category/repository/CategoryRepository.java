package project.healthcommunity.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.healthcommunity.category.domain.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByCategoryName(String categoryName);
}
