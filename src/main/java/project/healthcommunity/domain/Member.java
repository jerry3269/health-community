package project.healthcommunity.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "username", "age"})
public class Member extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;

    @OneToMany(mappedBy = "member")
    private List<Post> postList = new ArrayList<>();

    // == 생성자 == //
    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }

    // == 비지니스 로직 == //
    public void update(String username, int age){
        this.username = username;
        this.age = age;
    }
}
