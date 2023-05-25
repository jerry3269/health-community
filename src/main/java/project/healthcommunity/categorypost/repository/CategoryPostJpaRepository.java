package project.healthcommunity.categorypost.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.healthcommunity.categorypost.domain.CategoryPost;

import java.util.List;

public interface CategoryPostJpaRepository extends JpaRepository<CategoryPost, Long> {
    void deleteByPost_id(Long postId);

    void deleteByCategory_idAndPost_id(Long categoryId, Long postId);

    List<CategoryPost> findByCategory_id(Long categoryId, Pageable pageable);

    @Query("select count(cp.id) from CategoryPost cp where cp.category.id = :categoryId")
    Long countQueryByCategoryId(@Param("categoryId") Long categoryId);
}
