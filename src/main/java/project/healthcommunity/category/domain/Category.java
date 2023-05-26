package project.healthcommunity.category.domain;

import jakarta.persistence.*;
import lombok.*;
import project.healthcommunity.categorypost.domain.CategoryPost;
import project.healthcommunity.global.basic.BaseEntity;

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
    @Column(unique = true)
    private String categoryName;


    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<CategoryPost> postList = new ArrayList<>();

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @Builder(builderMethodName = "noParentBuilder", builderClassName = "noParentBuilder")
    public Category(String categoryName) {
        this.categoryName = categoryName;
    }
    @Builder(builderMethodName = "parentBuilder", builderClassName = "parentBuilder")
    public Category(String categoryName, Category parent) {
        this.categoryName = categoryName;
        addParentCategory(parent);
    }

    public void addParentCategory(Category category) {
        this.parent = category;
        category.getChild().add(this);
    }


}
