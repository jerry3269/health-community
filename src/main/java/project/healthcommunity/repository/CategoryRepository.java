package project.healthcommunity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.healthcommunity.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
