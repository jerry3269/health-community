package project.healthcommunity.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import project.healthcommunity.domain.Category;
import project.healthcommunity.domain.Post;
import project.healthcommunity.domain.Trainer;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class PostServiceTest {

    @Autowired
    PostService postService;
    @Autowired
    CategoryPostService categoryPostService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    TrainerService trainerService;



    @BeforeEach
    void init(){
        Category category1 = new Category("맨몸");
        Category category2 = new Category("윗몸", category1);
        Category category3 = new Category("팔굽", category1);
        Category category4 = new Category("달리기", category1);
        categoryService.join(category1);
        categoryService.join(category2);
        categoryService.join(category3);
        categoryService.join(category4);
    }

    @Test
    void join() {
        String s = "윗몸일으키기 10개하기 본문입니다.";
        List<Category> category1 = categoryService.categoryListByName("윗몸");
        Trainer trainer1 = new Trainer("t1",10,1);
        trainerService.join(trainer1);

        Post post1 = new Post("윗몸 10개 하기", s, category1, trainer1);
        postService.join(post1);

        Post post = postService.findOne(post1.getId());

        System.out.println("post = " + post);

    }

    @Test
    void update() {
    }

    @Test
    void findOne() {
    }

    @Test
    void posts() {
    }
}