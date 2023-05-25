package project.healthcommunity.categorypost.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.healthcommunity.category.domain.Category;
import project.healthcommunity.category.repository.CategoryRepositoryCustom;
import project.healthcommunity.categorypost.domain.CategoryPost;
import project.healthcommunity.categorypost.dto.CategoryPostResponse;
import project.healthcommunity.categorypost.dto.CreateCategoryPostRequest;
import project.healthcommunity.categorypost.repository.CategoryPostJpaRepository;
import project.healthcommunity.categorypost.repository.CategoryPostRepositoryCustom;
import project.healthcommunity.post.domain.Post;
import project.healthcommunity.post.repository.PostRepositoryCustom;
import project.healthcommunity.trainer.repository.TrainerRepositoryCustom;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryPostService {

    private final CategoryPostRepositoryCustom categoryPostRepositoryCustom;
    private final PostRepositoryCustom postRepositoryCustom;
    private final CategoryRepositoryCustom categoryRepositoryCustom;


    @Transactional
    public CategoryPostResponse save(CreateCategoryPostRequest createCategoryPostRequest){
        Post post = postRepositoryCustom.getById(createCategoryPostRequest.getPostId());
        Category category = categoryRepositoryCustom.getById(createCategoryPostRequest.getCategoryId());
        CategoryPost categoryPost = CreateCategoryPostRequest.toCategoryPost(category, post);
        CategoryPost savedCategoryPost = categoryPostRepositoryCustom.save(categoryPost);
        return CategoryPostResponse.createByCategoryPost(savedCategoryPost);
    }


    public List<CategoryPostResponse> findByCategoryId(Long categoryId, Pageable pageable) {
        List<CategoryPost> categoryPosts = categoryPostRepositoryCustom.findByCategory_id(categoryId, pageable);
        List<CategoryPostResponse> categoryPostResponses = categoryPosts.stream()
                .map(categoryPost -> CategoryPostResponse.createByCategoryPost(categoryPost))
                .collect(Collectors.toList());
        return categoryPostResponses;
    }

    public Page<CategoryPost> findAll(Pageable pageable){
        return categoryPostRepositoryCustom.findAll(pageable);
    }


}
