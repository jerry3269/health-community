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
@ToString(of = {"id", "categoryName"})
public class Category extends BaseEntity{

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




    //생성자

    public Category(String categoryName, Category parent) {
        this.categoryName = categoryName;
        addParentCategory(parent);
    }

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }
    // 연관관계 메서드 시작 //

    public void addParentCategory(Category category) {
        this.parent = category;
        category.getChild().add(this);
    }


    // 연관관계 메서드 끝 //
    public void update(String categoryName) {
        this.categoryName = categoryName;
    }
}
