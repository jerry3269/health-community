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


            for(int i = 1; i<11; i++){
                trainerService.join(new Trainer("t"+i,i,i));
                memberService.join(new Member("m"+i, i));
            }

            for (int i = 1; i < 11; i++) {
                Trainer trainerByName = trainerService.findByTrainerName("t" + i).get(0);
                if(i%2 == 0){
                    Certificate certificate1 = new Certificate(trainerByName, "팔굽1급", LocalDate.of(2019,12,19));
                    certificateService.register(certificate1);

                } else {
                    Certificate certificate2 = new Certificate(trainerByName, "윗몸1급", LocalDate.of(2013, 1, 1));
                    certificateService.register(certificate2);
                }
                Certificate certificate3 = new Certificate(trainerByName, "체력1급", LocalDate.of(2023, 10, 10));
                certificateService.register(certificate3);
            }


            List<Category> findCategory1 = categoryService.categoryListByName("윗몸");
            List<Category> findCategory2 = categoryService.categoryListByName("팔굽");
            List<Category> findCategory3 = categoryService.categoryListByName("달리기");

            for(int i=1; i<11; i++){
                Trainer trainerByName = trainerService.findByTrainerName("t" + i).get(0);
                Member memberByName = memberService.findByUsername("m" + i).get(0);
                if (i % 2 == 0) {
                    Post post1 = new Post("윗몸 10개 하기"+"("+i+")", s1+"("+ i+")", findCategory1, trainerByName);
                    postService.post(post1);
                } else {
                    Post post2 = new Post("팔굽 10개 하기"+"("+i+")", s2+"("+ i+")", findCategory2, memberByName);
                    postService.post(post2);
                }
                Post post3 = new Post("달리기 10km 하기"+"("+i+")", s3+"("+ i+")", findCategory3, trainerByName);
                postService.post(post3);
            }
        }
        public void dbInit2(){
            for (int i = 1; i < 11; i++) {
                if (i % 2 == 0) {
                    List<Post> postsByTitle = postService.findByTitle("윗몸 10개 하기" + "(" + i + ")");
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
            }


        }
    }

}
