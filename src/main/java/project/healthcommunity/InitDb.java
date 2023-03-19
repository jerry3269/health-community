package project.healthcommunity;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.healthcommunity.category.domain.Category;
import project.healthcommunity.category.service.CategoryService;
import project.healthcommunity.certificate.domain.Certificate;
import project.healthcommunity.certificate.service.CertificateService;
import project.healthcommunity.comment.domain.Comment;
import project.healthcommunity.comment.service.CommentService;
import project.healthcommunity.member.domain.Member;
import project.healthcommunity.member.service.MemberService;
import project.healthcommunity.post.domain.Post;
import project.healthcommunity.post.service.PostService;
import project.healthcommunity.trainer.domain.Trainer;
import project.healthcommunity.trainer.service.TrainerService;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @RequiredArgsConstructor
    @Transactional
    static class InitService{
        private final CategoryService categoryService;
        private final TrainerService trainerService;
        private final MemberService memberService;
        private final PostService postService;
        private final CommentService commentService;
        private final CertificateService certificateService;


        public void dbInit1(){
            Category category1 = Category.noParentBuilder()
                    .categoryName("운동")
                    .build();

            Category category2 = Category.parentBuilder()
                    .categoryName("윗몸")
                    .parent(category1)
                    .build();

            Category category3 = Category.parentBuilder()
                    .categoryName("팔굽")
                    .parent(category1)
                    .build();

            Category category4 = Category.parentBuilder()
                    .categoryName("달리기")
                    .parent(category1)
                    .build();

            categoryService.register(category1);
            categoryService.register(category2);
            categoryService.register(category3);
            categoryService.register(category4);

            String s1 = "윗몸일으키기 10개하기 본문입니다.";
            String s2 = "팔굽혀펴기 10개하기 본문입니다.";
            String s3 = "달리기 10km하기 본문입니다.";


            for(int i = 1; i<11; i++){
                trainerService.join(
                        Trainer.noCertificateBuilder()
                        .trainerName("t" + i)
                        .age(i)
                        .career(i)
                        .build());
                memberService.join(
                        Member.builder()
                                .username("m" + i)
                                .age(i)
                                .build());
            }

            for (int i = 1; i < 11; i++) {
                Trainer trainerByName = trainerService.findByTrainerName("t" + i).get(0);
                if(i%2 == 0){
                    Certificate certificate1 = Certificate.builder()
                            .trainer(trainerByName)
                            .certificateName("팔굽1급")
                            .acquisitionDate(LocalDate.of(2019, 12, 19))
                            .build();
                    
                    certificateService.register(certificate1);

                } else {
                    Certificate certificate2 = Certificate.builder()
                            .trainer(trainerByName)
                            .certificateName("윗몸1급")
                            .acquisitionDate(LocalDate.of(2013, 5, 1))
                            .build();

                    certificateService.register(certificate2);
                }

                Certificate certificate3 = Certificate.builder()
                        .trainer(trainerByName)
                        .certificateName("달리기1급")
                        .acquisitionDate(LocalDate.of(2023, 3, 19))
                        .build();

                certificateService.register(certificate3);
            }


            List<Category> findCategory1 = categoryService.categoryListByName("윗몸");
            List<Category> findCategory2 = categoryService.categoryListByName("팔굽");
            List<Category> findCategory3 = categoryService.categoryListByName("달리기");

            for(int i=1; i<11; i++){
                Trainer trainerByName = trainerService.findByTrainerName("t" + i).get(0);
                Member memberByName = memberService.findByUsername("m" + i).get(0);
                if (i % 2 == 0) {
                    Post post1 = Post.builder()
                            .title("윗몸 10개 하기" + "(" + i + ")")
                            .content(s1+"("+ i+")")
                            .categoryList(findCategory1)
                            .trainer(trainerByName)
                            .build();
                    postService.post(post1);
                } else {
                    Post post2 = Post.builder()
                            .title("팔굽 10개 하기" + "(" + i + ")")
                            .content(s2+"("+ i+")")
                            .categoryList(findCategory2)
                            .trainer(trainerByName)
                            .build();
                    postService.post(post2);
                }
                Post post3 = Post.builder()
                        .title("달리기 10km 하기" + "(" + i + ")")
                        .content(s3+"("+ i+")")
                        .categoryList(findCategory3)
                        .trainer(trainerByName)
                        .build();
                postService.post(post3);
            }
        }
        public void dbInit2(){
            for (int i = 1; i < 11; i++) {
                if (i % 2 == 0) {
                    List<Post> postsByTitle = postService.findByTitle("윗몸 10개 하기" + "(" + i + ")");
                    Post post = postsByTitle.get(0);

                    String s1 = "좋은 글 감사합니다.";
                    Member m = memberService.findByUsername("m1").get(0);

                    Comment comment1 = Comment.memberNoParentBuilder()
                            .post(post)
                            .content(s1)
                            .member(m)
                            .build();

                    commentService.write(comment1);

                    String s2 = "댓글 감사합니다.";
                    Trainer t = trainerService.findByTrainerName("t1").get(0);

                    Comment comment2 = Comment.trainerParentBuilder()
                            .post(post)
                            .content(s2)
                            .trainer(t)
                            .parent(comment1)
                            .build();
                    
                    commentService.write(comment2);
                }
            }


        }
    }

}
