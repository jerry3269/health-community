package project.healthcommunity.categorypost.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.healthcommunity.categorypost.domain.CategoryPost;

public interface CategoryPostRepository extends JpaRepository<CategoryPost, Long> {
    void deleteByPost_id(Long id);
}
