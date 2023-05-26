package project.healthcommunity.categorypost.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import project.healthcommunity.categorypost.domain.CategoryPost;

import java.util.List;
import java.util.Optional;

public interface CategoryPostRepositoryCustom{
    CategoryPost save(CategoryPost categoryPost);
    void deleteByPost_id(Long postId);
    void deleteByCategory_idAndPost_id(Long categoryId, Long postId);
    Optional<List<CategoryPost>> findByCategory_id(Long categoryId, Pageable pageable);
    List<CategoryPost> getByCategory_id(Long categoryId, Pageable pageable);
    Page<CategoryPost> findAll(Pageable pageable);
    Long countQueryByCategoryId(Long categoryId);
}
