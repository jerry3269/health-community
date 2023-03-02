package project.healthcommunity.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;

    @OneToMany(mappedBy = "member")
    private List<Post> postList = new ArrayList<>();

    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }
}
