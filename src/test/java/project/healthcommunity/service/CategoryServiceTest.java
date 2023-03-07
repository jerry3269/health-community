package project.healthcommunity.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import project.healthcommunity.category.domain.Category;
import project.healthcommunity.category.repository.CategoryRepository;
import project.healthcommunity.category.service.CategoryService;

import java.util.List;

@SpringBootTest
@Transactional
@Rollback(value = false)
class CategoryServiceTest {

    @Autowired
    CategoryService categoryService;
    @Autowired
    private CategoryRepository categoryRepository;


    @Test
    void join() {
        Category category1 = new Category("맨몸");
        Category category2 = new Category("윗몸", category1);
        Category category3 = new Category("팔굽", category1);
        Category category4 = new Category("달리기", category1);
        categoryService.register(category1);
        categoryService.register(category2);
        categoryService.register(category3);
        categoryService.register(category4);

        Category categoryOne = categoryService.findOne(category1.getId());

        System.out.println("categoryOne = " + categoryOne);

    }

    @Test
    void update() {
        Category category1 = new Category("맨몸");
        Category category2 = new Category("윗몸", category1);
        Category category3 = new Category("팔굽", category1);
        Category category4 = new Category("달리기", category1);
        categoryService.register(category1);
        categoryService.register(category2);
        categoryService.register(category3);
        categoryService.register(category4);

        categoryService.update(category4.getId(), "체력");

        Category categoryOne = categoryService.findOne(category1.getId());
        System.out.println("categoryOne = " + categoryOne);
    }


    @Test
    void categories() {

        Category category1 = new Category("맨몸");
        Category category2 = new Category("윗몸", category1);
        Category category3 = new Category("팔굽", category1);
        Category category4 = new Category("달리기", category1);
        categoryService.register(category1);
        categoryService.register(category2);
        categoryService.register(category3);
        categoryService.register(category4);

        List<Category> categories = categoryService.categories();

        for (Category category : categories) {
            System.out.println("category = " + category);
        }
    }
}