package project.healthcommunity.post.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import project.healthcommunity.category.domain.Category;
import project.healthcommunity.categorypost.domain.CategoryPost;
import project.healthcommunity.comment.domain.Comment;
import project.healthcommunity.post.dto.UpdatePostRequest;
import project.healthcommunity.trainer.domain.Trainer;
import project.healthcommunity.global.basic.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "title", "content", "likes" ,"categoryList"})
public class Post extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    private String title;
    @Lob
    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    private int likes = 0;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private List<CategoryPost> categoryList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    @Enumerated(EnumType.STRING)
    private PostStatus status;

    // 생성자
    @Builder
    public Post(String title, String content, List<Category> categoryList, Trainer trainer) {
        this.title = title;
        this.content = content;
        for (Category category : categoryList) {
            CategoryPost categoryPost = new CategoryPost(category, this);
        }
        addTrainer(trainer);
        this.status = PostStatus.POST;
    }

    // 연관관계 메서드 시작 //
    public void addTrainer(Trainer trainer) {
        this.trainer = trainer;
        trainer.getPostList().add(this);
        trainer.resetCountParameters();
    }


    //비지니스 로직 //
    public void update(UpdatePostRequest updatePostRequest) {
        this.title = updatePostRequest.getTitle();
        this.content = updatePostRequest.getContent();
    }


}
