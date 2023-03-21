package project.healthcommunity.member.domain;

import jakarta.persistence.*;
import lombok.*;
import project.healthcommunity.global.domain.BaseEntity;
import project.healthcommunity.comment.domain.Comment;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "username", "age"})
public class Member extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;


    @OneToMany(mappedBy = "member")
    private List<Comment> commentList = new ArrayList<>();

    private int commentCount;


    // == 생성자 == //
    @Builder
    public Member(String username, int age) {
        this.username = username;
        this.age = age;
        resetCountParameters();
    }

    // == 비지니스 로직 == //
    public void update(String username){
        this.username = username;
    }

    public void resetCountParameters(){
        this.commentCount = this.commentList.size();
    }
}
