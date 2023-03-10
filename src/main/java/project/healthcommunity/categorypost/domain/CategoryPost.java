package project.healthcommunity.categorypost.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import project.healthcommunity.category.domain.Category;
import project.healthcommunity.post.domain.Post;
import project.healthcommunity.global.domain.BaseEntity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "category"})
public class CategoryPost extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "category_post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    // 생성자
    public CategoryPost(Category category, Post post) {
        addCategory(category);
        addPost(post);
    }


    // 연관관계 메서드 시작 //
    public void addCategory(Category category) {
        this.category = category;
        category.getPostList().add(this);
    }

    public void addPost(Post post) {
        this.post = post;
        post.getCategoryList().add(this);
    }



}
