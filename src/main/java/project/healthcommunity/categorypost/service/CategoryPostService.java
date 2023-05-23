package project.healthcommunity.categorypost.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.healthcommunity.categorypost.domain.CategoryPost;
import project.healthcommunity.categorypost.repository.CategoryPostRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryPostService {

    private final CategoryPostRepository categoryPostRepository;

    @Transactional
    public void join(CategoryPost categoryPost){
        categoryPostRepository.save(categoryPost);
    }


    public CategoryPost findOne(Long id) {
        Optional<CategoryPost> optionalCategoryPost = categoryPostRepository.findById(id);
        if(!optionalCategoryPost.isPresent()){
            throw new IllegalStateException("해당 카테고리에 등록된 글이 아닙니다");
        }
        return optionalCategoryPost.get();
    }

    public List<CategoryPost> categoryPostList(){
        return categoryPostRepository.findAll();
    }

    public void deleteByCategoryIdAndPostId(Long categoryId, Long postId) {
        categoryPostRepository.deleteByCategory_idAndPost_id(categoryId, postId);
    }

    public void save(CategoryPost categoryPost) {
        categoryPostRepository.save(categoryPost);
    }

    public void deleteByPostId(Long postId) {
        categoryPostRepository.deleteByPost_id(postId);
    }
}
