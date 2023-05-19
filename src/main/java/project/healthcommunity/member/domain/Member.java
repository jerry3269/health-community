package project.healthcommunity.member.domain;

import jakarta.persistence.*;
import lombok.*;
import project.healthcommunity.global.basic.BaseEntity;
import project.healthcommunity.comment.domain.Comment;
import project.healthcommunity.member.dto.UpdateMemberDto;

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

    private String loginId;
    private String password;


    @OneToMany(mappedBy = "member")
    private List<Comment> commentList = new ArrayList<>();

    private int commentCount = 0;


    // == 생성자 == //
    @Builder
    public Member(String username, int age, String loginId, String password) {
        this.username = username;
        this.age = age;
        this.loginId = loginId;
        this.password = password;
        resetCountParameters();
    }

    // == 비지니스 로직 == //
    public void update(UpdateMemberDto updateMemberDto){
        this.username = updateMemberDto.getUsername();
        this.password = updateMemberDto.getPassword();
    }

    public void resetCountParameters(){
        this.commentCount = this.commentList.size();
    }
}
