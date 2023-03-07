package project.healthcommunity.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import project.healthcommunity.category.domain.Category;
import project.healthcommunity.category.service.CategoryService;
import project.healthcommunity.categorypost.domain.CategoryPost;
import project.healthcommunity.categorypost.service.CategoryPostService;
import project.healthcommunity.post.domain.Post;
import project.healthcommunity.post.service.PostService;
import project.healthcommunity.trainer.domain.Trainer;
import project.healthcommunity.trainer.service.TrainerService;


import java.util.List;

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

    @PersistenceContext
    EntityManager em;




    @BeforeEach
    void init(){
        Category category1 = new Category("맨몸");
        Category category2 = new Category("윗몸", category1);
        Category category3 = new Category("팔굽", category1);
        Category category4 = new Category("달리기", category1);
        categoryService.register(category1);
        categoryService.register(category2);
        categoryService.register(category3);
        categoryService.register(category4);
    }

    @Test
    void join() {
        String s = "윗몸일으키기 10개하기 본문입니다.";
        List<Category> category1 = categoryService.categoryListByName("윗몸");
        Trainer trainer1 = new Trainer("t1",10,1);
        trainerService.join(trainer1);

        Post post1 = new Post("윗몸 10개 하기", s, category1, trainer1);
        postService.post(post1);

        Post post = postService.findOne(post1.getId());

        System.out.println("post = " + post);

    }

    @Test
    void update() {
        String s1 = "윗몸일으키기 10개하기 본문입니다.";
        List<Category> category1 = categoryService.categoryListByName("윗몸");
        Trainer trainer1 = new Trainer("t1",10,1);
        trainerService.join(trainer1);

        Post post1 = new Post("윗몸 10개 하기", s1, category1, trainer1);
        postService.post(post1);

        em.flush();
        em.clear();


        String s2 = "팔굽혀펴기 10개하기 본문입니다.";
        List<Category> category2 = categoryService.categoryListByName("팔굽");
        postService.update(post1.getId(), "팔굽 10개 하기", s2, category2);
    }

    @Test
    void posts() {
        String s1 = "윗몸일으키기 10개하기 본문입니다.";
        String s2 = "팔굽혀펴기 10개하기 본문입니다.";
        String s3 = "달리기 10km하기 본문입니다.";
        List<Category> category1 = categoryService.categoryListByName("윗몸");
        List<Category> category2 = categoryService.categoryListByName("팔굽");
        List<Category> category3 = categoryService.categoryListByName("달리기");
        Trainer trainer1 = new Trainer("t1",10,1);
        trainerService.join(trainer1);

        Post post1 = new Post("윗몸 10개 하기", s1, category1, trainer1);
        Post post2 = new Post("팔굽 10개 하기", s2, category2, trainer1);
        Post post3 = new Post("달리기 10km 하기", s3, category3, trainer1);
        postService.post(post1);
        postService.post(post2);
        postService.post(post3);


        List<Post> posts = postService.posts();
        List<CategoryPost> categoryPosts = categoryPostService.categoryPostList();

        for (Post post : posts) {
            System.out.println("post = " + post);
        }

        for (CategoryPost categoryPost : categoryPosts) {
            System.out.println("categoryPost = " + categoryPost);
        }


    }
}