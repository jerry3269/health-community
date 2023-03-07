package project.healthcommunity.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import project.healthcommunity.category.domain.Category;
import project.healthcommunity.category.service.CategoryService;
import project.healthcommunity.comment.domain.Comment;
import project.healthcommunity.comment.service.CommentService;
import project.healthcommunity.member.domain.Member;
import project.healthcommunity.member.service.MemberService;
import project.healthcommunity.post.domain.Post;
import project.healthcommunity.post.service.PostService;
import project.healthcommunity.trainer.domain.Trainer;
import project.healthcommunity.trainer.service.TrainerService;

import java.util.List;

@SpringBootTest
@Transactional
@Rollback(value = false)
class CommentServiceTest {

    @Autowired
    CommentService commentService;
    @Autowired
    CategoryService categoryService;

    @Autowired
    PostService postService;

    @Autowired
    TrainerService trainerService;

    @Autowired
    MemberService memberService;

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

        String s1 = "윗몸일으키기 10개하기 본문입니다.";
        String s2 = "팔굽혀펴기 10개하기 본문입니다.";
        String s3 = "달리기 10km하기 본문입니다.";
        List<Category> findCategory1 = categoryService.categoryListByName("윗몸");
        List<Category> findCategory2 = categoryService.categoryListByName("팔굽");
        List<Category> findCategory3 = categoryService.categoryListByName("달리기");
        Trainer trainer1 = new Trainer("t1",10,1);
        Member member1 = new Member("m1", 10);
        trainerService.join(trainer1);
        memberService.join(member1);

        Post post1 = new Post("윗몸 10개 하기", s1, findCategory1, trainer1);
        Post post2 = new Post("팔굽 10개 하기", s2, findCategory2, trainer1);
        Post post3 = new Post("달리기 10km 하기", s3, findCategory3, trainer1);
        postService.post(post1);
        postService.post(post2);
        postService.post(post3);
    }

    @Test
    void join() {
        List<Post> postsByTitle = postService.findByTitle("윗몸 10개 하기");
        Post post = postsByTitle.get(0);

        String s1 = "좋은 글 감사합니다.";
        List<Member> m1List = memberService.findByUsername("m1");

        Comment comment1 = new Comment(post, s1, m1List.get(0));
        commentService.write(comment1);

        String s2 = "댓글 감사합니다.";
        List<Trainer> t1List = trainerService.findByTrainerName("t1");

        Comment comment2 = new Comment(post, s2, t1List.get(0), comment1);
        commentService.write(comment2);
    }

    @Test
    void update() {
        List<Post> postsByTitle = postService.findByTitle("윗몸 10개 하기");
        Post post = postsByTitle.get(0);

        String s1 = "좋은 글 감사합니다.";
        List<Member> m1List = memberService.findByUsername("m1");

        Comment comment1 = new Comment(post, s1, m1List.get(0));
        commentService.write(comment1);

        String s2 = "댓글 감사합니다.";
        List<Trainer> t1List = trainerService.findByTrainerName("t1");

        Comment comment2 = new Comment(post, s2, t1List.get(0), comment1);
        commentService.write(comment2);

        commentService.update(comment1.getId(), "좋은글 감사합니다(수정)");
        commentService.update(comment2.getId(), "댓글 감사합니다(수정");
    }

    @Test
    void findOne() {
        List<Post> postsByTitle = postService.findByTitle("윗몸 10개 하기");
        Post post = postsByTitle.get(0);

        String s1 = "좋은 글 감사합니다.";
        List<Member> m1List = memberService.findByUsername("m1");

        Comment comment1 = new Comment(post, s1, m1List.get(0));
        commentService.write(comment1);

        String s2 = "댓글 감사합니다.";
        List<Trainer> t1List = trainerService.findByTrainerName("t1");

        Comment comment2 = new Comment(post, s2, t1List.get(0), comment1);
        commentService.write(comment2);


        List<Comment> commentByTrainer = trainerService.findCommentByTrainer(t1List.get(0).getId());
        for (Comment comment : commentByTrainer) {
            System.out.println("comment = " + comment);
        }

        List<Comment> commentByMember = memberService.findCommentByMember(m1List.get(0).getId());
        for (Comment comment : commentByMember) {
            System.out.println("comment = " + comment);
        }

    }

    @Test
    void commentList() {
        List<Post> postsByTitle = postService.findByTitle("윗몸 10개 하기");
        Post post = postsByTitle.get(0);

        String s1 = "좋은 글 감사합니다.";
        List<Member> m1List = memberService.findByUsername("m1");

        Comment comment1 = new Comment(post, s1, m1List.get(0));
        commentService.write(comment1);

        String s2 = "댓글 감사합니다.";
        List<Trainer> t1List = trainerService.findByTrainerName("t1");

        Comment comment2 = new Comment(post, s2, t1List.get(0), comment1);
        commentService.write(comment2);

        List<Comment> commentList = commentService.comments();

        for (Comment comment : commentList) {
            System.out.println("comment = " + comment);
        }

    }
}