package project.healthcommunity.category.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.healthcommunity.category.domain.Category;
import project.healthcommunity.category.repository.CategoryJpaRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryJpaRepository categoryJpaRepository;

    @Transactional
    public void register(Category category) {
        validDupCategory(category);
        categoryJpaRepository.save(category);
    }

    private void validDupCategory(Category category) {
        List<Category> result = categoryJpaRepository.findByCategoryName(category.getCategoryName());
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
        Optional<Category> optionalCategory = categoryJpaRepository.findById(id);
        if(!optionalCategory.isPresent()){
            throw new IllegalStateException("존재하지 않는 카테고리입니다.");
        }
        return optionalCategory.get();
    }


    public List<Category> categories(){
        return categoryJpaRepository.findAll();
    }


    public List<Category> categoryListByName(String categoryName){
        return categoryJpaRepository.findByCategoryName(categoryName);
    }
}
