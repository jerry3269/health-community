package project.healthcommunity.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "title", "content", "sympathy","categoryList"})
public class Post extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    private String title;

    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    private int sympathy = 0;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<CategoryPost> categoryList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    @Enumerated(EnumType.STRING)
    private PostStatus status;

    // 생성자

    public Post(String title, String content, List<Category> categoryList, Member member) {
        this.title = title;
        this.content = content;
        for (Category category : categoryList) {
            CategoryPost categoryPost = new CategoryPost(category, this);
            this.categoryList.add(categoryPost);
        }
        this.member = member;
    }

    public Post(String title, String content, List<Category> categoryList, Trainer trainer) {
        this.title = title;
        this.content = content;
        for (Category category : categoryList) {
            CategoryPost categoryPost = new CategoryPost(category, this);
            this.categoryList.add(categoryPost);
        }
        this.trainer = trainer;
    }

    // 연관관계 메서드 시작 //

    public void addMember(Member member){
        this.member = member;
        member.getPostList().add(this);
    }

    public void addTrainer(Trainer trainer) {
        this.trainer = trainer;
        trainer.getPostList().add(this);
    }


    // 연관관계 메서드 끝 //

    public void update(String title, String content, List<CategoryPost> categoryList) {
        this.title = title;
        this.content = content;
        this.categoryList = categoryList;
    }


}
