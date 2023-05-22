package project.healthcommunity;

import jakarta.annotation.PostConstruct;
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
import project.healthcommunity.member.dto.CreateMemberRequest;
import project.healthcommunity.member.service.MemberService;
import project.healthcommunity.post.domain.Post;
import project.healthcommunity.post.service.PostService;
import project.healthcommunity.trainer.domain.Trainer;
import project.healthcommunity.trainer.dto.CreateTrainerRequest;
import project.healthcommunity.trainer.service.TrainerService;

import java.time.LocalDate;
import java.util.List;

import static project.healthcommunity.global.basic.BasicStaticField.rootCategory;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.staticFieldInjection();
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @RequiredArgsConstructor
    @Transactional
    static class InitService {
        private final CategoryService categoryService;
        private final TrainerService trainerService;
        private final MemberService memberService;
        private final PostService postService;
        private final CommentService commentService;
        private final CertificateService certificateService;

        public void staticFieldInjection() {
            categoryService.register(rootCategory);
        }


        public void dbInit1() {

        }

        public void dbInit2() {



        }
    }

}
