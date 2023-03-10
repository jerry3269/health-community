package project.healthcommunity.category.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.healthcommunity.category.domain.Category;
import project.healthcommunity.category.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    /**
     * 카테고리 등록
     */
    @Transactional
    public void register(Category category) {
        validDupCategory(category);
        categoryRepository.save(category);
    }


    private void validDupCategory(Category category) {
        List<Category> result = categoryRepository.findByCategoryName(category.getCategoryName());
        if(!result.isEmpty()){
            throw new IllegalStateException("이미 존재하는 카테고리입니다.");
        }
    }

    @Transactional
    public void update(Long id, String categoryName){
        Category category = findOne(id);
        category.update(categoryName);
    }


    public Category findOne(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(!optionalCategory.isPresent()){
            throw new IllegalStateException("존재하지 않는 카테고리입니다.");
        }
        return optionalCategory.get();
    }


    public List<Category> categories(){
        return categoryRepository.findAll();
    }


    public List<Category> categoryListByName(String categoryName){
        return categoryRepository.findByCategoryName(categoryName);
    }
}
