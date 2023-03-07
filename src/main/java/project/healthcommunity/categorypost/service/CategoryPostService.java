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

    /**
     * 등록
     */
    @Transactional
    public void join(CategoryPost categoryPost){
        categoryPostRepository.save(categoryPost);
    }


    /**
     * 조회
     */
    public CategoryPost findOne(Long id) {
        Optional<CategoryPost> optionalCategoryPost = categoryPostRepository.findById(id);
        if(!optionalCategoryPost.isPresent()){
            throw new IllegalStateException("해당 카테고리에 등록된 글이 아닙니다");
        }
        return optionalCategoryPost.get();
    }

    /**
     * 모두조회
     */
    public List<CategoryPost> categoryPostList(){
        return categoryPostRepository.findAll();
    }

    /**
     *
     */
}
