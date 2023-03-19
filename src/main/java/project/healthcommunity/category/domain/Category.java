package project.healthcommunity.category.domain;

import jakarta.persistence.*;
import lombok.*;
import project.healthcommunity.categorypost.domain.CategoryPost;
import project.healthcommunity.global.domain.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "categoryName"})
public class Category extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String categoryName;


    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<CategoryPost> postList = new ArrayList<>();

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;




    //생성자9

    @Builder(builderMethodName = "noParentBuilder", builderClassName = "noParentBuilder")
    public Category(String categoryName) {
        this.categoryName = categoryName;
    }
    @Builder(builderMethodName = "parentBuilder", builderClassName = "parentBuilder")
    public Category(String categoryName, Category parent) {
        this.categoryName = categoryName;
        addParentCategory(parent);
    }



    // 연관관계 메서드 시작 //
    public void addParentCategory(Category category) {
        this.parent = category;
        category.getChild().add(this);
    }


    // 비지니스 로직
    public void update(String categoryName) {
        this.categoryName = categoryName;
    }
}
