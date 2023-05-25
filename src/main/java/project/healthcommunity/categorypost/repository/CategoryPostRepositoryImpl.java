package project.healthcommunity.categorypost.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import project.healthcommunity.categorypost.domain.CategoryPost;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class CategoryPostRepositoryImpl implements CategoryPostRepositoryCustom {

    private final JPAQueryFactory QueryFactory;
    private final CategoryPostJpaRepository categoryPostJpaRepository;

    @Override
    public CategoryPost save(CategoryPost categoryPost) {
        return categoryPostJpaRepository.save(categoryPost);
    }

    @Override
    public void deleteByPost_id(Long postId) {
        categoryPostJpaRepository.deleteByPost_id(postId);
    }

    @Override
    public void deleteByCategory_idAndPost_id(Long categoryId, Long postId) {
        categoryPostJpaRepository.deleteByCategory_idAndPost_id(categoryId, postId);
    }

    @Override
    public List<CategoryPost> findByCategory_id(Long categoryId, Pageable pageable) {
        return categoryPostJpaRepository.findByCategory_id(categoryId, pageable);
    }

    @Override
    public Page<CategoryPost> findAll(Pageable pageable) {
        return categoryPostJpaRepository.findAll(pageable);
    }

    @Override
    public Long countQueryByCategoryId(Long categoryId) {
        return categoryPostJpaRepository.countQueryByCategoryId(categoryId);
    }
}
