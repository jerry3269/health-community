package project.healthcommunity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.healthcommunity.domain.CategoryPost;

public interface CategoryPostRepository extends JpaRepository<CategoryPost, Long> {
}
